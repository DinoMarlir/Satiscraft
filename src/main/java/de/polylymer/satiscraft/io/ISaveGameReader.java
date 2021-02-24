package de.polylymer.satiscraft.io;

import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;

public class ISaveGameReader {

    private final File file;
    private final YamlConfiguration yamlConfiguration;

    public ISaveGameReader(ISaveGame saveGame) {
        file = saveGame.getFile();
        yamlConfiguration = YamlConfiguration.loadConfiguration(file);
    }

    @SuppressWarnings("unchecked")
    public <T> T read(String key) {
        return (T) yamlConfiguration.get(key);
    }

}
