package de.polylymer.satiscraft.io;


import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class ISaveGameBuilder {

    private File file;
    private YamlConfiguration yamlConfiguration;

    public ISaveGameBuilder() {
        if(!new File("./savegames").exists()) {
            new File("./savegames").mkdirs();
        }
        file = new File("./savegames/", "savegame.yml");
        if(!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        yamlConfiguration = YamlConfiguration.loadConfiguration(file);
    }

    public ISaveGameBuilder setOwner(UUID uuid) {
        write("owner", uuid);
        return this;
    }

    public ISaveGameBuilder setLastModification(Date date) {
        write("last-modify", new SimpleDateFormat("mm:HH, dd.MM.yyyy").format(date));
        return this;
    }

    public ISaveGame build() {
        return new ISaveGame(file);
    }

    private <V> void write(String key, V value) {
        try {
            yamlConfiguration.set(key, value);
            yamlConfiguration.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
