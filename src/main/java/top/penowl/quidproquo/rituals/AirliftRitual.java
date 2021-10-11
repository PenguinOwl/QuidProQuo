package top.penowl.quidproquo.rituals;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

import top.penowl.quidproquo.Ritual;

@SuppressWarnings("deprecation")
public class AirliftRitual extends Ritual {

    @Override
    public void setup() {
        addIngredient(Material.PAPER, 1);
        addIngredient(Material.IRON_SPADE, 1);
        addIngredient(Material.FEATHER, 1);
        addIngredient(Material.ARROW, 1);
        addIngredient(Material.DIRT, 1);
        addIngredient(Material.STONE, 1);
        addIngredient(Material.SAPLING, 1);
        addSacrifice(EntityType.CHICKEN, 1);
        addIngredient(Material.WHEAT, 64*2);
        name = "raising";
        description = "Raises the chunk.";
        health = 3;
        notify = false;
    }

    @Override
    public void execute(Player caster, Player target, Location location) {
        Block block = location.getBlock().getRelative(-8, -12, -8);
        for (int y = 200; y >= 0; y--) {
            for (int x = 0; x < 16; x++) {
                for (int z = 0; z < 16; z++) {
                    Block targetBlock = block.getRelative(x, y, z);
                    Block toBlock = targetBlock.getRelative(0, 20, 0);
                    toBlock.setType(targetBlock.getType());
                    toBlock.setData(targetBlock.getData());
                    targetBlock.setType(Material.AIR);
                }
            }
        }
        caster.teleport(caster.getLocation().clone().add(0, 20, 0));
        location.add(0, 20, 0);
    }
    
}
