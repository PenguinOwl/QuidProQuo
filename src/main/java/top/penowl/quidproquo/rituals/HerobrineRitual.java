package top.penowl.quidproquo.rituals;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.Material;
import org.bukkit.World;
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
        double targX = targetLocation.getX();
        double targY = targetLocation.getY();
        double targZ = targetLocation.getZ();
        World world = targetLocation.getWorld();
        boolean foundSpawnLoc = false;
        Location spawnLoc;
        for(int direction = 0; direction < 16; direction ++) {
            double rads = Math.toRadians(direction * 24);
            double testX = targX + 20 * Math.sin(rads);
            double testZ = targY + 20 * Math.cos(rads);
            if((new Location(world, testX, targY, testZ).getBlock().getType() == Material.AIR) && 
                (new Location(world, testX, targY + 1, testZ).getBlock().getType() == Material.AIR)) {
                    spawnLoc = new Location(world, testX, targY, testZ);
                    foundSpawnLoc = true;
                    world.spawnEntity(spawnLoc, EntityType.ZOMBIE);
                    break;
            }
        }
        if(foundSpawnLoc == false)
            world.spawnEntity(new Location(world, targX, targY, targZ), EntityType.ZOMBIE);
    }
    
}
