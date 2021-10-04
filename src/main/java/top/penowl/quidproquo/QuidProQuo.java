package top.penowl.quidproquo;
import java.lang.reflect.Constructor;
import java.nio.Buffer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.event.HandlerList;
import org.bukkit.plugin.java.JavaPlugin;
import org.reflections.Reflections;

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

        // auto-register rituals
        Set<Class<? extends Ritual>> subClasses = (new Reflections()).getSubTypesOf(Ritual.class);
        for (Class<? extends Ritual> ritualClass : subClasses) {
            Class<?>[] params = {};
            try {
                if (ritualClass.getField("enabled").getBoolean(null)) {
                    Constructor<? extends Ritual> constructor = ritualClass.getConstructor(params);
                    Object[] args = {};
                    rituals.add(constructor.newInstance(args));
                }
            } catch (Exception e) {
                System.out.println(e.toString());
            }
        }

        // register all rituals
        // rituals.add(new AnvilRitual());
        // rituals.add(new BatSpewRitual());
        // rituals.add(new BlockShiftRitual());
        // rituals.add(new ChickenSpewRitual());
        // rituals.add(new ChugJugRitual());
        // rituals.add(new CreeperHissRitual());
        // rituals.add(new CrystalizationRtiual());
        // rituals.add(new DragonRitual());
        // rituals.add(new FakePlayerRitual());
        // rituals.add(new FeedingRitual());
        // rituals.add(new FillerRitual());
        // rituals.add(new GBJRitual());
        // rituals.add(new GetLuckyRitual());
        // rituals.add(new HardPenorRitual());
        // rituals.add(new HealRitual());
        // rituals.add(new HerobrineRitual());
        // rituals.add(new HitRitual());
        // rituals.add(new LavaRitual());
        // rituals.add(new LightQuicktimeRitual());
        // rituals.add(new LightningRitual());
        // rituals.add(new MidasRitual());
        // rituals.add(new QuicktimeRitual());
        // rituals.add(new RotateRitual());
        // rituals.add(new SnowmanRitual());
        // rituals.add(new SoftPenorRitual());
        // rituals.add(new SoundRitual());
        // rituals.add(new SummoningRitual());
        // rituals.add(new WitherRitual());
        // rituals.add(new WoolingRitual());
        // rituals.add(new RandomItemRitual());
        // rituals.add(new KickingRitual());

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
