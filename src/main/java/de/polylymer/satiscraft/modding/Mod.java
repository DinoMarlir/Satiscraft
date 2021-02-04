package de.polylymer.satiscraft.modding;

import mooziii.plugin.BSpigotApplication;
import org.bukkit.plugin.java.JavaPlugin;

public abstract class Mod extends BSpigotApplication {

    public abstract void finishInit();

    public abstract void initialize();

    public abstract void closeAndClean();

    @Override
    public void load() {
        initialize();
    }

    @Override
    public void startup() {
        finishInit();
    }

    @Override
    public void shutdown() {
        closeAndClean();
    }
}
