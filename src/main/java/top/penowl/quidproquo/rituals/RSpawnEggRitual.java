package top.penowl.quidproquo.rituals;

import java.util.Random;

import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.material.SpawnEgg;
import org.bukkit.Material;

import top.penowl.quidproquo.Ritual;

public class RSpawnEggRitual extends Ritual {

    private static final EntityType[] pEnt = {
        EntityType.BAT, EntityType.BLAZE, EntityType.CAVE_SPIDER, EntityType.CHICKEN,
        EntityType.COW, EntityType.CREEPER, EntityType.ENDERMAN, EntityType.ENDERMITE,
        EntityType.GHAST, EntityType.GUARDIAN, EntityType.HORSE, EntityType.MAGMA_CUBE,
        EntityType.MUSHROOM_COW, EntityType.OCELOT, EntityType.PIG, EntityType.PIG_ZOMBIE,
        EntityType.RABBIT, EntityType.SHEEP, EntityType.SILVERFISH, EntityType.SKELETON,
        EntityType.SLIME, EntityType.SPIDER, EntityType.SQUID, EntityType.VILLAGER, 
        EntityType.WITCH, EntityType.WOLF, EntityType.ZOMBIE
    };

    @Override
    public void setup() {
        addIngredient(Material.EGG, 16);
        addIngredient(Material.WHEAT, 64);
        name = "egging";
        health = 2;
        description = "Summon a random spawn egg.";
    }

    @Override
    public void execute(Player caster, Player target, Location location) {
        Random random = new Random();
        SpawnEgg sEgg = new SpawnEgg(pEnt[random.nextInt(pEnt.length)]);
        ItemStack egg = sEgg.toItemStack(1);
        location.getWorld().dropItemNaturally(location.clone().add(0, 1, 0), egg);
    }
    
}
