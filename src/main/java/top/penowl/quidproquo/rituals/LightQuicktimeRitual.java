package top.penowl.quidproquo.rituals;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import top.penowl.quidproquo.Ritual;

public class LightQuicktimeRitual extends Ritual {

    @Override
    public void setup() {
        addIngredient(Material.WHEAT, 64+32);
        addIngredient(Material.GLASS_BOTTLE, 1);
        addIngredient(Material.SPIDER_EYE, 1);
        name = "quicktime lite";
        health = 4;
        backfire = 0.3;
        description = "Force your enemy to undergo a quicktime event.";
    }

    @Override
    public void execute(Player caster, Player target, Location location) {
        target.addPotionEffect(new PotionEffect(PotionEffectType.POISON, 60, 1));
        target.getWorld().dropItemNaturally(target.getLocation(), new ItemStack(Material.MILK_BUCKET, 1));
        target.sendMessage("DRINK THE MILK IDIOT");
    }
    
}
