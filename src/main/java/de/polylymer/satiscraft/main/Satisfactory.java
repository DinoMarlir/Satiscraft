package de.polylymer.satiscraft.main;

import de.polylymer.satiscraft.commands.implementation.EventCommand;
import de.polylymer.satiscraft.commands.implementation.SoundCommand;
import de.polylymer.satiscraft.commands.implementation.SummonCommand;
import de.polylymer.satiscraft.config.Localization;
import de.polylymer.satiscraft.entity.implementation.BirdEntity;
import de.polylymer.satiscraft.entity.implementation.HoveringJetpackEntity;
import de.polylymer.satiscraft.internal.FactoryGameServer;
import de.polylymer.satiscraft.io.ISaveGame;
import de.polylymer.satiscraft.io.ISaveGameBuilder;
import de.polylymer.satiscraft.listener.PlayerJoinListener;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import java.util.Date;

public class Satisfactory extends JavaPlugin {

    private static Satisfactory satisfactory;
    private static FactoryGameServer integratedServer;
    private static ISaveGame saveGame;

    public ISaveGame getSaveGame() {
        return saveGame;
    }

    public static Satisfactory getFactory() {
        return satisfactory;
    }

    @Override
    public void onLoad() {
        new Localization();
    }

    @Override
    public void onEnable() {
        satisfactory = this;
        saveGame = new ISaveGameBuilder().setLastModification(new Date()).build();
        integratedServer = new FactoryGameServer();
        PluginManager pluginManager = Bukkit.getPluginManager();
        pluginManager.registerEvents(new PlayerJoinListener(), this);
        getIntegratedServer().registerEntity(new HoveringJetpackEntity());
        getIntegratedServer().registerEntity(new BirdEntity());
        new EventCommand();
        new SoundCommand();
        new SummonCommand();
    }

    public FactoryGameServer getIntegratedServer() {
        return integratedServer;
    }

    @Override
    public void onDisable() {
        //write heap to temp.yml and read temp.yml and put stuff back on heap when server starts again
        satisfactory = null;
    }
}
