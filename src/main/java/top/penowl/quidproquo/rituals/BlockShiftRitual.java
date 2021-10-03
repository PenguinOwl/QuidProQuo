package top.penowl.quidproquo.rituals;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import top.penowl.quidproquo.Ritual;

public class BlockShiftRitual extends Ritual {
    //possible mats
    private static final Material[] pMat = {
        Material.STONE, Material.GRASS, Material.DIRT, Material.COBBLESTONE, Material.PLANKS,
        Material.WATER, Material.LAVA, Material.SAND
    };
    @Override
    public void setup() {
        addIngredient(Material.RED_MUSHROOM, 32);
        addIngredient(Material.BROWN_MUSHROOM, 32);
        addIngredient(Material.WHEAT, 32);
        addIngredient(Material.ENDER_PEARL, 4);
        name = "block shift";
    }

    @Override
    public void execute(Player caster, Player target, Location location) {
    }
    
}
