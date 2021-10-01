package top.penowl.quidproquo;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;

public class Altar {

    // this thing just makes sure the multiblock is built right, doesn't check for air right now
    public static Boolean CheckAltar(Location location) {
        Boolean gold_block = location.getBlock().getType() == Material.GOLD_BLOCK;
        int iron_blocks = 0;
        for (int x = -1; x < 2; x++) {
            for (int z = -1; z < 2; z++) {
                Location ironLocation = location.clone().add(x, -1, z);
                if (ironLocation.getBlock().getType() == Material.IRON_BLOCK) {
                    iron_blocks++;
                }
            }
        }
        return (gold_block && iron_blocks == 9);
    }
}
