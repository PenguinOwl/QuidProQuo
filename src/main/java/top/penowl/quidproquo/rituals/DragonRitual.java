package top.penowl.quidproquo.rituals;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

import top.penowl.quidproquo.Ritual;

public class DragonRitual extends Ritual {

    @Override
    public void setup() {
        addIngredient(Material.ENDER_PEARL, 16);
        addIngredient(Material.WHEAT, 64*9*2);
        addSacrifice(EntityType.CHICKEN, 2);
        addSacrifice(EntityType.ENDERMAN, 1);
        addIngredient(Material.EGG, 64);
        addIngredient(Material.STONE, 64);
        addIngredient(Material.BOOK, 4);
        addIngredient(Material.DIAMOND_BLOCK, 1);
        name = "dragon summoning";
        health = 3;
    }

    @Override
    public void execute(Player caster, Player target, Location location) {
        location.getWorld().spawnEntity(location.clone().add(0, 1, 0), EntityType.ENDER_DRAGON);
    }
    
}
