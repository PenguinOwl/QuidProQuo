package top.penowl.quidproquo.rituals;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

import top.penowl.quidproquo.Ritual;

public class ChunkBegoneRitual extends Ritual {

    @Override
    public void setup() {
        addIngredient(Material.OBSIDIAN, 64);
        addIngredient(Material.WHEAT, 64*18);
        addIngredient(Material.ENDER_PEARL, 16);
        addIngredient(Material.GOLDEN_APPLE, 1);
        name = "chunk begone";
        notify = false;
        description = "Deletes a chunk. You want to stand on the gold block for this one.";
    }

    @Override
    public void execute(Player caster, Player target, Location location) {
        Location bedrock = location.clone();
        int dist = bedrock.getBlockY();
        bedrock.setY(0);
        fillBlocks(bedrock.add(-8, 0, -8), 16, dist, 16, Material.AIR);
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
