package de.polylymer.satiscraft.internal;

import de.polylymer.satiscraft.audio.SoundTrack;
import de.polylymer.satiscraft.io.ISaveGame;
import de.polylymer.satiscraft.main.Satisfactory;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import java.util.Optional;
import java.util.UUID;

public class FactoryGamePlayer {

    protected final UUID uuid;
    protected boolean isPlayingSoundtrack;
    protected SoundTrack currentSoundtrack;

    public FactoryGamePlayer(UUID uuid) {
        this.uuid = uuid;
    }

    public Optional<Player> getBukkitPlayer() {
        return Optional.ofNullable(Bukkit.getPlayer(this.uuid));
    }

    public boolean isPlayingSoundtrack() {
        return isPlayingSoundtrack;
    }

    public void setPlayingSoundtrack(boolean playingSoundtrack) {
        isPlayingSoundtrack = playingSoundtrack;
    }

    public void setCurrentSoundtrack(SoundTrack currentSoundtrack) {
        this.currentSoundtrack = currentSoundtrack;
    }

    public SoundTrack getCurrentSoundtrack() {
        return currentSoundtrack;
    }

    public boolean isServerOwner() {
        ISaveGame saveGame = Satisfactory.getFactory().getSaveGame();
        String uuid = saveGame.getReader().read("owner");
        return uuid.equals(this.uuid.toString());
    }

}
