package de.polylymer.satiscraft.commands.implementation;

import de.polylymer.satiscraft.audio.AudioPlayer;
import de.polylymer.satiscraft.audio.SoundTrack;
import de.polylymer.satiscraft.commands.AbstractCommand;
import de.polylymer.satiscraft.commands.CommandCompleter;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SoundCommand extends AbstractCommand {

    private static CommandCompleter commandCompleter;

    public SoundCommand() {
        super("playsound");
        commandCompleter = new CommandCompleter();
        for (SoundTrack soundTrack : SoundTrack.values()) {
            commandCompleter.addSuggestion(1, soundTrack.name().toLowerCase());
        }
        commandCompleter.suggest(this);
    }

    @Override
    public void handle(CommandSender sender, String[] args) {
        if(sender instanceof Player) {
            if(args.length == 1) {
                SoundTrack soundTrack = SoundTrack.valueOf(args[0].toUpperCase());
                new AudioPlayer((Player) sender, soundTrack);
            }
        }
    }
}
