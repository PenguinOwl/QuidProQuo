package top.penowl.quidproquo.rituals;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import top.penowl.quidproquo.Ritual;

public class LavaRitual extends Ritual {
    @Override
    public void setup() {
       addIngredient(Material.LAVA_BUCKET, 1);
       addIngredient(Material.DIAMOND, 1);
       addIngredient(Material.WHEAT, 64);
       backfire = 0.10;
       name = "combustion";
       description = "Light your enemy on fire for a short period.";
    }

    @Override
    public void execute(Player caster, Player target, Location location) {
        target.setFireTicks(250);
    }

}
