import greenfoot.*;
import java.util.*;

public class Bomb extends Melee {
    
    int counter = 0;
    boolean lockedOntoPlayer = false;
    int mov_speed;
    float schaden;
    Player target = null;
    
    public Bomb(int newMov_Speed) {
        mov_speed = newMov_Speed;
    }

    public void act() {
        
        
        List actorinrange = new ArrayList();
        actorinrange = this.getObjectsInRange(200, Player.class);
        
        for(Object a : actorinrange)  {
            if(a instanceof Player) {
                lockedOntoPlayer = true;
                target =(Player) a;
            }
            
        }
        
        if(lockedOntoPlayer) {
            this.turnTowards(target.getX(), target.getY());
        }
        
        if(counter==40){
            turn(90);
            counter = 0;
        }
        
        move(mov_speed);
        counter = counter + 1;

    }    
}
