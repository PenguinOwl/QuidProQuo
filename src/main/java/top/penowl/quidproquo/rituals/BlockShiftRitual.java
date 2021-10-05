package top.penowl.quidproquo.rituals;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import java.util.Random;

import top.penowl.quidproquo.Ritual;

public class BlockShiftRitual extends Ritual {
    //possible mats
    private static final Material[] pMat = {
        Material.STONE, Material.GRASS, Material.DIRT, Material.COBBLESTONE, Material.WOOD,
        Material.WATER, Material.LAVA, Material.SAND, Material.GRAVEL, Material.GOLD_ORE,
        Material.IRON_ORE, Material.COAL_ORE, Material.LOG, Material.LEAVES, Material.SPONGE,
        Material.GLASS, Material.LAPIS_ORE, Material.LAPIS_BLOCK, Material.SANDSTONE,
        Material.WEB, Material.WOOL, Material.GOLD_BLOCK, Material.IRON_BLOCK, Material.BRICK,
        Material.TNT, Material.BOOKSHELF, Material.MOSSY_COBBLESTONE, Material.OBSIDIAN, 
        Material.DIAMOND_ORE, Material.DIAMOND_BLOCK, Material.REDSTONE_ORE, Material.ICE,
        Material.SNOW_BLOCK, Material.CLAY, Material.PUMPKIN, Material.NETHERRACK,
        Material.SOUL_SAND, Material.GLOWSTONE, Material.JACK_O_LANTERN, Material.STAINED_GLASS,
        Material.MONSTER_EGGS, Material.SMOOTH_BRICK, Material.HUGE_MUSHROOM_1,
        Material.HUGE_MUSHROOM_2, Material.MELON_BLOCK, Material.MYCEL, Material.NETHER_BRICK,
        Material.ENDER_STONE, Material.EMERALD_ORE, Material.EMERALD_BLOCK, Material.REDSTONE_BLOCK,
        Material.QUARTZ_ORE, Material.QUARTZ_BLOCK, Material.STAINED_CLAY, Material.LEAVES,
        Material.LOG, Material.SLIME_BLOCK, Material.PRISMARINE, Material.SEA_LANTERN,
        Material.HAY_BLOCK, Material.CLAY_BRICK, Material.COAL_BLOCK, Material.PACKED_ICE,
        Material.RED_SANDSTONE, Material.AIR
    };
    @Override
    public void setup() {
        addIngredient(Material.RED_MUSHROOM, 8);
        addIngredient(Material.BROWN_MUSHROOM, 8);
        addIngredient(Material.WHEAT, 32);
        addIngredient(Material.ENDER_PEARL, 1);
        name = "block shift";
        health = 3;
        description = "Shift reality around you.";
    }

    @Override
    public void execute(Player caster, Player target, Location location) {
        Random random = new Random();
        Material fromMat = pMat[random.nextInt(pMat.length)];
        Material toMat = pMat[random.nextInt(pMat.length)];
        Material chosenMat;
        if (Math.random() > 0.5) {
            chosenMat = fromMat;
        } else {
            chosenMat = toMat;
        }
        target.sendMessage(ChatColor.YELLOW + "You sense that reality has shifted. You see " + chosenMat.toString().replace("_", " ") + " in glowing letters.");
        Block block = target.getLocation().getBlock();
        for(int x = -12; x <= 12; x ++) {
            for(int y = -12; y <= 12; y ++) {
                for(int z = -12; z <= 12; z ++) {
                    Block testBlock = block.getRelative(x, y, z);
                    if(testBlock.getType() == fromMat)
                        testBlock.setType(toMat);
                }
            }
        }
    }
    
}