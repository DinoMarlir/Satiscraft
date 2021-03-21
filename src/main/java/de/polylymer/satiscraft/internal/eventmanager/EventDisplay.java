package de.polylymer.satiscraft.internal.eventmanager;

import com.google.common.collect.ImmutableMap;
import de.polylymer.satiscraft.config.Localization;
import de.polylymer.satiscraft.event.Event;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

public class EventDisplay {

    private static String history = "|++";
    private static String display = "Display Log from FactoryGameFactory: ";

    public static void display(String mod) {
        Bukkit.getOnlinePlayers().forEach(player -> player.sendMessage(Localization.getMessage("core.events.displaying", ImmutableMap.of("mod", mod), player.getLocale())));
        Bukkit.getOnlinePlayers().forEach(player -> player.sendMessage(display));
        Bukkit.getOnlinePlayers().forEach(player -> player.sendMessage(ChatColor.STRIKETHROUGH + "                                                                    "));
        Bukkit.getOnlinePlayers().forEach(player -> player.sendMessage(history.replace("|", ChatColor.BLUE + "|").replace("+", ChatColor.GREEN + "+").replace("-", ChatColor.RED + "-")));
    }

    public static void addToDisplay(String displayStash) {
        display+=displayStash;
    }

    public static void addToStash(String stash) {
        history+=stash;
    }

}
