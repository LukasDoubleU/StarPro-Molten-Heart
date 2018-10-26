
import greenfoot.*;
import java.util.*;

/**
 * Write a description of class Kugeldamage here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class BulletDamage extends Projectiles
{
    /**
     * Act - do whatever the Kugeldamage wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    
    int mov_speed;
    int damage = 1;
    Player target = null;
    boolean turnNotDone = true;
    
    public BulletDamage(int newMov_Speed, int newDamage, Player newTarget) {
        mov_speed = newMov_Speed;
        damage = newDamage;
        target = newTarget;
        
    }
    
    public void act() 
    {
        if(turnNotDone) {
            this.turnTowards(target.getX(), target.getY());
            turnNotDone = false;
        }
        
        move(mov_speed);
        if(this.isAtEdge()) {
            this.getWorld().removeObject(this);
        }
        
        List intersectingObjects = new ArrayList();
        intersectingObjects = this.getIntersectingObjects(Player.class);
        
        for(Object a : intersectingObjects)  {
            if(a instanceof Player) {
                Player.get().damage(damage);
            }
            
        }
    }    
}
