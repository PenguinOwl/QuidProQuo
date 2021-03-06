package top.penowl.quidproquo.rituals;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import top.penowl.quidproquo.Ritual;

public class KickingRitual extends Ritual {

    @Override
    public void setup() {
        addIngredient(Material.WHEAT, 32);
        addIngredient(Material.FLOWER_POT_ITEM, 1);
        addIngredient(Material.LOG, 1);
        addIngredient(Material.IRON_BOOTS, 1);
        health = 3;
        name = "kicking";
        backfire = 0.3;
        description = "ADMIN ABUSE!";
    }

    @Override
    public void execute(Player caster, Player target, Location location) {
        target.kickPlayer("You have been banned by " + caster.getName() + ".");
    }
    
}
