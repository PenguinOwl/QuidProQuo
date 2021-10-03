package top.penowl.quidproquo.rituals;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Player;

import top.penowl.quidproquo.Ritual;

public class LightningRitual extends Ritual {

    @Override
    public void setup() {
        addIngredient(Material.WHEAT, 64);
        addIngredient(Material.GLOWSTONE, 64);
        name = "lightning location";
    }

    @Override
    public void execute(Player caster, Player target, Location location) {
        // Does this actually get the overworld?
        World overworld = Bukkit.getWorld("world");
        List<Player> players = overworld.getPlayers();
        for(Player player : players) {
            overworld.strikeLightningEffect(player.getLocation().clone().add(0, 1, 0));
        }
    }
    
}
