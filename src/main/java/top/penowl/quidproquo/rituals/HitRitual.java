package top.penowl.quidproquo.rituals;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

import top.penowl.quidproquo.Ritual;

public class HitRitual extends Ritual {
    @Override
    public void setup() {
       addIngredient(Material.IRON_SWORD, 1);
       addIngredient(Material.HAY_BLOCK, 1);
       name = "shadow hit";
       health = 2;
    }

    @Override
    public void execute(Player caster, Player target, Location location) {
        target.playSound(target.getLocation(), Sound.SUCCESSFUL_HIT, 1, 1);
    }

}