package de.polylymer.satiscraft.commands.implementation;

import de.polylymer.satiscraft.audio.AudioPlayer;
import de.polylymer.satiscraft.audio.SoundTrack;
import de.polylymer.satiscraft.commands.AbstractCommand;
import de.polylymer.satiscraft.commands.CommandCompleter;
import de.polylymer.satiscraft.entity.FactoryGameEntity;
import de.polylymer.satiscraft.main.Satisfactory;
import de.polylymer.satiscraft.modding.Identifier;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;

public class SummonCommand extends AbstractCommand {

    private static CommandCompleter commandCompleter;

    public SummonCommand() {
        super("summon");
        commandCompleter = new CommandCompleter();
        for (FactoryGameEntity<?> factoryGameEntity : Satisfactory.getFactory().getIntegratedServer().getEntities()) {
            commandCompleter.addSuggestion(1, factoryGameEntity.getIdentifier().toString());
        }
        commandCompleter.suggest(this);
    }

    @Override
    public void handle(CommandSender sender, String[] args) {
        if(sender instanceof Player) {
            if(args.length == 1) {
                for (FactoryGameEntity<?> factoryGameEntity : Satisfactory.getFactory().getIntegratedServer().getEntities()) {
                    if(factoryGameEntity.getIdentifier().equals(new Identifier(args[0].split(":")[0],args[0].split(":")[1]))) {
                        FactoryGameEntity<?> entity = factoryGameEntity.getNewInstance();
                        entity.prepareSpawn(sender);
                        entity.spawn(ArmorStand.class, ((Player) sender).getLocation(), 3);
                    }
                }
            }
        }
    }
}
