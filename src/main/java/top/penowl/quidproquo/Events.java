package top.penowl.quidproquo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Item;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public final class Events implements Listener {

    @EventHandler
    public static void onPlayerInteract(PlayerInteractEvent event) {

        if (event.getAction() != Action.RIGHT_CLICK_BLOCK) return;

        Block block = event.getClickedBlock();
        Player player = event.getPlayer();
        Location location = block.getLocation();

        if (!Altar.CheckAltar(block.getLocation())) return;

        if (player.isSneaking()) {

            // change target

        } else {

            Boolean success = false;

            Collection<Item> items = block.getWorld().getEntitiesByClass(Item.class);
            ArrayList<Item> near_items = new ArrayList<Item>();

            for (Item item : items) {
                if (item.getLocation().distance(location) < 2) {
                    near_items.add(item);
                }
            }

            Bukkit.getLogger().info(near_items.toString());

            Collection<LivingEntity> sacrifices = block.getWorld().getEntitiesByClass(LivingEntity.class);
            ArrayList<LivingEntity> near_sacrifices = new ArrayList<LivingEntity>();

            for (LivingEntity sacrifice : sacrifices) {
                if (sacrifice.getLocation().distance(location) < 2) {
                    near_sacrifices.add(sacrifice);
                }
            }

            for (Ritual ritual : QuidProQuo.instance.rituals) {
                ArrayList<List<Item>> possibleItems = new ArrayList<List<Item>>();
                ArrayList<List<LivingEntity>> possibleSacrifices = new ArrayList<List<LivingEntity>>();
                Boolean failed = false;
                ArrayList<ItemStack> byproducts = new ArrayList<ItemStack>();
                for (Map.Entry<Material, Integer> entry : ritual.ingredients.entrySet()) {
                    Material material = entry.getKey();
                    int count = entry.getValue();

                    List<Item> matches = near_items.stream().filter(item -> item.getItemStack().getType() == material).collect(Collectors.toList());
                    int matchCount = 0;
                    for (Item item : matches) {
                        matchCount += item.getItemStack().getAmount();
                    }
                    if (matchCount >= count) {
                        possibleItems.add(matches);
                        byproducts.add(new ItemStack(material, matchCount - count));
                    } else {
                        failed = true;
                        break;
                    }
                }
                for (Map.Entry<EntityType, Integer> entry : ritual.sacrifices.entrySet()) {
                    EntityType entityType = entry.getKey();
                    int count = entry.getValue();

                    List<LivingEntity> matches = near_sacrifices.stream().filter(entity -> entity.getType() == entityType).limit(count).collect(Collectors.toList());
                    if (matches.size() >= count) {
                        possibleSacrifices.add(matches);
                    } else {
                        failed = true;
                        break;
                    }
                }

                if (failed) continue;

                for (List<Item> itemArray : possibleItems) {
                    for (Item item : itemArray) {
                        item.remove();
                    }
                }

                for (List<LivingEntity> sacrificeArray : possibleSacrifices) {
                    for (LivingEntity sacrifice : sacrificeArray) {
                        sacrifice.setHealth(0);
                    }
                }

                player.setHealth(player.getHealth() - ritual.health);

                player.sendMessage(ChatColor.GREEN + "You enacted a " + ritual.name + " ritual.");

                for (ItemStack additionalByproduct : ritual.byproducts) {
                    byproducts.add(additionalByproduct);
                }

                for (ItemStack byproduct : byproducts) {
                    block.getLocation().getWorld().dropItem(location.clone().add(0, 2, 0), byproduct);
                }

                if(new Random().nextDouble() >= ritual.backfire) {
                    ritual.execute(player, null, block.getLocation());
                } else {
                    ritual.execute(null, player, block.getLocation());
                }
                
                player.playSound(player.getLocation(), Sound.EXPLODE, 50, 0);
                player.getWorld().playEffect(location.clone().add(0, 1, 0), Effect.EXPLOSION_LARGE, 0);
                player.getWorld().strikeLightningEffect(location.clone().add(0, 1, 0));

                success = true;

                break;

            }

            if (!success) {
                player.sendMessage(ChatColor.RED + "You do not have the nessecary sacrifices to enact a ritual!");
            }

        }

    }
}
