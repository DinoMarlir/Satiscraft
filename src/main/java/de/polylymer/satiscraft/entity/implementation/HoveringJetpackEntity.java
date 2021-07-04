package de.polylymer.satiscraft.entity.implementation;

import de.polylymer.satiscraft.entity.FactoryGameEntity;
import de.polylymer.satiscraft.entity.IAnimateable;
import de.polylymer.satiscraft.entity.animation.AnimationBuilder;
import de.polylymer.satiscraft.modding.Identifier;
import org.bukkit.*;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.EulerAngle;

import java.util.Random;

public class HoveringJetpackEntity extends FactoryGameEntity<ArmorStand> implements IAnimateable<ArmorStand> {

    private Player owner;

    public HoveringJetpackEntity() {
        super(new Identifier("hovering_jetpack"));
    }

    @Override
    public void setLivingAnimations(ArmorStand entity, float tickDelta) {
        new AnimationBuilder(this)
                .withAttackAnimation(it -> {
                    it.getWorld().spawnParticle(Particle.SWEEP_ATTACK, it.getLocation().clone().add(0,1.4,0), 1);
                    drawParticleCircle(it.getLocation(), 1.4);
                    it.getWorld().playSound(it.getLocation(), Sound.ENTITY_PLAYER_ATTACK_SWEEP, 1, 1);
                    entity.setRotation(Float.parseFloat(String.valueOf(new Random().nextInt(270))),Float.parseFloat(String.valueOf(new Random().nextInt(270))));
                })
                .withRemoveOnBlock(true)
                .withWalkingAnimation(it -> entity.setRightArmPose(new EulerAngle(267f,273f,2f)))
                .withPathfinderGoal(owner, 4)
                .apply();
    }

    @Override
    public void prepareSpawn(Object obj) {
        if(obj instanceof Player) {
            this.owner = (Player) obj;
        }
    }

    @Override
    public void afterSpawn(ArmorStand entity) {
        entity.setVisible(false);
        entity.setArms(true);
        entity.getEquipment().setItemInMainHand(new ItemStack(Material.IRON_SWORD));
        for (EquipmentSlot equipmentSlot : EquipmentSlot.values()) {
            entity.addEquipmentLock(equipmentSlot, ArmorStand.LockType.ADDING_OR_CHANGING);
            entity.addEquipmentLock(equipmentSlot, ArmorStand.LockType.REMOVING_OR_CHANGING);
        }
        entity.setGravity(false);
    }

    @Override
    public FactoryGameEntity<ArmorStand> getNewInstance() {
        return new HoveringJetpackEntity();
    }

    private void drawParticleCircle(Location location, double yOffset) {
        Location loc = location.clone();
        loc.add(0, yOffset, 0);
        for (int i = 0; i < 8; i++) {
            location.getWorld().spawnParticle(Particle.BLOCK_CRACK, new Location(loc.getWorld(), loc.getX(),loc.getY(),loc.getZ()), 2, Bukkit.createBlockData(Material.REDSTONE_BLOCK));
        }
    }
}
