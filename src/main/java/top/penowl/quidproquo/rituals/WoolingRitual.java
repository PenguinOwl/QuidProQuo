package top.penowl.quidproquo.rituals;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import top.penowl.quidproquo.Ritual;

public class WoolingRitual extends Ritual {

    @Override
    public void setup() {
        addIngredient(Material.IRON_SWORD, 1);
        addSacrifice(EntityType.SHEEP, 1);
        addIngredient(Material.WHEAT, 8);
        name = "wooling";
        health = 2;
        byproducts.add(new ItemStack(Material.WOOL, 64));
        notify = false;
        description = "Summons a ton of wool.";
    }

    @Override
    public void execute(Player caster, Player target, Location location) {
    }
    
}
