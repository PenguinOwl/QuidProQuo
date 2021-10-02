package top.penowl.quidproquo.rituals;
//TODO TEST

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

import top.penowl.quidproquo.Ritual;

public class HardPenorRitual extends Ritual {

    @Override
    public void setup() {
        addIngredient(Material.COBBLESTONE, 500);
        addIngredient(Material.BONE, 10);
        name = "erection";
    }

    @Override
    public void execute(Player caster, Player target, Location location) {
        Block shaft = caster.getLocation().getBlock();
        Block ball1 = shaft.getRelative(2, -1, 0);
        Block ball2 = shaft.getRelative(-2,-1,0);
        //TODO USE ELEGANT MATHEMATICS INSTEAD OF SCUFFED IF STATEMENTS
        for(int d = 0; d < 8; d ++) {
            int x = 0;
            if(d < 3) x = 1;
            if(d > 4) x = -1;
            int z = 0;
            if(d % 3 == 0) z = 1;
            if(d % 3 == 1) z = -1;
            for(int y = 0; y < 5; y++) {
                Block changingBlock = shaft.getRelative(x,y,z);
                if(changingBlock.getType() == Material.AIR)
                    changingBlock.setType(Material.COBBLESTONE);
            }
        }
        for(int x = 0; x < 3; x ++) {
            for(int y = 0; y < 3; y ++) {
                for(int z = 0; z < 3; z ++) {
                    Block changingBlock1 = ball1.getRelative(x,y,z);
                    Block changingBlock2 = ball2.getRelative(x,y,z);
                    if(changingBlock1.getType() == Material.AIR)
                        changingBlock1.setType(Material.COBBLESTONE);
                    if(changingBlock2.getType() == Material.AIR)
                        changingBlock1.setType(Material.COBBLESTONE);
                }
            }
        }
    }
    
}