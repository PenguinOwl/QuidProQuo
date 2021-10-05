package top.penowl.quidproquo.rituals;

import org.apache.commons.lang.WordUtils;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import top.penowl.quidproquo.Ritual;

public class RandomItemRitual extends Ritual {

    @Override
    public void setup() {
        addIngredient(Material.DIRT, 6);
        addIngredient(Material.STONE, 5);
        addIngredient(Material.IRON_INGOT, 4);
        addIngredient(Material.GOLD_INGOT, 3);
        addIngredient(Material.REDSTONE, 2);
        addIngredient(Material.DIAMOND, 1);
        addIngredient(Material.WHEAT, 16);
        health = 2;
        name = "itemization";
        notify = false;
    }

    @Override
    public void execute(Player caster, Player target, Location location) {
        Material[] materials = Material.values();
        Material item = materials[(int)(materials.length*Math.random())];
        location.getWorld().dropItemNaturally(location.clone().add(0, 1, 0), new ItemStack(item, 8));
        caster.sendMessage(ChatColor.YELLOW + "You summoned " + WordUtils.capitalizeFully(item.toString().replace("_", " ")) + "!");
    }
    
}
