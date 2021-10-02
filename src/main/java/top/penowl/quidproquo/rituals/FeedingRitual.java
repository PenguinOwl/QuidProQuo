package top.penowl.quidproquo.rituals;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import top.penowl.quidproquo.Ritual;

public class FeedingRitual extends Ritual {

    @Override
    public void setup() {
        addIngredient(Material.WOOD_HOE, 1);
        addIngredient(Material.WHEAT, 16);
        health = 1;
        name = "feeding";
    }

    @Override
    public void execute(Player caster, Player target, Location location) {
        caster.setFoodLevel(20);
    }
    
}
