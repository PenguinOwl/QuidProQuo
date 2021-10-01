package top.penowl.quidproquo;

import java.util.ArrayList;
import java.util.HashMap;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public abstract class Ritual {
    public HashMap<Material, Integer> ingredients = new HashMap<Material, Integer>();
    public HashMap<EntityType, Integer> sacrifices = new HashMap<EntityType, Integer>();
    public ArrayList<ItemStack> byproducts = new ArrayList<ItemStack>();
    public int health = 0;
    public double backfire = 0.0;
    public String name = "unnamed";

    public abstract void execute(Player caster, Player target, Location location);

    public void addIngredient(Material material, int count) {
        ingredients.put(material, count);
    }

    public void addSacrifice(EntityType type, int count) {
        sacrifices.put(type, count);
    }

    public abstract void setup();
}