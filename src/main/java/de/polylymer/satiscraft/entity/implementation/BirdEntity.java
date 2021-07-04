package de.polylymer.satiscraft.entity.implementation;

import de.polylymer.satiscraft.entity.FactoryGameEntity;
import de.polylymer.satiscraft.entity.IAnimateable;
import de.polylymer.satiscraft.entity.animation.AnimationBuilder;
import de.polylymer.satiscraft.modding.Identifier;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Cow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;

import java.util.Random;

public class BirdEntity extends FactoryGameEntity<ArmorStand> implements IAnimateable<ArmorStand> {

    public BirdEntity() {
        super(new Identifier("bird"));
    }

    private Cow stolenEntity = null;

    @Override
    public void afterSpawn(ArmorStand entity) {
        entity.setVisible(false);
        entity.setArms(true);
        entity.getEquipment().setItemInMainHand(new ItemStack(Material.BONE));
        entity.getEquipment().setItemInOffHand(new ItemStack(Material.BONE));
        entity.getEquipment().setHelmet(new ItemStack(Material.BROWN_WOOL));
        for (EquipmentSlot equipmentSlot : EquipmentSlot.values()) {
            entity.addEquipmentLock(equipmentSlot, ArmorStand.LockType.ADDING_OR_CHANGING);
            entity.addEquipmentLock(equipmentSlot, ArmorStand.LockType.REMOVING_OR_CHANGING);
        }
        entity.setGravity(false);
    }

    @Override
    public FactoryGameEntity<ArmorStand> getNewInstance() {
        return new BirdEntity();
    }

    @Override
    public void setLivingAnimations(ArmorStand entity, float tickDelta) {
        for (Entity targetPossibility : entity.getNearbyEntities(15, 150, 15)) {
            AnimationBuilder animationBuilder = new AnimationBuilder(this);
            animationBuilder.withRemoveOnBlock(false);
            animationBuilder.withDeathAnimation(it -> it.getWorld().playSound(it.getLocation(), Sound.ENTITY_PHANTOM_DEATH, 1, 10));
            animationBuilder.withWalkingAnimation(it -> {
                if(stolenEntity != null) {
                    stolenEntity.teleport(entity.getLocation().clone().subtract(0,0.7,0));
                }
            });
            animationBuilder.shouldAttackWhen(it -> stolenEntity == null || stolenEntity.isDead() && it != stolenEntity);
            if(targetPossibility instanceof Cow) {
                if(!targetPossibility.isDead()) {
                    if(targetPossibility != stolenEntity && stolenEntity == null || stolenEntity.isDead()) {
                        animationBuilder.withPathfinderGoal((LivingEntity) targetPossibility, 0.1);
                    } else {
                        continue;
                    }
                    animationBuilder.withAttackAnimation(it -> {
                        stolenEntity = (Cow) it;
                        it.getWorld().playSound(it.getLocation(), Sound.ENTITY_PHANTOM_BITE, 1, 10);
                        int randomY = new Random().nextInt(270);
                        animationBuilder.withPathfinderGoal(entity.getLocation().clone().subtract(0,entity.getLocation().getY(),0).add(15, randomY > 80 ? randomY : 87,15));
                    });
                }
            } else {
                int randomY = new Random().nextInt(270);
                animationBuilder.withPathfinderGoal(entity.getLocation().clone().subtract(0,entity.getLocation().getY(),0).add(15, randomY > 80 ? randomY : 87,15));
            }
            animationBuilder.apply();
            break;
        }
    }
}
