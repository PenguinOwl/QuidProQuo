package top.penowl.quidproquo.rituals;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

import top.penowl.quidproquo.Ritual;

public class CrystalizationRtiual extends Ritual {

    @Override
    public void setup() {
        addIngredient(Material.ENDER_PEARL, 1);
        addIngredient(Material.GLASS, 1);
        addIngredient(Material.TNT, 1);
        addIngredient(Material.WHEAT, 32);
        name = "crystalization";
        health = 2;
        notify = false;
        description = "Combine ingredients into a ender crystal.";
    }

    @Override
    public void execute(Player caster, Player target, Location location) {
        location.getWorld().spawnEntity(location.clone().add(0, 1, 0), EntityType.ENDER_CRYSTAL);
    }
    
}
