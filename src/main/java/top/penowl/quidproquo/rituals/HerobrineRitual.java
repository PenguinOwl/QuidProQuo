package top.penowl.quidproquo.rituals;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.EntityType;

import top.penowl.quidproquo.Ritual;

public class HerobrineRitual extends Ritual {

    @Override
    public void setup() {
        addIngredient(Material.WHEAT, 64);
        addIngredient(Material.DIAMOND, 2);
        health = 10;
        name = "herobrine";
        backfire = 0.5;
    }
    @Override
    public void execute(Player caster, Player target, Location location) {
        Location targetLocation = target.getLocation();
        Block block = targetLocation.clone().add(0, 1, 0).getBlock();
        for (int x = -5; x <= 5; x++) {
            for (int z = -5; z <= 5; z++) {
                if(block.getRelative(x, 0, z).getType() == Material.AIR) {
                    targetLocation.getWorld().spawnEntity(targetLocation.clone().add(x, 1, z), EntityType.ZOMBIE);
                    return;
                }
            }
        }
    }
    
}
