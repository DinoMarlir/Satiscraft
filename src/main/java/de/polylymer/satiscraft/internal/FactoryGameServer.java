package de.polylymer.satiscraft.internal;

import de.polylymer.satiscraft.entity.FactoryGameEntity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.UUID;

public class FactoryGameServer {

    private static HashMap<UUID, FactoryGamePlayer> factoryPlayers = new HashMap<>();
    private static Collection<FactoryGameEntity<?>> entities = new ArrayList<>();

    public FactoryGamePlayer createPlayer(UUID uuid) {
        FactoryGamePlayer factoryGamePlayer = new FactoryGamePlayer(uuid);
        factoryPlayers.put(uuid,factoryGamePlayer);
        return factoryGamePlayer;
    }

    public void registerEntity(FactoryGameEntity<?> factoryGameEntity) {
        entities.add(factoryGameEntity);
    }

    public Collection<FactoryGameEntity<?>> getEntities() {
        return entities;
    }

    public FactoryGamePlayer getPlayer(UUID uuid) {
        return factoryPlayers.get(uuid);
    }

    public boolean existsPlayer(UUID uuid) {
        return factoryPlayers.containsKey(uuid);
    }

}
