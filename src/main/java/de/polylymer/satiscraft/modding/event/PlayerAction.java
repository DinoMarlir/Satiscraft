package de.polylymer.satiscraft.modding.event;

import org.bukkit.entity.Player;

public abstract class PlayerAction extends Action {

    protected final Player who;

    public PlayerAction(Player who) {
        this.who = who;
    }

    public Player getPlayer() {
        return this.who;
    }

}
