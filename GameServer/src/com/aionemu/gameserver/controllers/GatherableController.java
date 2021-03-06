package com.aionemu.gameserver.controllers;

import java.util.List;

import com.aionemu.commons.utils.Rnd;

import com.aionemu.gameserver.configs.main.*;
import com.aionemu.gameserver.controllers.observer.*;
import com.aionemu.gameserver.dataholders.DataManager;
import com.aionemu.gameserver.model.*;
import com.aionemu.gameserver.model.actions.*;
import com.aionemu.gameserver.model.gameobjects.*;
import com.aionemu.gameserver.model.gameobjects.player.*;
import com.aionemu.gameserver.model.gameobjects.state.*;
import com.aionemu.gameserver.model.templates.gather.*;
import com.aionemu.gameserver.network.aion.serverpackets.*;
import com.aionemu.gameserver.services.*;
import com.aionemu.gameserver.services.player.*;
import com.aionemu.gameserver.skillengine.effect.*;
import com.aionemu.gameserver.skillengine.task.*;
import com.aionemu.gameserver.utils.*;
import com.aionemu.gameserver.utils.captcha.*;
import com.aionemu.gameserver.world.*;

public class GatherableController extends VisibleObjectController<Gatherable>
{
	private int gatherCount;
	private int currentGatherer;
	private GatheringTask task;
	private GatherState state = GatherState.IDLE;
	private RndSelector<Material> mats;
	
	public enum GatherState {
		GATHERED,
		GATHERING,
		IDLE
	}
	
	public void onStartUse(final Player player) {
		final GatherableTemplate template = this.getOwner().getObjectTemplate();
		int gatherId = template.getTemplateId();
		if (player.getLevel() > 10) {
			switch (gatherId) {
				case 400201: //Impure Iron Ore.
				case 400251: //Impure Iron Ore.
				case 400601: //Young Aria.
				case 400651: //Young Azpha.
				case 400701: //Mela Sapling.
				case 400751: //Raydam Sapling.
					PacketSendUtility.sendPacket(player, SM_SYSTEM_MESSAGE.STR_GATHER_INCORRECT_SKILL);
				break;
			}
			finishGathering(player);
		}
		if (template.getLevelLimit() > 0) {
			if (player.getLevel() < template.getLevelLimit()) {
				PacketSendUtility.sendPacket(player, new SM_SYSTEM_MESSAGE(1400737, template.getLevelLimit()));
				return;
			}
		} if (player.isInPlayerMode(PlayerMode.RIDE)) {
			PacketSendUtility.sendPacket(player, new SM_SYSTEM_MESSAGE(1401096));
			return;
		} if (player.getInventory().isFull()) {
			PacketSendUtility.sendPacket(player, new SM_SYSTEM_MESSAGE(1330036));
			return;
		} if (MathUtil.getDistance(getOwner(), player) > 6)
			return;
		if (!checkGatherable(player, template))
			return;
		if (!checkPlayerSkill(player, template))
			return;
		byte result = checkPlayerRequiredExtractor(player, template);
		if (result == 0)
			return;
		if (SecurityConfig.CAPTCHA_ENABLE) {
			if (SecurityConfig.CAPTCHA_APPEAR.equals(template.getSourceType()) || SecurityConfig.CAPTCHA_APPEAR.equals("ALL")) {
				int rate = SecurityConfig.CAPTCHA_APPEAR_RATE;
				if (template.getCaptchaRate() > 0) {
					rate = (int) (template.getCaptchaRate() * 0.1f);
				} if (Rnd.get(0, 100) < rate) {
					player.setCaptchaWord(CAPTCHAUtil.getRandomWord());
					player.setCaptchaImage(CAPTCHAUtil.createCAPTCHA(player.getCaptchaWord()).array());
					PunishmentService.setIsNotGatherable(player, 0, true, SecurityConfig.CAPTCHA_EXTRACTION_BAN_TIME * 1000);
					//You were poisoned during extraction and cannot extract for (Time remaining: 10Min)
					PacketSendUtility.sendPacket(player, SM_SYSTEM_MESSAGE.STR_MSG_CAPTCHA_RESTRICTED("10"));
					PacketSendUtility.sendPacket(player, new SM_QUEST_ACTION(0, 600));
				}
			}
		}
		List<Material> materials = null;
		switch (result) {
			case 1:
				materials = template.getExtraMaterials().getMaterial();
			break;
			case 2:
				materials = template.getMaterials().getMaterial();
			break;
		}
		mats = new RndSelector<Material>();
		for (Material mat: materials) {
			mats.add(mat, mat.getRate());
		}
		synchronized (state) {
			if (state != GatherState.GATHERING) {
				state = GatherState.GATHERING;
				currentGatherer = player.getObjectId();
				startGatherProtection(player);
				player.getObserveController().attach(new StartMovingListener() {
					@Override
					public void moved() {
						finishGathering(player);
						stopGatherProtection(player);
					}
				});
				int skillLvlDiff = player.getSkillList().getSkillLevel(template.getHarvestSkill()) - template.getSkillLevel();
				task = new GatheringTask(player, getOwner(), getMaterial(), skillLvlDiff);
				task.start();
			}
		}
	}
	
