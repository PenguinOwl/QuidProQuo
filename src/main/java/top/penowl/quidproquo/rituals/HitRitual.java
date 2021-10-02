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
<<<<<<< HEAD
       addIngredient(Material.IRON_INGOT, 1);
=======
>>>>>>> 7f77c0dac5cb9bbd0ceaf70f710f7fdecac3e9ad
       addIngredient(Material.WHEAT, 9);
       name = "shadow hit";
       health = 1;
    }

    @Override
    public void execute(Player caster, Player target, Location location) {
<<<<<<< HEAD
        target.playSound(target.getLocation(), Sound.HURT_FLESH, 1, 1);
        //target.damage(1.0);
=======
        for(int i = 0; i < 3; i ++) {
            target.playSound(target.getLocation(), Sound.SUCCESSFUL_HIT, 1, 1);
        }
>>>>>>> 7f77c0dac5cb9bbd0ceaf70f710f7fdecac3e9ad
    }

}
