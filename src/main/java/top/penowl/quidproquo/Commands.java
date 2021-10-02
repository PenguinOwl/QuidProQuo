package top.penowl.quidproquo;

import java.util.HashMap;
import java.util.List;

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
            return true;
        } else if (recipes.containsKey(args[0].toUpperCase())) {
            // display recipe info
            return true;
        }
        return false;
	}
    
}
