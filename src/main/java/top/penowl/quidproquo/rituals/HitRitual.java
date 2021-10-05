package top.penowl.quidproquo.rituals;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

import top.penowl.quidproquo.QuidProQuo;
import top.penowl.quidproquo.Ritual;

public class HitRitual extends Ritual {
    @Override
    public void setup() {
       addIngredient(Material.IRON_SWORD, 1);
       addIngredient(Material.IRON_INGOT, 1);
       addIngredient(Material.WHEAT, 9);
       name = "shadow hit";
       health = 1;
       notify = false;
       description = "Play a fake hit noise to someone.";
    }

    @Override
    public void execute(Player caster, Player target, Location location) {
        target.playSound(target.getLocation(), Sound.HURT_FLESH, 1, 1);
        target.damage(1);
        target.setVelocity(target.getVelocity().add(target.getLocation().getDirection().multiply(1)));
        Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(QuidProQuo.instance, new Runnable() {
            public void run() {
                target.playSound(target.getLocation(), Sound.HURT_FLESH, 0.8f, 1.1f);
                target.setHealth(target.getHealth()+2);
                target.damage(0.01);
            }
        }, 1 * 15L);
        Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(QuidProQuo.instance, new Runnable() {
            public void run() {
                target.playSound(target.getLocation(), Sound.HURT_FLESH, 1.2f, 0.9f);
                target.damage(1);
            }
        }, 2 * 15L);
    }

}
