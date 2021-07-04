package de.polylymer.satiscraft.element;

import de.polylymer.satiscraft.internal.eventmanager.EventDisplay;
import de.polylymer.satiscraft.internal.exception.FactoryGameBuildException;
import de.polylymer.satiscraft.internal.FactoryGameCrashReport;
import de.polylymer.satiscraft.main.Satisfactory;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public abstract class ModularElementPlacer {

    public <E> void place(Player player, E e, Location location) {
        EventDisplay.addToStash("|");
        ModularBuilding modularBuilding = e.getClass().getAnnotation(ModularBuilding.class);
        if(modularBuilding != null) {
            String key = "building." + location.getBlockX() + "_" + location.getBlockY() + "_" + location.getBlockZ();
            Satisfactory.getFactory().getSaveGame().getWriter().write(key + e.getClass().getName(), true);
            EventDisplay.addToStash("+");
            Satisfactory.getFactory().getSaveGame().getWriter().write(key + e.getClass().getName() + ".recipe", null);
            EventDisplay.addToStash("+");
        } else {
            EventDisplay.addToStash("-");
            new FactoryGameCrashReport(new FactoryGameBuildException("Cannot place illegally building: Building is not annotated with @ModularBuilding"), location, getClass());
        }
        EventDisplay.addToStash("|");
    }

}
