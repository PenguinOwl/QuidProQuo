package top.penowl.quidproquo.rituals;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

import top.penowl.quidproquo.Ritual;

public class FeedingRitual extends Ritual {

    @Override
    public void setup() {
        addSacrifice(EntityType.PIG, 1);
        addIngredient(Material.WOOD_SWORD, 1);
        health = 4;
        name = "feeding";
    }

    @Override
    public void execute(Player caster, Player target, Location location) {
        caster.setFoodLevel(20);
    }
    
}
