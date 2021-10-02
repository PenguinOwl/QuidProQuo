package top.penowl.quidproquo.rituals;
//TODO MAKE COBBLESTONE PENOR THAT ONLY SPAWNS AROUND CASTER

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import top.penowl.quidproquo.Ritual;

public class HardPenorRitual extends Ritual {

    @Override
    public void setup() {
        addIngredient(Material.COBBLESTONE, 500);
        addIngredient(Material.BONE, 10);
        name = "erection";
    }

    @Override
    public void execute(Player caster, Player target, Location location) {

    }
    
}
