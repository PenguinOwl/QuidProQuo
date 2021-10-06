package top.penowl.quidproquo.rituals;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

import top.penowl.quidproquo.Ritual;

public class DiggyRitual extends Ritual {

    @Override
    public void setup() {
        addIngredient(Material.OBSIDIAN, 1);
        addIngredient(Material.WHEAT, 64);
        addIngredient(Material.IRON_PICKAXE, 1);
        addIngredient(Material.GRAVEL, 1);
        description = "Digs a hole to bedrock and then some.";
        name = "diggy";
        health = 2;
    }

    @Override
    public void execute(Player caster, Player target, Location location) {
        Location bedrock = location.clone();
        int dist = bedrock.getBlockY();
        bedrock.setY(0);
        fillBlocks(bedrock, 1, dist + 1, 1, Material.AIR);
    }

    public void fillBlocks(Location location, int offsetx, int offsety, int offsetz, Material material) {
        Block block = location.getBlock();
        for (int x = 0; x < offsetx; x++ ) {
            for (int y = 0; y < offsety; y++ ) {
                for (int z = 0; z < offsetz; z++ ) {
                    Block targetBlock = block.getRelative(x, y, z);
                    targetBlock.setType(material);
                }
            }
        }
    }

}
