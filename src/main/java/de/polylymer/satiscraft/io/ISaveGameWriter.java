package de.polylymer.satiscraft.io;

import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ISaveGameWriter {

    private final File file;
    private final YamlConfiguration yamlConfiguration;

    public ISaveGameWriter(ISaveGame saveGame) {
        file = saveGame.getFile();
        yamlConfiguration = YamlConfiguration.loadConfiguration(file);
    }

    @SuppressWarnings("unchecked")
    public <V> void write(String key, V value) {
        try {
            yamlConfiguration.set(key,value);
            yamlConfiguration.set("last-modify", new SimpleDateFormat("mm:HH, dd.MM.yyyy").format(new Date()));
            yamlConfiguration.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
