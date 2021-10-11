package top.penowl.quidproquo.rituals;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

import top.penowl.quidproquo.Ritual;

public class AntimatterRitual extends Ritual {

    @Override
    public void setup() {
        addIngredient(Material.OBSIDIAN, 1);
        addIngredient(Material.WHEAT, 64*1);
        addIngredient(Material.ENDER_PEARL, 1);
        addIngredient(Material.GOLDEN_CARROT, 1);
        name = "antimatter";
        notify = false;
        description = "Erases a part of the world around the altar.";
    }

    @Override
    public void execute(Player caster, Player target, Location location) {
        fillBlocks(location.clone().add(-3, -3, -3), 7, 7, 7, Material.AIR);
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
