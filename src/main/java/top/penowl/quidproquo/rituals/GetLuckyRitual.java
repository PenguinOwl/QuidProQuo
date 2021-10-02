package top.penowl.quidproquo.rituals;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import top.penowl.quidproquo.Ritual;

public class GetLuckyRitual extends Ritual {

    @Override
    public void setup() {
        addIngredient(Material.GOLD_BLOCK, 3);
        addIngredient(Material.TNT, 1);
        health = 3;
        lightning = true;
        name = "daring";
    }

    @Override
    public void execute(Player caster, Player target, Location location) {
        if (Math.random() < 0.5) {
            location.getWorld().createExplosion(location, 100F);
        } else { 
            byproducts.add(new ItemStack(Material.GOLD_BLOCK, 16));
        }
    }
    
}