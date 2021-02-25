package de.polylymer.satiscraft.element;

import org.bukkit.entity.Player;

public interface ModularElementPlacer {

    default <E> void place(Player player, E e) {
        ModularBuilding modularBuilding = e.getClass().getAnnotation(ModularBuilding.class);
        if(modularBuilding != null) {

        } else {

        }
    }

}
