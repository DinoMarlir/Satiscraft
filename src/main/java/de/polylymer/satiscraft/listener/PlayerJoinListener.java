package de.polylymer.satiscraft.listener;

import de.polylymer.satiscraft.internal.FactoryGameServer;
import de.polylymer.satiscraft.main.Satisfactory;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoinListener implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        FactoryGameServer integratedServer = Satisfactory.getFactory().getIntegratedServer();
        Player player = event.getPlayer();
        if(!integratedServer.existsPlayer(player.getUniqueId())) {
            integratedServer.createPlayer(player.getUniqueId());
        }
    }
}
