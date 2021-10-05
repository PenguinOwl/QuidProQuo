package top.penowl.quidproquo.rituals;

import java.util.List;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Player;

import top.penowl.quidproquo.Ritual;

public class LightningRitual extends Ritual {

    @Override
    public void setup() {
        addIngredient(Material.WHEAT, 64);
        addIngredient(Material.GLOWSTONE, 16);
        name = "lightning location";
        description = "Send a bolt of locating lightning to all players.";
        notify = false;
    }

    @Override
    public void execute(Player caster, Player target, Location location) {
        // Does this actually get the overworld?
        World world = location.getWorld();
        List<Player> players = world.getPlayers();
        for(Player player : players) {
            world.strikeLightningEffect(player.getLocation().clone().add(0, 1, 0));
        }
    }
    
}