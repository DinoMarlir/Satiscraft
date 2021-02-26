package de.polylymer.satiscraft.element;

import de.polylymer.satiscraft.main.Satisfactory;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public interface ModularElementPlacer {

    default <E> void place(Player player, E e, Location location) {
        ModularBuilding modularBuilding = e.getClass().getAnnotation(ModularBuilding.class);
        if(modularBuilding != null) {
            String key = "building." + location.getBlockX() + "_" + location.getBlockY() + "_" + location.getBlockZ();
            Satisfactory.getFactory().getSaveGame().getWriter().write(key + e.getClass().getName(), true);
            Satisfactory.getFactory().getSaveGame().getWriter().write(key + e.getClass().getName() + ".recipe", null);
        } else {

        }
    }

}
