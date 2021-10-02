package top.penowl.quidproquo.rituals;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import top.penowl.quidproquo.Ritual;

public class Lava extends Ritual {
    @Override
    public void setup() {
       addIngredient(Material.LAVA_BUCKET, 5);
       addIngredient(Material.DIAMOND, 1);
       backfire = 0.50;
       name = "combustion";
    }

    @Override
    public void execute(Player caster, Player target, Location location) {
        target.setFireTicks(60);
    }

}
