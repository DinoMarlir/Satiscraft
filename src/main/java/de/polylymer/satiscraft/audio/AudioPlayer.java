package de.polylymer.satiscraft.audio;

import de.polylymer.satiscraft.internal.FactoryGamePlayer;
import de.polylymer.satiscraft.main.Satisfactory;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

public class AudioPlayer {

    public AudioPlayer(Sound sound) {
        for (Player player : Bukkit.getOnlinePlayers()) {
            player.playSound(player.getLocation(), sound, 1, 1);
        }
    }

    public AudioPlayer(Player player, Sound sound) {
        player.playSound(player.getLocation(), sound, 1, 1);
    }

    public AudioPlayer(Sound sound, Float pitch) {
        for (Player player : Bukkit.getOnlinePlayers()) {
            player.playSound(player.getLocation(), sound, 1, pitch);
        }
    }

    public AudioPlayer(Player player, Sound sound, Float pitch) {
        player.playSound(player.getLocation(), sound, 1, pitch);
    }

    public AudioPlayer(SoundTrack soundTrack) {
        for (Player player : Bukkit.getOnlinePlayers()) {
            player.playSound(player.getLocation(), soundTrack.getSound(), 1, 1);
            FactoryGamePlayer factoryGamePlayer = Satisfactory.getFactory().getIntegratedServer().getPlayer(player.getUniqueId());
            factoryGamePlayer.setPlayingSoundtrack(true);
            factoryGamePlayer.setCurrentSoundtrack(soundTrack);
            Bukkit.getScheduler().runTaskLater(Satisfactory.getFactory(), () -> {
                factoryGamePlayer.setPlayingSoundtrack(false);
                factoryGamePlayer.setCurrentSoundtrack(null);
            }, soundTrack.getDuration());
        }
    }

    public AudioPlayer(Player player, SoundTrack soundTrack) {
        player.playSound(player.getLocation(), soundTrack.getSound(), 1, 1);
        FactoryGamePlayer factoryGamePlayer = Satisfactory.getFactory().getIntegratedServer().getPlayer(player.getUniqueId());
        factoryGamePlayer.setPlayingSoundtrack(true);
        factoryGamePlayer.setCurrentSoundtrack(soundTrack);
        Bukkit.getScheduler().runTaskLater(Satisfactory.getFactory(), () -> {
            factoryGamePlayer.setPlayingSoundtrack(false);
            factoryGamePlayer.setCurrentSoundtrack(null);
        }, soundTrack.getDuration());
    }

}
