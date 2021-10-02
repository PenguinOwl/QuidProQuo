package top.penowl.quidproquo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class Commands implements CommandExecutor {

    HashMap<String, Ritual> recipes = new HashMap<String, Ritual>();

    public Commands(List<Ritual> rituals) {
        for (Ritual ritual : rituals) {
            recipes.put(ritual.name.toUpperCase(), ritual);
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
            int maxPages = max / 10;
            int page = 0;
            try {
                page = Math.min(Math.max(Integer.parseInt(args[1]) - 1, 0), maxPages);
            } catch(Exception e) {
            }
            StringBuilder builder = new StringBuilder();
            builder.append(ChatColor.DARK_PURPLE + "======== Rituals ========    " + ChatColor.YELLOW + "Page " + String.valueOf(page + 1) + " / " + String.valueOf(maxPages + 1));
            builder.append("\n" + ChatColor.GREEN);
            for (int i = page * 10; i < Math.min(page * 10 + 10, max + 1); i++) {
                builder.append(ritualNames.get(i));
                builder.append("\n" + ChatColor.GREEN);
            }
            sender.sendMessage(builder.toString());
            return true;
        } else if (recipes.containsKey(args[0].toUpperCase())) {
            // display recipe info
            return true;
        }
        return false;
	}
    
}
