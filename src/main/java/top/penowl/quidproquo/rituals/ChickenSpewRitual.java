package top.penowl.quidproquo.rituals;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

import top.penowl.quidproquo.Ritual;

public class ChickenSpewRitual extends Ritual {

    @Override
    public void setup() {
        addIngredient(Material.WHEAT, 64);
        addIngredient(Material.DIAMOND_HOE, 1);
        health = 3;
        name = "chicken spew";
        backfire = 0.1;
    }

    @Override
    public void execute(Player caster, Player target, Location location) {
        for (int i = 0; i < 20; i++) {
            target.getWorld().spawnEntity(target.getLocation(), EntityType.CHICKEN);
        }
    }
    
}
