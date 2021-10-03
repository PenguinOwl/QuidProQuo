package top.penowl.quidproquo;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

import org.bukkit.event.HandlerList;
import org.bukkit.plugin.java.JavaPlugin;

import top.penowl.quidproquo.rituals.*;

public class QuidProQuo extends JavaPlugin {

    // DONT COMMENT CODE AT 3 AM

    // haha singleton go brrr
    public static QuidProQuo instance;

    // global list of registered rituals
    public ArrayList<Ritual> rituals = new ArrayList<Ritual>();
    
    // dictionary of who has who selected as a ritual target
    public HashMap<UUID, UUID> targets = new HashMap<UUID, UUID>();

    // this gets run when the plugin loads
    @Override
    public void onEnable() {

        // here we register events and the singleton
        instance = this;
        getLogger().info("Loading rituals...");
        getServer().getPluginManager().registerEvents(new Events(), this);

        // register all rituals
        rituals.add(new AnvilRitual());
        rituals.add(new ChickenSpewRitual());
        rituals.add(new ChugJugRitual());
        rituals.add(new CreeperHissRitual());
        rituals.add(new FeedingRitual());
        rituals.add(new FillerRitual());
        rituals.add(new GetLuckyRitual());
        rituals.add(new HardPenorRitual());
        rituals.add(new HealRitual());
        rituals.add(new HerobrineRitual());
        rituals.add(new HitRitual());
        rituals.add(new LavaRitual());
        rituals.add(new RotateRitual());
        rituals.add(new SnowmanRitual());
        rituals.add(new SoftPenorRitual());
        rituals.add(new WoolingRitual());

        // run ritual setup scripts
        for (Ritual ritual : rituals) {
            getLogger().info("Loading " + ritual.getClass().toString() + "...");
            ritual.setup();
            getLogger().info("Ingredients: " + ritual.ingredients.toString());
            getLogger().info("Sacrifices: " + ritual.sacrifices.toString());
            getLogger().info("Loaded a " + ritual.name + " ritual.");
        }

        // register command handler
        getCommand("ritual").setExecutor(new Commands(rituals));

    }
    @Override
    public void onDisable() {

        // basically just handler cleanup
        getLogger().info("Unloading handler...");
        HandlerList.unregisterAll(this);

    }

}
