package top.penowl.quidproquo.rituals;
//TODO MAKE A DIRT PENOR THAT CAN SPAWN AROUND OTHERS

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import top.penowl.quidproquo.Ritual;

public class SoftPenorRitual extends Ritual {

    @Override
    public void setup() {
        addIngredient(Material.DIRT, 500);
        addIngredient(Material.BONE, 20);
        addIngredient(Material.REDSTONE, 15);
        addIngredient(Material.WHEAT, 15);
        backfire = 0.10;
        name = "disfunction";
    }

    @Override
    public void execute(Player caster, Player target, Location location) {
        Location loc = target.getLocation();
    }
    
}
