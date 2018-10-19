package enemy;
import greenfoot.*;
import player.Player;

import java.util.*;

/**
 * Write a description of class Kamel here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Kamel extends Ranged
{
    /**
     * Act - do whatever the Kamel wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    int counter = 0;
    boolean lockedOntoPlayer = false;
    int mov_speed;
    float schaden;
    Player target = null;
    List projectiles = new ArrayList();
    
    public Kamel(int newMov_Speed) {
        turn(45);
        mov_speed = newMov_Speed;
    }
    
    public Kamel() {
        this(3);
    }
        
    public void act() 
    {
        if(target == null) {
            target = getTarget();
        }
        
        if(target != null) {
            this.turnTowards(target.getX(), target.getY());
            if(counter == 100) {
                KugelSchaden k = new KugelSchaden(4, 1.0f, target);
                this.getWorld().addObject(k, this.getX(), this.getY());
                counter = 0;
            }
            counter++;
            move(1);
        }else {
            if(counter==30){
            turn(90);
            counter = 0;
           
            
        }
        move(mov_speed);
        counter = counter + 1; 
            
        }
        
      
    }    
    
    public Player getTarget() {
        List actorinrange = new ArrayList();
        actorinrange = this.getObjectsInRange(200, Player.class);
        
        for(Object a : actorinrange)  {
            if(a instanceof Player) {
              
                return (Player) a;
            }
            
        }
        return null;
        
    }
}
