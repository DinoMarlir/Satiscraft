package de.polylymer.satiscraft.entity;

import de.polylymer.satiscraft.main.Satisfactory;
import de.polylymer.satiscraft.modding.Identifier;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.scheduler.BukkitRunnable;
import java.util.function.Consumer;

public abstract class FactoryGameEntity<T extends Entity> {

    private Entity originEntity;
    private Consumer<Entity> runConsumer;
    private Consumer<Entity> deathConsumer;
    private Identifier identifier;

    public FactoryGameEntity(Identifier identifier) {
        this.identifier = identifier;
    }

    public Identifier getIdentifier() {
        return identifier;
    }

    public void prepareSpawn(Object obj) {

    }

    public void afterSpawn(T entity) {

    }

    public abstract FactoryGameEntity<T> getNewInstance();

    public void spawn(Class<? extends Entity> origin, Location location, int tickDelta) {
        originEntity = Bukkit.getWorld("world").spawn(location, origin);
        if(this instanceof IAnimateable<?>) {
            IAnimateable<T> iAnimateable = (IAnimateable<T>) this;
            new BukkitRunnable() {
                @Override
                public void run() {
                    if(originEntity.isDead()) {
                        if(deathConsumer != null) {
                            deathConsumer.accept(originEntity);
                        }
                        cancel();
                    } else {
                        if(runConsumer != null) {
                            runConsumer.accept(originEntity);
                        }
                    }
                    iAnimateable.setLivingAnimations((T) originEntity, Float.parseFloat(String.valueOf(tickDelta)));
                }
            }.runTaskTimer(Satisfactory.getFactory(), 2, tickDelta);
        }
        afterSpawn((T) originEntity);
    }

    public void applyDeaths(Consumer<Entity> deathConsumer) {
        this.deathConsumer = deathConsumer;
    }

    public void applyRuns(Consumer<Entity> runs) {
        this.runConsumer = runs;
    }

    public Entity getBukkitEntity() {
        return originEntity;
    }

}
