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
        addIngredient(Material.DIAMOND, 2);
        addSacrifice(EntityType.SHEEP, 3);
        health = 10;
        name = "snowing";
    }

    @Override
    public void execute(Player caster, Player target, Location location) {
        for (int i = 0; i < 10; i++) {
            target.getLocation().getWorld().spawnEntity(target.getLocation(), EntityType.SNOWMAN);
        }
    }
    
}