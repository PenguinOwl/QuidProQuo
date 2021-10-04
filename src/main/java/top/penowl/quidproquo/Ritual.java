package top.penowl.quidproquo;

import java.util.ArrayList;
import java.util.HashMap;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public abstract class Ritual {

    // ingredients, map of type to count
    public HashMap<Material, Integer> ingredients = new HashMap<Material, Integer>();

    // mob sacrifices, map of type to count
    public HashMap<EntityType, Integer> sacrifices = new HashMap<EntityType, Integer>();
    
    // extra byproducts, prob shouldn't need to be used
    public ArrayList<ItemStack> byproducts = new ArrayList<ItemStack>();

    // the amount of heath that is drained from doing the sacrifice
    public int health = 0;

    // chance of it backfireing and swapping the target and caster, ranges from 0.0 to 1.0, should be 0.0 for rituals with no target
    public double backfire = 0.0;

    // name of ritual, should be a gerund
    public String name = "unnamed";

    // whether you want a lightning effect
    public Boolean lightning = false;

    // whether the target is notified
    public Boolean notify = true;

    // the actual effect that gets triggered
    public abstract void execute(Player caster, Player target, Location location);

    public void handleByproducts(ArrayList<ItemStack> extra) {}

    // add an ingredient to the recipe (type and count)
    public void addIngredient(Material material, int count) {
        ingredients.put(material, count);
    }

    // add a sacrifice to the recipe (type and count)
    public void addSacrifice(EntityType type, int count) {
        sacrifices.put(type, count);
    }

    // this is where you set up the recipe, e.g. ingredients, sacrifices, lightning, health, name, etc
    public abstract void setup();

}