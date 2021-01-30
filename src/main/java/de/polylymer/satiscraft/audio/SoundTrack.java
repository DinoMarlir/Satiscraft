package de.polylymer.satiscraft.audio;

import org.bukkit.Sound;

public enum SoundTrack {

    MAIN_MENU(Sound.ENTITY_FOX_TELEPORT, 292*20L),
    EARLY_DEVELOPMENT(Sound.AMBIENT_UNDERWATER_ENTER,460*20L),
    SATISFACTORY(Sound.AMBIENT_CRIMSON_FOREST_MOOD,195*20L),
    FLOURISH_LAND(Sound.AMBIENT_WARPED_FOREST_MOOD,448*20L);

    private Sound sound;
    private Long duration;

    SoundTrack(Sound sound, Long duration) {
        this.sound = sound;
        this.duration = duration;
    }

    public Sound getSound() {
        return sound;
    }

    public Long getDuration() {return duration;}
}