	public Material getMaterial() {
		Material m = mats.select();
        int chance = Rnd.get(m.getRate());
        int current = 0;
        current += m.getRate();
        if (mats != null) {
            if (current >= chance) {
                return m;
            }
        }
        return null;
	}
	
	private boolean checkPlayerSkill(final Player player, final GatherableTemplate template) {
		int harvestSkillId = template.getHarvestSkill();
		if (!player.getSkillList().isSkillPresent(harvestSkillId)) {
			if (harvestSkillId == 30001) {
				PacketSendUtility.sendPacket(player, SM_SYSTEM_MESSAGE.STR_GATHER_INCORRECT_SKILL);
			} else {
				PacketSendUtility.sendPacket(player, new SM_SYSTEM_MESSAGE(1330054, new DescriptionId(DataManager.SKILL_DATA.getSkillTemplate(harvestSkillId).getNameId())));
			}
			return false;
		} if (player.getSkillList().getSkillLevel(harvestSkillId) < template.getSkillLevel()) {
			PacketSendUtility.sendPacket(player, new SM_SYSTEM_MESSAGE(1330001, new DescriptionId(DataManager.SKILL_DATA.getSkillTemplate(harvestSkillId).getNameId())));
			return false;
		}
		return true;
	}
	
	private byte checkPlayerRequiredExtractor(final Player player, final GatherableTemplate template) {
		if (template.getRequiredItemId() > 0) {
			if (template.getCheckType() == 1) {
				List<Item> items = player.getEquipment().getEquippedItemsByItemId(template.getRequiredItemId());
				boolean condOk = false;
				for (Item item: items) {
					if (item.isEquipped()) {
						condOk = true;
						break;
					}
				}
				return (byte) (condOk ? 1 : 2);
			} else if (template.getCheckType() == 2) {
				if (player.getInventory().getItemCountByItemId(template.getRequiredItemId()) < template.getEraseValue()) {
					PacketSendUtility.sendPacket(player, new SM_SYSTEM_MESSAGE(1400376, new DescriptionId(template.getRequiredItemNameId())));
					return 0;
				} else {
					return 1;
				}
			}
		}
		return 2;
	}
	
	private boolean checkGatherable(final Player player, final GatherableTemplate template) {
		if (player.isNotGatherable()) {
			//You are currently unable to extract. (Time remaining: 10Min)
			PacketSendUtility.sendPacket(player, new SM_SYSTEM_MESSAGE(1400273, (int) ((player.getGatherableTimer() - (System.currentTimeMillis() - player .getStopGatherable())) / 1000)));
			return false;
		}
		return true;
	}
	
	public void completeInteraction() {
		state = GatherState.IDLE;
		gatherCount++;
		if (gatherCount == getOwner().getObjectTemplate().getHarvestCount()) {
			onDespawn();
		}
	}
	
	public void rewardPlayer(Player player) {
		if (player != null) {
			int skillLvl = getOwner().getObjectTemplate().getSkillLevel();
			int xpReward = (int) ((0.0031 * (skillLvl + 5.3) * (skillLvl + 1592.8) + 60));
			if (player.getSkillList().addSkillXp(player, getOwner().getObjectTemplate().getHarvestSkill(),
				(int) RewardType.GATHERING.calcReward(player, xpReward), skillLvl)) {
				PacketSendUtility.sendPacket(player, SM_SYSTEM_MESSAGE.STR_EXTRACT_GATHERING_SUCCESS_GETEXP);
				player.getCommonData().addExp(xpReward, RewardType.GATHERING);
			} else {
				PacketSendUtility.sendPacket(player, SM_SYSTEM_MESSAGE.STR_MSG_DONT_GET_PRODUCTION_EXP(new DescriptionId(DataManager.SKILL_DATA.getSkillTemplate(getOwner().getObjectTemplate().getHarvestSkill()).getNameId())));
			}
		}
	}
	
	public void finishGathering(Player player) {
		if (currentGatherer == player.getObjectId()) {
			if (state == GatherState.GATHERING) {
				task.abort();
			}
			currentGatherer = 0;
			state = GatherState.IDLE;
		}
	}
	
   /**
	* This is prevent "Pvp Uncivilized" when a player want gather in peace.
	* The player become "Invisible" for other player.
	*/
	public void startGatherProtection(Player player) {
		if (CraftConfig.PROTECTION_GATHER_ENABLE) {
		    player.getEffectController().setAbnormal(AbnormalState.HIDE.getId());
			player.setVisualState(CreatureVisualState.HIDE3);
			PacketSendUtility.broadcastPacket(player, new SM_PLAYER_STATE(player), true);
		}
	}
	public void stopGatherProtection(Player player) {
		player.getEffectController().unsetAbnormal(AbnormalState.HIDE.getId());
		player.unsetVisualState(CreatureVisualState.HIDE3);
		PacketSendUtility.broadcastPacket(player, new SM_PLAYER_STATE(player), true);
	}
	
	@Override
	public void onDespawn() {
		Gatherable owner = getOwner();
		if (!getOwner().isInInstance()) {
			RespawnService.scheduleRespawnTask(owner);
		}
		World.getInstance().despawn(owner);
	}
	
	@Override
	public void onBeforeSpawn() {
		this.gatherCount = 0;
	}
	
	@Override
	public Gatherable getOwner() {
		return super.getOwner();
	}
}