import greenfoot.*;
import java.util.*;

public class Bomb extends Enemy {
    
    int counter = 0;
    boolean foundplayer = false;

    public void act() {
        
        List actorinrange = new ArrayList();
        actorinrange = this.getObjectsInRange(30, Player.class);
        
        for(Object a : actorinrange)  {
            if(a instanceof Player) {
                
            }
            
        }
        
        
        
        if(counter==40){
            turn(90);
            counter = 0;
        }
        
        move(3);
        counter = counter + 1;

    }    
}
