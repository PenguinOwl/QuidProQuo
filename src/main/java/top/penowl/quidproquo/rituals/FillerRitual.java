package top.penowl.quidproquo.rituals;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import java.util.Random;

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
        description = "Fill your foe's inventory with junk!";
    }

    @Override
    public void execute(Player caster, Player target, Location location) {
        int items = target.getInventory().getSize();
        for (int i = 0; i < items; i++) {
            Material typeMaterial;
            int random = new Random().nextInt(5);
            switch(random) {
                case 0:
                    typeMaterial = Material.WOOD_AXE;
                    break;
                case 1:
                    typeMaterial = Material.WOOD_SPADE;
                    break;
                case 2:
                    typeMaterial = Material.WOOD_SWORD;
                    break;
                case 3:
                    typeMaterial = Material.WOOD_PICKAXE;
                default:
                    typeMaterial=Material.WOOD_HOE;
                    break;
            }
            ItemStack tool = new ItemStack(typeMaterial, 1);
            tool.setDurability((short) (Material.WOOD_PICKAXE.getMaxDurability() - 1));
            target.getInventory().addItem(tool);
        }
    }
    
}
