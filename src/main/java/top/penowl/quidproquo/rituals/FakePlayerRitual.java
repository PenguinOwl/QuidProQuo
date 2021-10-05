package top.penowl.quidproquo.rituals;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Slime;
import org.bukkit.inventory.EntityEquipment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.Random;

import top.penowl.quidproquo.Ritual;

public class FakePlayerRitual extends Ritual {

    @Override
    public void setup() {
        addIngredient(Material.WHEAT, 32);
        addIngredient(Material.LEAVES, 32);
        name = "player illusion";
        notify = false;
        health = 4;
    }

    @Override
    public void execute(Player caster, Player target, Location location) {
        Location loc = target.getLocation();
        Random random = new Random();
        int x = random.nextInt(51) - 25;
        int y = random.nextInt(51) - 25;
        int z = random.nextInt(51) - 25;
        Slime slime = (Slime)target.getWorld().spawnEntity(loc.clone().add(x, y, z), EntityType.SLIME);
        LivingEntity armorStand = (LivingEntity)target.getWorld().spawnEntity(loc.clone().add(x, y, z), EntityType.ARMOR_STAND);
        armorStand.setCustomName(caster.getName());
        armorStand.setCustomNameVisible(true);
        slime.setPassenger(armorStand);
        slime.setSize(1);
        EntityEquipment equipment = armorStand.getEquipment();
        ItemStack skull = new ItemStack(Material.SKULL, 1);
        SkullMeta meta = (SkullMeta)skull;
        meta.setOwner(caster.getName());
        equipment.setHelmet(skull);
    }
    
}
