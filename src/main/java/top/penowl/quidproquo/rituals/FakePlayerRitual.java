package top.penowl.quidproquo.rituals;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Slime;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.Random;

import top.penowl.quidproquo.Ritual;

public class FakePlayerRitual extends Ritual {

    @Override
    public void setup() {
        addIngredient(Material.WHEAT, 32);
        addIngredient(Material.LEAVES, 32);
        name = "player illusion";
        notify = false;
        health = 4;
    }

    @Override
    public void execute(Player caster, Player target, Location location) {
        Location loc = target.getLocation();
        Random random = new Random();
        int x = random.nextInt(51) - 25;
        int y = random.nextInt(51) - 25;
        int z = random.nextInt(51) - 25;
        Entity fakePlayer = target.getWorld().spawnEntity(loc.clone().add(x, y, z), EntityType.SLIME);
        LivingEntity fp_livingEntity = (LivingEntity)fakePlayer;
        fp_livingEntity.setMaxHealth(100.0);
        fp_livingEntity.setHealth(100.0);
        fp_livingEntity.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 200, 100));
        fp_livingEntity.addPotionEffect(new PotionEffect(PotionEffectType.WITHER, 200, 1));
        Entity armorStand = target.getWorld().spawnEntity(loc.clone().add(x, y, z), EntityType.ARMOR_STAND);
        armorStand.setCustomName(caster.getName());
        armorStand.setCustomNameVisible(true);
        fp_livingEntity.setPassenger(armorStand);
        Slime fpSlime = (Slime)fp_livingEntity;
        fpSlime.setSize(1);
    }
    
}
