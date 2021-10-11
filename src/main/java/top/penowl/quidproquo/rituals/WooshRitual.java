package top.penowl.quidproquo.rituals;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import top.penowl.quidproquo.Ritual;

public class WooshRitual extends Ritual {

    @Override
    public void setup() {
        addIngredient(Material.WORKBENCH, 1);
        addIngredient(Material.WHEAT, 8);
        addIngredient(Material.SEEDS, 1);
        addIngredient(Material.STICK, 1);
        name = "wooshing";
        description = "Teleport yourself to highest block.";
        health = 1;
        notify = false;
    }

    @Override
    public void execute(Player caster, Player target, Location location) {
        int y = 255;
        while (location.getBlock().getRelative(0, y, 0).getType() != Material.AIR) {
            y--;
        }
        caster.teleport(location.getBlock().getRelative(0, y + 1, 0).getLocation());
    }
    
}
