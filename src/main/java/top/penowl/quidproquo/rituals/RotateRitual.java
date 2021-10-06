package top.penowl.quidproquo.rituals;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import top.penowl.quidproquo.Ritual;

public class RotateRitual extends Ritual {

    @Override
    public void setup() {
        addIngredient(Material.COMPASS, 1);
        addIngredient(Material.STICK, 1);
        addIngredient(Material.IRON_BOOTS, 1);
        addIngredient(Material.WHEAT, 24);
        health = 4;
        backfire = 0.05;
        name = "rotation";
        notify = false;
        description = "Rotate your target by 180 degrees.";
    }

    @Override
    public void execute(Player caster, Player target, Location location) {
        Location targetLocation = target.getLocation();
        targetLocation.setDirection(targetLocation.getDirection().multiply(-1));
        target.teleport(targetLocation);
    }
    
}
