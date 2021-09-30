package top.penowl.quidproquo;

import java.util.HashMap;

import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

public abstract class Ritual {
    public HashMap<Material, Integer> ingredients = new HashMap<Material, Integer>();
    public HashMap<EntityType, Integer> sacrifices = new HashMap<EntityType, Integer>();
    public abstract void execute(Player caster, Player target);
    public void addIngredient(Material material, int count) {
        ingredients.put(material, count);
    }
    public void addSacrifice(EntityType type, int count) {
        sacrifices.put(type, count);
    }
    public abstract void setup();
}