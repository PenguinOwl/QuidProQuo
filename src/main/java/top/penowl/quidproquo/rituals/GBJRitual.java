package top.penowl.quidproquo.rituals;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

import top.penowl.quidproquo.Ritual;

public class GBJRitual extends Ritual {

    @Override
    public void setup() {
        addIngredient(Material.IRON_INGOT, 6);
        addIngredient(Material.STONE, 1);
        addIngredient(Material.WHEAT, 272);
        name = "caging";
        health = 5;
        backfire = 0.2;
        description = "Trap your foe in the wagie cage!";
    }

    @Override
    public void execute(Player caster, Player target, Location location) {
        Location targetLocation = target.getLocation();
        fillBlocks(targetLocation.clone().add(-1, -1, -1), 3, 1, 3, Material.OBSIDIAN);
        fillEmptyBlocks(targetLocation.clone().add(-1, 3, -1), 3, 1, 3, Material.OBSIDIAN);
        fillEmptyBlocks(targetLocation.clone().add(-1, 0, -1), 3, 3, 3, Material.IRON_FENCE);
        fillBlocks(targetLocation, 1, 3, 1, Material.AIR);
    }

    public void fillEmptyBlocks(Location location, int offsetx, int offsety, int offsetz, Material material) {
        Block block = location.getBlock();
        for (int x = 0; x < offsetx; x++ ) {
            for (int y = 0; y < offsety; y++ ) {
                for (int z = 0; z < offsetz; z++ ) {
                    Block targetBlock = block.getRelative(x, y, z);
                    if (targetBlock.getType() == Material.AIR) {
                        targetBlock.setType(material);
                    }
                }
            }
        }
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
