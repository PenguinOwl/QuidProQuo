package top.penowl.quidproquo.rituals;

import java.util.Set;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

import top.penowl.quidproquo.Ritual;

public class MidasRitual extends Ritual {

    @Override
    public void setup() {
        addIngredient(Material.GOLDEN_CARROT, 1);
        addIngredient(Material.GOLD_BLOCK, 1);
        addIngredient(Material.GOLDEN_APPLE, 1);
        addIngredient(Material.GLASS_BOTTLE, 1);
        addIngredient(Material.WHEAT, 16);
        name = "midas";
        health = 5;
        description = "Turn the block your enemy is looking at to gold.";
    }

    @Override
    public void execute(Player caster, Player target, Location location) {
        Block block = target.getTargetBlock((Set<Material>) null, 7);
        if (block != null) {
            block.setType(Material.GOLD_BLOCK);
        }
    }
    
}
