package top.penowl.quidproquo.rituals;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import top.penowl.quidproquo.Ritual;

public class PissingRitual extends Ritual {

    @Override
    public void setup() {
        addIngredient(Material.WATER_BUCKET, 1);
        addIngredient(Material.GOLD_LEGGINGS, 1);
        addIngredient(Material.STICK, 16);
        addIngredient(Material.WHEAT, 32);
        health = 2;
        name = "pissing";
        description = "Make your foe piss their pants.";
        backfire = 0.25;
    }

    @Override
    public void execute(Player caster, Player target, Location location) {
        target.getLocation().getBlock().setType(Material.STATIONARY_WATER);
        Bukkit.broadcastMessage(ChatColor.YELLOW + target.getName() + " pissed their pants!");
    }
    
}
