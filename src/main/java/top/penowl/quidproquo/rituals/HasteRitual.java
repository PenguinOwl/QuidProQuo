package top.penowl.quidproquo.rituals;

import java.util.Random;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import top.penowl.quidproquo.Ritual;

public class HasteRitual extends Ritual {

    @Override
    public void setup() {
        addIngredient(Material.DIAMOND_PICKAXE,1);
        addIngredient(Material.WHEAT, 96);
        addIngredient(Material.REDSTONE, 16);
        health = 8;
        name = "minering";
        description = "Give the target extreme haste or fatigue";
    }

    @Override
    public void execute(Player caster, Player target, Location location) {
        Random random = new Random();
        if(random.nextInt(10) != 0) {
            target.addPotionEffect(new PotionEffect(PotionEffectType.FAST_DIGGING, 45 * 20, 85));
        } else target.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_DIGGING, 45 * 20, 50));
    }
    
}