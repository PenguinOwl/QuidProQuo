package top.penowl.quidproquo.rituals;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import top.penowl.quidproquo.Ritual;

public class SummoningRitual extends Ritual {

    @Override
    public void setup() {
        addIngredient(Material.ENDER_PEARL, 1);
        addIngredient(Material.COMPASS, 1);
        addIngredient(Material.WHEAT, 64+32);
        health = 6;
        name = "summoning";
        backfire = 0.04;
        description = "Summons a player to the altar.";
    }

    @Override
    public void execute(Player caster, Player target, Location location) {
        target.teleport(location.clone().add(0, 1, 0));
        target.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 10, 255));
        location.getWorld().createExplosion(location, 5F);
    }
    
}