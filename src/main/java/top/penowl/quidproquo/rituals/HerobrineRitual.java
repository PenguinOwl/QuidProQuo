package top.penowl.quidproquo.rituals;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.inventory.EntityEquipment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;

import top.penowl.quidproquo.Ritual;

public class HerobrineRitual extends Ritual {
    final static int LONGTIME = 10000000;

    @Override
    public void setup() {
        addIngredient(Material.WHEAT, 64*9);
        addIngredient(Material.DIAMOND, 9);
        addIngredient(Material.ROTTEN_FLESH, 64);
        addIngredient(Material.REDSTONE_BLOCK, 9);
        addIngredient(Material.SUGAR, 32);
        addSacrifice(EntityType.ZOMBIE, 3);
        health = 10;
        name = "herobrine";
        backfire = 0.1;
    }
    @Override
    public void execute(Player caster, Player target, Location location) {
        Entity herobrine;
        Location targetLocation = target.getLocation();
        Block block = targetLocation.clone().add(0, 1, 0).getBlock();
        for (int x = -5; x <= 5; x++) {
            for (int z = -5; z <= 5; z++) {
                Material footblock = block.getRelative(x, 0, z).getType();
                Material headblock = block.getRelative(x, 1, z).getType();
                if(( (footblock == Material.AIR) || (footblock == Material.WATER) ) &&  
                ( (headblock == Material.AIR) || (footblock == Material.WATER) )) {
                    herobrine = targetLocation.getWorld().spawnEntity(targetLocation.clone().add(x, 0, z), EntityType.ZOMBIE);
                    brine(herobrine);
                    return;
                }
            }
        }
        herobrine = targetLocation.getWorld().spawnEntity(targetLocation, EntityType.ZOMBIE);
        brine(herobrine);
    }
    private static void brine(Entity herobrine) {
        LivingEntity heroBine = (LivingEntity)herobrine;
        heroBine.setCustomName("Herobrine");
        heroBine.setMaxHealth(40.0);
        heroBine.setHealth(40.0);
        PotionEffect resistance = new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, LONGTIME, 2);
        PotionEffect fireResistance = new PotionEffect(PotionEffectType.FIRE_RESISTANCE, LONGTIME, 1);
        PotionEffect speed = new PotionEffect(PotionEffectType.SPEED, LONGTIME, 1);
        heroBine.addPotionEffect(resistance);
        heroBine.addPotionEffect(fireResistance);
        heroBine.addPotionEffect(speed);
        EntityEquipment equipment = heroBine.getEquipment();
        ItemStack skull = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
        SkullMeta meta = (SkullMeta) skull.getItemMeta();
        meta.setOwner("MHF_Herobrine");
        skull.setItemMeta(meta);
        equipment.setHelmet(skull);
        equipment.setChestplate(new ItemStack(Material.DIAMOND_CHESTPLATE));
        equipment.setLeggings(new ItemStack(Material.DIAMOND_LEGGINGS));
        equipment.setBoots(new ItemStack(Material.DIAMOND_BOOTS));
        equipment.setItemInHand(new ItemStack(Material.DIAMOND_SWORD));
    }
}
