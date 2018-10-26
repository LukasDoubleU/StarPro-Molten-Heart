
import greenfoot.*;

import java.util.*;

public class Snake extends Melee {
    
    int counter = 0;
    boolean lockedOntoPlayer = false;
    int mov_speed;
    int damage = 1;
    Player target = null;
    
    public Snake(int newMov_Speed) {
        mov_speed = newMov_Speed;
        setImage("snake/image_part_010.png");
    }
    
    public Snake(){
        this(3);
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
        
        List intersectingObjects = new ArrayList();
        intersectingObjects = this.getIntersectingObjects(Player.class);
        
        for(Object a : intersectingObjects)  {
            if(a instanceof Player) {
                Player.get().damage(damage);
                
            }
            
        }
        
        move(mov_speed);
        counter = counter + 1;

    }    
}
