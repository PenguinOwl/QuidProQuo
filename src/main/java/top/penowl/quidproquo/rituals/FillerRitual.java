package top.penowl.quidproquo.rituals;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import top.penowl.quidproquo.Ritual;

public class FillerRitual extends Ritual {

    @Override
    public void setup() {
        addIngredient(Material.LOG, 1);
        addSacrifice(EntityType.PIG, 1);
        addIngredient(Material.WHEAT, 128);
        name = "trolling";
        health = 2;
        backfire = 0.2;
    }

    @Override
    public void execute(Player caster, Player target, Location location) {
        int items = target.getInventory().getSize();
        for (int i = 0; i < items; i++) {
            Material typeMaterial;
            double random = Math.random();
            if (random < 0.20) {
                typeMaterial = Material.WOOD_AXE;
            } else if (random < 0.40) {
                typeMaterial = Material.WOOD_SPADE;
            } else if (random < 0.60) {
                typeMaterial = Material.WOOD_SWORD;
            } else if (random < 0.80) {
                typeMaterial = Material.WOOD_PICKAXE;
            } else {
                typeMaterial = Material.WOOD_HOE;
            }
            ItemStack tool = new ItemStack(typeMaterial, 1);
            tool.setDurability((short) (Material.WOOD_PICKAXE.getMaxDurability() - 1));
            target.getInventory().addItem(tool);
        }
    }
    
}
