package top.penowl.quidproquo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.lang.WordUtils;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.util.StringUtil;

import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;

public class Commands implements CommandExecutor, TabCompleter {

    HashMap<String, Ritual> recipes = new HashMap<String, Ritual>();

    public Commands(List<Ritual> rituals) {
        for (Ritual ritual : rituals) {
            recipes.put(ritual.name.toLowerCase(), ritual);
        }
    }

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        String key = String.join(" ", Arrays.asList(args).stream().map(string -> string.replace("_", " ")).collect(Collectors.toSet())).toLowerCase();
		if (args.length < 1 || args[0].toLowerCase().equals("list")) {
            List<String> ritualNames = new ArrayList<String>(recipes.keySet());
            Collections.sort(ritualNames);
            int max = ritualNames.size() - 1;
            int maxPages = max / 9;
            int page = 0;
            try {
                page = Math.min(Math.max(Integer.parseInt(args[1]) - 1, 0), maxPages);
            } catch(Exception e) {
            }
            ComponentBuilder builder = new ComponentBuilder("");
            if (args.length > 2 && args[2].equals("d")) {
                builder.append("\n\n\n\n\n\n\n\n\n\n");
            }
            builder.append(ChatColor.DARK_PURPLE + "    ======= " + ChatColor.LIGHT_PURPLE + "Rituals" + ChatColor.DARK_PURPLE + " =======    ");
            if (page != 0) {
                builder.append(" <  ");
                builder.color(net.md_5.bungee.api.ChatColor.YELLOW);
                builder.event(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/rt list " + String.valueOf(page) + " d"));
            } else {
                builder.append(" <  ");
                builder.color(net.md_5.bungee.api.ChatColor.GRAY);
            }
            builder.append("Page " + String.valueOf(page + 1) + " / " + String.valueOf(maxPages + 1) + " ");
            builder.color(net.md_5.bungee.api.ChatColor.AQUA);
            if (page != maxPages) {
                builder.append("  > ");
                builder.color(net.md_5.bungee.api.ChatColor.YELLOW);
                builder.event(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/rt list " + String.valueOf(page + 2) + " d"));
            } else {
                builder.append("  > ");
                builder.color(net.md_5.bungee.api.ChatColor.GRAY);
            }
            for (int i = page * 9; i < page * 9 + 9; i++) {
                if (i > max) {
                    builder.append(" \n");
                    continue;
                }
                builder.append("\n" + WordUtils.capitalizeFully(ritualNames.get(i)) + " Ritual");
                if (i % 2 == 0) {
                    builder.color(net.md_5.bungee.api.ChatColor.GOLD);
                } else {
                    builder.color(net.md_5.bungee.api.ChatColor.YELLOW);
                }
                builder.event(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/ritual " + ritualNames.get(i)));
                builder.event(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(ChatColor.YELLOW + "Click to see more...").create()));
            }
            if (sender instanceof Player) {
                Player playerSender = (Player) sender;
                playerSender.spigot().sendMessage(builder.create());
            }
            return true;
        } else if (recipes.containsKey(key)) {
            ComponentBuilder builder = new ComponentBuilder("");
            Ritual ritual = recipes.get(key);
            builder.append("\n\n\n\n\n\n\n\n\n\n\n");
            builder.append(ChatColor.DARK_RED + "   ==== " + ChatColor.YELLOW + WordUtils.capitalizeFully(key) + " Ritual" + ChatColor.DARK_RED + " ====  ");
            builder.append("  [Back]");
            builder.color(net.md_5.bungee.api.ChatColor.GOLD);
            builder.event(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/rt list 1 d"));
            builder.append("\n\n" + ChatColor.GREEN + "" + ChatColor.BOLD + "Ingredients:\n");
            for (Map.Entry<Material, Integer> entry : ritual.ingredients.entrySet()) {
                builder.append(WordUtils.capitalizeFully(entry.getKey().toString().replace('_', ' ').toLowerCase()));
                builder.color(net.md_5.bungee.api.ChatColor.DARK_AQUA);
                builder.append(": " + ChatColor.AQUA + entry.getValue().toString());
                builder.append("\n");
            }
            if (ritual.sacrifices.size() > 0) {
                builder.append(" \n" + ChatColor.RED + "" + ChatColor.BOLD + "Sacrifices:\n");
                for (Map.Entry<EntityType, Integer> entry : ritual.sacrifices.entrySet()) {
                    builder.append(WordUtils.capitalizeFully(entry.getKey().toString().replace('_', ' ').toLowerCase()));
                    builder.color(net.md_5.bungee.api.ChatColor.DARK_AQUA);
                    builder.append(": " + ChatColor.AQUA + entry.getValue().toString());
                    builder.append("\n");
                }
            }
            builder.append(" \n" + ChatColor.YELLOW + "" + ChatColor.BOLD + "Blood: " + ChatColor.RESET + "" + ChatColor.AQUA + String.valueOf(ritual.health / 2.0) + ChatColor.RED + " ♥\n");
            builder.append(ChatColor.YELLOW + "" + ChatColor.BOLD + "Backfire: " + ChatColor.RESET + "" + ChatColor.AQUA + String.valueOf(Math.round(ritual.backfire * 100)) + "%");
            if (sender instanceof Player) {
                Player playerSender = (Player) sender;
                playerSender.spigot().sendMessage(builder.create());
            }
            return true;
        }
        return false;
	}

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        final List<String> completions = new ArrayList<>();
        StringUtil.copyPartialMatches(args[0], recipes.keySet().stream().map(string -> string.replace(" ", "_")).collect(Collectors.toSet()), completions);
        Collections.sort(completions);
        return completions;
    }
    
}
