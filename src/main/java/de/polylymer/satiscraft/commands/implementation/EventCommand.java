package de.polylymer.satiscraft.commands.implementation;

import de.polylymer.satiscraft.commands.AbstractCommand;
import de.polylymer.satiscraft.commands.CommandCompleter;
import de.polylymer.satiscraft.event.Event;
import de.polylymer.satiscraft.internal.FactoryGameCrashReport;
import de.polylymer.satiscraft.internal.eventmanager.EventDisplay;
import de.polylymer.satiscraft.internal.exception.FactoryGameEventInitializeException;
import de.polylymer.satiscraft.modding.event.Action;
import org.bukkit.command.CommandSender;

import java.lang.reflect.InvocationTargetException;

public class EventCommand extends AbstractCommand {

    private static CommandCompleter commandCompleter;

    public EventCommand() {
        super("event", "satiscraft.factory.event");
        commandCompleter = new CommandCompleter();
        commandCompleter.addSuggestion(1, "display");
        commandCompleter.addSuggestion(1, "trigger");
        for (Event event : Event.values()) {
            commandCompleter.addSuggestion(2, event.name().toLowerCase());
        }
        commandCompleter.suggest(this);
    }

    @Override
    public void handle(CommandSender sender, String[] args) {
        if(args.length == 1) {
            EventDisplay.display("core");
        } else if(args.length == 2) {
            if(args[0].equalsIgnoreCase("trigger")) {
                Event event = Event.valueOf(args[1].toUpperCase());
                try {
                    Event.trigger(event, getClass().getMethod("handle", CommandSender.class, String[].class), null);
                } catch (Exception e) {
                    new FactoryGameCrashReport(e, event, getClass());
                }
            }
        }
    }
}
