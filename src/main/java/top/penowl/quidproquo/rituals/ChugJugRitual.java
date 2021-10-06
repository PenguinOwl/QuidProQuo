package top.penowl.quidproquo.rituals;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import top.penowl.quidproquo.Ritual;

public class ChugJugRitual extends Ritual {

    @Override
    public void setup() {
        addIngredient(Material.BLAZE_ROD, 1);
        addIngredient(Material.GLASS_BOTTLE, 1);
        addIngredient(Material.REDSTONE, 1);
        addIngredient(Material.GLOWSTONE_DUST, 1);
        addIngredient(Material.WHEAT, 64*3);
        addSacrifice(EntityType.WITCH, 1);
        health = 10;
        name = "chug jug";
        description = "Summon a chug-jug'ed witch to poison your enemy!";
    }

    @Override
    public void execute(Player caster, Player target, Location location) {
        LivingEntity witch = (LivingEntity) target.getWorld().spawnEntity(target.getLocation(), EntityType.WITCH);
        witch.setMaxHealth(100);
        witch.setHealth(100);
        witch.setCustomName("fortnite gaming");
        witch.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 100000, 4));
    }
    
}
