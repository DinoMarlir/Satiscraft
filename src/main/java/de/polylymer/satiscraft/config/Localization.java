package de.polylymer.satiscraft.config;

import com.google.common.collect.ImmutableMap;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;

public class Localization {

    private static YamlConfiguration yamlConfiguration;
    private static File file;
    private static String colorKey = "&";
    private static String colorValue = "\u00A7";

    public Localization() {
        try {
            file = new File("./plugins/", "config.yml");
            if(!file.exists()) {
                file.createNewFile();
            }
            yamlConfiguration = YamlConfiguration.loadConfiguration(file);
        } catch (Exception e) {

        }
    }

    public static String getMessage(String key, String locale) {
        String lang = locale.split("_")[0];
        if(yamlConfiguration.contains("messages." + lang + "." + key)) {
            return yamlConfiguration.getString("messages." + lang + "." + key).replace(colorKey,colorValue);
        } else {
            try {
                yamlConfiguration.set("messages." + lang + "." + key, "messages." + lang + "." + key);
                yamlConfiguration.save(file);
            } catch (Exception e) {

            }
        }
        return "messages." + lang + "." + key;
    }

    public static String getMessage(String key, ImmutableMap<String,String> map, String locale) {
        String lang = locale.split("_")[0];
        if(yamlConfiguration.contains("messages." + lang + "." + key)) {
            String result = yamlConfiguration.getString("messages." + lang + "." + key);
            for(String s : map.keySet()) {
                result = yamlConfiguration.getString("messages." + lang + "." + key).replace( "$" + s, map.get(s));
            }
            return result;
        } else {
            try {
                yamlConfiguration.set("messages." + lang + "." + key, "messages." + lang + "." + key);
                yamlConfiguration.save(file);
            } catch (Exception e) {

            }
        }
        return "messages." + lang + "." + key;
    }

}
