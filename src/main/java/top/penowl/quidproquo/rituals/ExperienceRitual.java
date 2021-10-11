package top.penowl.quidproquo.rituals;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import top.penowl.quidproquo.Ritual;

public class ExperienceRitual extends Ritual {

    @Override
    public void setup() {
        addIngredient(Material.REDSTONE_BLOCK, 5);
        addIngredient(Material.WHEAT, 16);
        addIngredient(Material.GLASS_BOTTLE, 1);
        health = 1;
        name = "experience";
        description = "Experience the easy way.";
        notify = false;
    }

    @Override
    public void execute(Player caster, Player target, Location location) {
        target.giveExp(250);
        caster.giveExp(250);
    }
    
}
