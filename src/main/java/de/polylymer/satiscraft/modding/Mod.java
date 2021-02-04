package de.polylymer.satiscraft.modding;

import org.bukkit.plugin.java.JavaPlugin;

public abstract class Mod extends JavaPlugin {

    public abstract void finishInit();
    public abstract void initialize();
    public abstract void shutdown();

    @Override
    public void onLoad() {
        initialize();
    }

    @Override
    public void onDisable() {
        shutdown();
    }

    @Override
    public void onEnable() {
        finishInit();
    }
}
