package top.penowl.quidproquo.rituals;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import top.penowl.quidproquo.Ritual;

public class RandomItemRitual extends Ritual {

    @Override
    public void setup() {
        addIngredient(Material.DIRT, 6);
        addIngredient(Material.STONE, 5);
        addIngredient(Material.IRON_INGOT, 4);
        addIngredient(Material.GOLD_INGOT, 3;
        addIngredient(Material.REDSTONE, 2);
        addIngredient(Material.DIAMOND, 1);
        addIngredient(Material.WHEAT, 16);
        health = 2;
        name = "itemization";
    }

    @Override
    public void execute(Player caster, Player target, Location location) {
        Random r = new Random();
        int i = r.nextInt(440);
        while(Material.getValue(i) == null){
            i = r.nextInt(440);
        }
        location.getWorld().dropItemNaturally(location.clone().add(0, 1, 0), ItemStack(Material.getValue(i));
    }
    
}
