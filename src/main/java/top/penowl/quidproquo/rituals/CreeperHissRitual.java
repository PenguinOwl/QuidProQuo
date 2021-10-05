package top.penowl.quidproquo.rituals;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

import top.penowl.quidproquo.Ritual;

public class CreeperHissRitual extends Ritual {

    @Override
    public void setup() {
        addIngredient(Material.SULPHUR, 1);
        addIngredient(Material.REDSTONE, 1);
        addIngredient(Material.WHEAT, 16);
        name = "creeper hissing";
        health = 1;
        notify = false;
        description = "Play a creeper hiss to your enemy.";
    }

    @Override
    public void execute(Player caster, Player target, Location location) {
        target.playSound(target.getLocation(), Sound.CREEPER_HISS, 1, 1);
    }
    
}
