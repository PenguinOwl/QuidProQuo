package top.penowl.quidproquo.rituals;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import java.util.Random;
import top.penowl.quidproquo.Ritual;

public class SoundRitual extends Ritual {
    @Override
    public void setup() {
        addIngredient(Material.NOTE_BLOCK, 1);
        addIngredient(Material.WHEAT, 4);
        name = "shadow sound";
        health = 1;
        notify = false;
    }

    @Override
    public void execute(Player caster, Player target, Location location) {
        Sound randomSound;
        int randomInt = new Random().nextInt(15);
        switch (randomInt) {
            case 0:
                randomSound = Sound.STEP_STONE;
                break;
            case 1:
                randomSound = Sound.CLICK;
                break;
            case 2:
                randomSound = Sound.CREEPER_HISS;
                break;
            case 3:
                randomSound = Sound.DIG_GRAVEL;
                break;
            case 4:
                randomSound = Sound.DIG_STONE;
                break;
            case 5:
                randomSound = Sound.DIG_WOOD;
                break;
            case 6:
                randomSound = Sound.EXPLODE;
                break;
            case 7:
                randomSound = Sound.FIZZ;
                break;
            case 8:
                randomSound = Sound.GHAST_SCREAM;
                break;
            case 9:
                randomSound = Sound.SKELETON_HURT;
                break;
            case 10:
                randomSound = Sound.STEP_GRAVEL;
                break;
            case 11:
                randomSound = Sound.STEP_GRASS;
                break;
            case 12:
                randomSound = Sound.ENDERDRAGON_GROWL;
                break;
            case 13:
                randomSound = Sound.AMBIENCE_CAVE;
                break;
            default:
                randomSound = Sound.ZOMBIE_HURT;
                break;
        }
        target.playSound(target.getLocation(), randomSound, 1, 1);
    }

}
