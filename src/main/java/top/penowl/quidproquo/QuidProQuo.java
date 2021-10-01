package top.penowl.quidproquo;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

import org.bukkit.event.HandlerList;
import org.bukkit.plugin.java.JavaPlugin;

import top.penowl.quidproquo.rituals.FeedingRitual;
import top.penowl.quidproquo.rituals.HealRitual;
import top.penowl.quidproquo.rituals.WoolingRitual;

public class QuidProQuo extends JavaPlugin {
    
    public static QuidProQuo instance;
    public ArrayList<Ritual> rituals = new ArrayList<Ritual>();
    public HashMap<UUID, UUID> targets = new HashMap<UUID, UUID>();

    @Override
    public void onEnable() {
        instance = this;
        getLogger().info("Hello, SpigotMC!");
        getServer().getPluginManager().registerEvents(new Events(), this);

        rituals.add(new HealRitual());
        rituals.add(new WoolingRitual());
        rituals.add(new FeedingRitual());

        for (Ritual ritual : rituals) {
            getLogger().info("Loading " + ritual.getClass().toString() + "...");
            ritual.setup();
            getLogger().info("Loaded a " + ritual.name + " ritual.");
        }
    }
    @Override
    public void onDisable() {
        getLogger().info("See you again, SpigotMC!");
        HandlerList.unregisterAll(this);
    }

}