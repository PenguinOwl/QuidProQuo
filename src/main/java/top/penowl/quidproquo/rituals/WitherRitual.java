package top.penowl.quidproquo.rituals;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

import top.penowl.quidproquo.Ritual;

public class WitherRitual extends Ritual {

    @Override
    public void setup() {
        addIngredient(Material.BONE, 16);
        addIngredient(Material.WHEAT, 64*10);
        addSacrifice(EntityType.SKELETON, 1);
        addIngredient(Material.FLOWER_POT_ITEM, 1);
        addIngredient(Material.SAND, 1);
        name = "wither summoning";
        health = 2;
        notify = false;
        lightning = true;
        description = "Summons a wither.";
    }

    @Override
    public void execute(Player caster, Player target, Location location) {
        location.getWorld().spawnEntity(location.clone().add(0, 1, 0), EntityType.WITHER);
    }
    
}
