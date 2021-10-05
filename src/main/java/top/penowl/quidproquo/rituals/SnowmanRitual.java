package top.penowl.quidproquo.rituals;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

import top.penowl.quidproquo.Ritual;

public class SnowmanRitual extends Ritual {

    @Override
    public void setup() {
        addIngredient(Material.STICK, 64);
        addIngredient(Material.DIAMOND, 1);
        addSacrifice(EntityType.SHEEP, 1);
        addIngredient(Material.WHEAT, 32);
        health = 10;
        name = "snowing";
        description = "Cause a snowman invasion at the location of your enemy.";
    }

    @Override
    public void execute(Player caster, Player target, Location location) {
        for (int i = 0; i < 10; i++) {
            target.getLocation().getWorld().spawnEntity(target.getLocation(), EntityType.SNOWMAN);
        }
    }
    
}
