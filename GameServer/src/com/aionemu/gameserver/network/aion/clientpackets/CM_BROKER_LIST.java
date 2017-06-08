package com.aionemu.gameserver.network.aion.clientpackets;

import com.aionemu.gameserver.model.gameobjects.player.Player;
import com.aionemu.gameserver.network.aion.AionClientPacket;
import com.aionemu.gameserver.network.aion.AionConnection.State;
import com.aionemu.gameserver.services.BrokerService;

public class CM_BROKER_LIST extends AionClientPacket
{
    @SuppressWarnings("unused")
    private int brokerId;
    private int sortType;
    private int page;
    private int listMask;
	
    public CM_BROKER_LIST(int opcode, State state, State... restStates) {
        super(opcode, state, restStates);
    }
	
    @Override
    protected void readImpl() {
        this.brokerId = readD();
        this.sortType = readC();
        this.page = readH();
        this.listMask = readH();
    }
	
    @Override
    protected void runImpl() {
        Player player = getConnection().getActivePlayer();
        BrokerService.getInstance().showRequestedItems(player, listMask, sortType, page, null);
    }
}