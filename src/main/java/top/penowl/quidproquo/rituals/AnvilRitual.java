package top.penowl.quidproquo.rituals;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.FallingBlock;
import org.bukkit.entity.Player;

import top.penowl.quidproquo.Ritual;

@SuppressWarnings("deprecation")
public class AnvilRitual extends Ritual {

    @Override
    public void setup() {
        addIngredient(Material.ANVIL, 1);
        addIngredient(Material.ARROW, 1);
        addIngredient(Material.ROTTEN_FLESH, 1);
        addIngredient(Material.WHEAT, 32);
        name = "anviling";
        health = 4;
        backfire = 0.3;
        notify = false;
        description = "Drop an anvil on your enemy.";
    }

    @Override
    public void execute(Player caster, Player target, Location location) {
        FallingBlock block = target.getWorld().spawnFallingBlock(target.getLocation().clone().add(0, 20, 0), Material.ANVIL, (byte) 0);
        block.setHurtEntities(true);
    }
    
}
