package com.aionemu.gameserver.model.gameobjects.player;

public class PortalCooldownItem {

	private int worldId;
    private int entryCount;
    private long cooldown;

    public PortalCooldownItem(int worldId, int entryCount, long cooldown) {
        this.worldId = worldId;
        this.entryCount = entryCount;
        this.cooldown = cooldown;
    }

    public int getWorldId() {
        return worldId;
    }

    public int getEntryCount() {
        return entryCount;
    }

    public void setEntryCount(int entryCount) {
        this.entryCount = entryCount;
    }

    public long getCooldown() {
        return cooldown;
    }

    public void setCooldown(long cooldown) {
        this.cooldown = cooldown;
    }
}
