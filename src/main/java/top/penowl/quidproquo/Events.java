package top.penowl.quidproquo;

import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

public final class Events implements Listener {

    @EventHandler
    public static void onPlayerInteract(PlayerInteractEvent event) {
        Block block = event.getClickedBlock();
        Player player = event.getPlayer();

        if (Altar.CheckAltar(block.getLocation())) {
            player.sendMessage("you clicked an altar");
        } else {
            player.sendMessage("not an altar");
        }

    }
}
