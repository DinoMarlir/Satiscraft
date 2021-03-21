package de.polylymer.satiscraft.modding.event.player;

import de.polylymer.satiscraft.element.ModularBuilding;
import de.polylymer.satiscraft.modding.event.Action;
import de.polylymer.satiscraft.modding.event.PlayerAction;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public class PlayerPlaceModularBuildingAction extends PlayerAction {


    private ModularBuilding modularBuilding;
    private Location where;

    public PlayerPlaceModularBuildingAction(Player who, ModularBuilding modularBuilding, Location where) {
        super(who);
        this.modularBuilding = modularBuilding;
        this.where = where;
    }

    public ModularBuilding getModularBuilding() {
        return modularBuilding;
    }

    public void setModularBuilding(ModularBuilding modularBuilding) {
        this.modularBuilding = modularBuilding;
    }

    public Location getWhere() {
        return where;
    }

    public void setWhere(Location where) {
        this.where = where;
    }
}
