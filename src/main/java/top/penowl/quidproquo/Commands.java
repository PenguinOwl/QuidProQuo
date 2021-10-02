package top.penowl.quidproquo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.WordUtils;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.EntityType;

public class Commands implements CommandExecutor {

    HashMap<String, Ritual> recipes = new HashMap<String, Ritual>();

    public Commands(List<Ritual> rituals) {
        for (Ritual ritual : rituals) {
            recipes.put(ritual.name.toLowerCase(), ritual);
        }
    }

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (args.length < 1) {
            // display help
            return false;
        } else if (args[0].toLowerCase().equals("list")) {
            List<String> ritualNames = new ArrayList<String>(recipes.keySet());
            int max = ritualNames.size() - 1;
            int maxPages = max / 9;
            int page = 0;
            try {
                page = Math.min(Math.max(Integer.parseInt(args[1]) - 1, 0), maxPages);
            } catch(Exception e) {
            }
            StringBuilder builder = new StringBuilder();
            builder.append(ChatColor.DARK_PURPLE + "======== Rituals ========    " + ChatColor.YELLOW + "Page " + String.valueOf(page + 1) + " / " + String.valueOf(maxPages + 1));
            builder.append("\n" + ChatColor.GREEN);
            for (int i = page * 9; i < page * 9 + 9; i++) {
                if (i > max) {
                    builder.append(" \n");
                    continue;
                }
                builder.append(ritualNames.get(i));
                builder.append("\n" + ChatColor.GREEN);
            }
            sender.sendMessage(builder.toString());
            return true;
        } else if (recipes.containsKey(String.join(" ", args).toLowerCase())) {
            String key = String.join(" ", args).toLowerCase();
            StringBuilder builder = new StringBuilder();
            Ritual ritual = recipes.get(key);
            builder.append("\n");
            builder.append(ChatColor.DARK_RED + "======== " + ChatColor.YELLOW + WordUtils.capitalizeFully(key) + " Ritual" + ChatColor.DARK_RED + " ========\n ");
            builder.append("\n" + ChatColor.GREEN + "" + ChatColor.BOLD + "Ingredients:\n");
            for (Map.Entry<Material, Integer> entry : ritual.ingredients.entrySet()) {
                builder.append(ChatColor.GOLD);
                builder.append(WordUtils.capitalizeFully(entry.getKey().toString().replace('_', ' ').toLowerCase()));
                builder.append(": " + ChatColor.AQUA + entry.getValue().toString());
                builder.append("\n");
            }
            builder.append(" \n" + ChatColor.RED + "" + ChatColor.BOLD + "Sacrifices:\n");
            for (Map.Entry<EntityType, Integer> entry : ritual.sacrifices.entrySet()) {
                builder.append(ChatColor.GOLD);
                builder.append(WordUtils.capitalizeFully(entry.getKey().toString().replace('_', ' ').toLowerCase()));
                builder.append(": " + ChatColor.AQUA + entry.getValue().toString());
                builder.append("\n");
            }
            builder.append(" \n" + ChatColor.YELLOW + "" + ChatColor.BOLD + "Blood: " + ChatColor.RESET + "" + ChatColor.AQUA + String.valueOf(ritual.health / 2.0) + ChatColor.RED + " ♥\n");
            builder.append(ChatColor.YELLOW + "" + ChatColor.BOLD + "Backfire: " + ChatColor.RESET + "" + ChatColor.AQUA + String.valueOf(Math.round(ritual.backfire * 100)) + "%\n");
            sender.sendMessage(builder.toString());
            return true;
        }
        return false;
	}
    
}
