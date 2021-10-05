package top.penowl.quidproquo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.UUID;
import java.util.stream.Collectors;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Effect;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.Sound;
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

        // only right clicks please
        if (event.getAction() != Action.RIGHT_CLICK_BLOCK) return;

        // general data that we want
        Block block = event.getClickedBlock();
        Player player = event.getPlayer();
        Location location = block.getLocation();

        // skip if we didn't click an altar
        if (!Altar.CheckAltar(block.getLocation())) return;

        event.setCancelled(true);

        // cycle targets if player is sneaking, otherwise try to enact a ritual
        if (player.isSneaking()) {

            // get the player's uuid
            UUID playerUuid = player.getUniqueId();

            // get a list of everyone else's uuid
            List<Player> players = new ArrayList<Player>(Bukkit.getOnlinePlayers());
            List<UUID> uuids = new ArrayList<UUID>();
            for (Player target : players) {
                //if (target.getUniqueId() != playerUuid) {
                    uuids.add(target.getUniqueId());
                //}
            }
            Collections.sort(uuids);

            player.playSound(location, Sound.CLICK, 0.5f, 1);
            if (uuids.size() == 0) {
                player.sendMessage(ChatColor.YELLOW + "No targets online!");
            } else {
                // if the player already has a target, pick the next one, otherwise pick the first
                if (QuidProQuo.instance.targets.containsKey(playerUuid)) {
                    UUID current_target = QuidProQuo.instance.targets.get(playerUuid);
                    if (Bukkit.getPlayer(QuidProQuo.instance.targets.get(playerUuid)) instanceof OfflinePlayer) {
                        QuidProQuo.instance.targets.put(playerUuid, uuids.get(0));
                    }
                    QuidProQuo.instance.targets.put(playerUuid, uuids.get((uuids.indexOf(current_target) + 1) % uuids.size()));
                } else {
                    QuidProQuo.instance.targets.put(playerUuid, uuids.get(0));
                }
                // friendly message
                player.sendMessage(ChatColor.YELLOW + "Switched target to " + Bukkit.getPlayer(QuidProQuo.instance.targets.get(playerUuid)).getName());
            }

        } else {

            // java control flow go brrrr
            Boolean success = false;

            // we find all of the items and mobs that matter
            Collection<Item> items = block.getWorld().getEntitiesByClass(Item.class);
            ArrayList<Item> near_items = new ArrayList<Item>();
            Collection<LivingEntity> sacrifices = block.getWorld().getEntitiesByClass(LivingEntity.class);
            ArrayList<LivingEntity> near_sacrifices = new ArrayList<LivingEntity>();
            for (Item item : items) {
                if (item.getLocation().distance(location) < 2) {
                    near_items.add(item);
                }
            }
            for (LivingEntity sacrifice : sacrifices) {
                if (sacrifice.getLocation().distance(location) < 6) {
                    near_sacrifices.add(sacrifice);
                }
            }

            // we check each ritual in order
            for (Ritual ritual : QuidProQuo.instance.rituals) {

                // this is what all the ingredients get collected in
                ArrayList<List<Item>> possibleItems = new ArrayList<List<Item>>();
                ArrayList<List<LivingEntity>> possibleSacrifices = new ArrayList<List<LivingEntity>>();
                Boolean failed = false;
                ArrayList<ItemStack> byproducts = new ArrayList<ItemStack>();

                // iterate over each different part of the recipe
                for (Map.Entry<Material, Integer> entry : ritual.ingredients.entrySet()) {

                    Material material = entry.getKey();
                    int count = entry.getValue();

                    // this just sorts out matching items
                    List<Item> matches = near_items.stream().filter(item -> item.getItemStack().getType() == material).collect(Collectors.toList());
                    int matchCount = 0;
                    for (Item item : matches) {
                        matchCount += item.getItemStack().getAmount();
                    }
                    if (matchCount > count) {
                        possibleItems.add(matches);
                        byproducts.add(new ItemStack(material, matchCount - count));
                    } else if (matchCount == count) {
                        possibleItems.add(matches);
                    } else {
                        failed = true;
                        break;
                    }
                }

                // same thing but mob sacrifices
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

                // next recipe if we missed an ingredient
                if (failed) continue;

                // delete all used items
                for (List<Item> itemArray : possibleItems) {
                    for (Item item : itemArray) {
                        item.remove();
                    }
                }

                // kill all sacrifices
                for (List<LivingEntity> sacrificeArray : possibleSacrifices) {
                    for (LivingEntity sacrifice : sacrificeArray) {
                        sacrifice.setHealth(0);
                    }
                }

                // administer health penalty
                if (player.getGameMode() != GameMode.CREATIVE && player.getGameMode() != GameMode.SPECTATOR) {
                    player.setHealth(Math.max(0, player.getHealth() - ritual.health));
                }

                // shiny message
                player.sendMessage(ChatColor.GREEN + "You enacted a " + ritual.name + " ritual.");

                // add in extra items from recipe
                for (ItemStack additionalByproduct : ritual.byproducts) {
                    byproducts.add(additionalByproduct);
                }

                // spit out extra items that were consumed 
                for (ItemStack byproduct : byproducts) {
                    block.getLocation().getWorld().dropItem(location.clone().add(0, 2, 0), byproduct);
                }

                // uuid stuff to make sure we target the right person
                UUID playerUuid = player.getUniqueId();
                Player otherPlayer;
                List<Player> players = new ArrayList<Player>(Bukkit.getOnlinePlayers());
                List<UUID> uuids = new ArrayList<UUID>();
                for (Player target : players) {
                    //if (target.getUniqueId() != playerUuid) {
                        uuids.add(target.getUniqueId());
                    //}
                }
                Collections.sort(uuids);

                if (uuids.size() == 0) {
                    otherPlayer = null;
                } else {
                    if (!QuidProQuo.instance.targets.containsKey(playerUuid)) {
                        QuidProQuo.instance.targets.put(playerUuid, uuids.get(0));
                    }
                    if (!(Bukkit.getPlayer(QuidProQuo.instance.targets.get(playerUuid))).isOnline()) {
                        QuidProQuo.instance.targets.put(playerUuid, uuids.get(0));
                        Bukkit.getLogger().info("tet");
                    }
                    otherPlayer = Bukkit.getPlayer(QuidProQuo.instance.targets.get(playerUuid));
                }

                // backfire check, if succeeds then the ritual gets reversed
                if(new Random().nextDouble() >= ritual.backfire) {
                    if (ritual.notify && otherPlayer.getUniqueId() != player.getUniqueId()) {
                        otherPlayer.sendMessage(ChatColor.YELLOW + "You were hit by a " + ritual.name + " ritual!");
                    }
                    ritual.execute(player, otherPlayer, block.getLocation());
                } else {
                    if (ritual.notify) {
                        if (otherPlayer.getUniqueId() != player.getUniqueId()) {
                            otherPlayer.sendMessage(ChatColor.YELLOW + "Someone tried to enact a " + ritual.name + " ritual on you but it backfired!");
                        }
                        player.sendMessage(ChatColor.YELLOW + "The ritual backfired on you!");
                    }
                    ritual.execute(otherPlayer, player, block.getLocation());
                }
                
                // cool effects
                player.playSound(player.getLocation(), Sound.EXPLODE, 50, 0);
                player.getWorld().playEffect(location.clone().add(0, 1, 0), Effect.EXPLOSION_LARGE, 0);
                if (ritual.lightning) {
                    player.getWorld().strikeLightningEffect(location.clone().add(0, 1, 0));
                }

                // poggers
                success = true;

                break;

            }

            if (!success) {
                player.sendMessage(ChatColor.RED + "You do not have the nessecary sacrifices to enact a ritual!");
            }

        }

    }
}
