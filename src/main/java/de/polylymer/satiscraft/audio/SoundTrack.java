package de.polylymer.satiscraft.audio;

import org.bukkit.Sound;

public enum SoundTrack {

    MAIN_MENU(Sound.ENTITY_FOX_TELEPORT),
    EARLY_DEVELOPMENT(Sound.AMBIENT_CRIMSON_FOREST_MOOD),
    SATISFACTORY(Sound.AMBIENT_UNDERWATER_ENTER),
    FLOURISH_LAND(Sound.AMBIENT_WARPED_FOREST_MOOD);

    private Sound sound;

    SoundTrack(Sound sound) {
        this.sound = sound;
    }

    public Sound getSound() {
        return sound;
    }
}
