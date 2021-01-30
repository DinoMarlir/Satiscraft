package de.polylymer.satiscraft.audio;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

public class AudioPlayer {

    public AudioPlayer(Sound sound) {
        for (Player player : Bukkit.getOnlinePlayers()) {
            player.playSound(player.getLocation(), sound, 10, 1);
        }
    }

    public AudioPlayer(Player player, Sound sound) {
        player.playSound(player.getLocation(), sound, 10, 1);
    }

    public AudioPlayer(Sound sound, Float pitch) {
        for (Player player : Bukkit.getOnlinePlayers()) {
            player.playSound(player.getLocation(), sound, 10, pitch);
        }
    }

    public AudioPlayer(Player player, Sound sound, Float pitch) {
        player.playSound(player.getLocation(), sound, 10, pitch);
    }

    public AudioPlayer(SoundTrack soundTrack) {
        for (Player player : Bukkit.getOnlinePlayers()) {
            player.playSound(player.getLocation(), soundTrack.getSound(), 10, 1);
        }
    }

    public AudioPlayer(Player player, SoundTrack soundTrack) {
        player.playSound(player.getLocation(), soundTrack.getSound(), 10, 1);
    }

}
