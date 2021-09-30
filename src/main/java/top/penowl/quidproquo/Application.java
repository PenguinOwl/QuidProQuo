package top.penowl.quidproquo;
import java.util.ArrayList;

import org.bukkit.event.HandlerList;
import org.bukkit.plugin.java.JavaPlugin;

public class Application extends JavaPlugin {
    
    public ArrayList<Ritual> rituals = new ArrayList<Ritual>();

    @Override
    public void onEnable() {
        getLogger().info("Hello, SpigotMC!");
        getServer().getPluginManager().registerEvents(new Events(), this);
    }
    @Override
    public void onDisable() {
        getLogger().info("See you again, SpigotMC!");
        HandlerList.unregisterAll(this);
    }

}