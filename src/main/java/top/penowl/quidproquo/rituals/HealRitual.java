package top.penowl.quidproquo.rituals;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import top.penowl.quidproquo.Ritual;

public class HealRitual extends Ritual {

    @Override
    public void setup() {
        addIngredient(Material.REDSTONE, 1);
        addIngredient(Material.WHEAT, 16);
        addIngredient(Material.GOLD_NUGGET, 1);
        name = "healing";
    }

    @Override
    public void execute(Player caster, Player target, Location location) {
        caster.setHealth(Math.min(caster.getMaxHealth(), caster.getHealth()+8.0));
    }
    
}
