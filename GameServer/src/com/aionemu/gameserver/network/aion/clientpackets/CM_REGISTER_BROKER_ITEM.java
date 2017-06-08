package com.aionemu.gameserver.network.aion.clientpackets;

import com.aionemu.gameserver.model.gameobjects.player.Player;
import com.aionemu.gameserver.network.aion.AionClientPacket;
import com.aionemu.gameserver.network.aion.AionConnection.State;
import com.aionemu.gameserver.services.BrokerService;

public class CM_REGISTER_BROKER_ITEM extends AionClientPacket
{
    @SuppressWarnings("unused")
    private int brokerId;
    private int itemUniqueId;
    private long PricePerItem;
    private long itemCount;
	private boolean isSplitSell;
	
    public CM_REGISTER_BROKER_ITEM(int opcode, State state, State... restStates) {
        super(opcode, state, restStates);
    }
	
    @Override
    protected void readImpl() {
        this.brokerId = readD();
        this.itemUniqueId = readD();
        this.PricePerItem = readQ();
        this.itemCount = readQ();
		this.isSplitSell = (readC() == 1);
    }
	
    @Override
    protected void runImpl() {
        Player player = getConnection().getActivePlayer();
        if (player.isTrading() || PricePerItem < 1 || itemCount < 1) {
            return;
        }
        BrokerService.getInstance().registerItem(player, itemUniqueId, itemCount, PricePerItem, isSplitSell);
    }
}