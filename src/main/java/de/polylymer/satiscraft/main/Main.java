package de.polylymer.satiscraft.main;

import mooziii.annotation.PluginApp;
import mooziii.plugin.BSpigotApplication;
import org.bukkit.plugin.java.JavaPlugin;

@PluginApp(main = Main.class)
public class Main extends BSpigotApplication {
    @Override
    public String name() {
        return "Satiscraft";
    }

    @Override
    public String version() {
        return "1.0.0";
    }

    @Override
    public JavaPlugin core() {
        return this;
    }

    @Override
    public void load() {

    }

    @Override
    public void startup() {

    }

    @Override
    public void shutdown() {

    }
}
