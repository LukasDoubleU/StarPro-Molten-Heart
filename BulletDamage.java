
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
    int damage;
    int turnDegree;
    Player target = null;
    boolean turnNotDone;
    boolean isAlive;

    public BulletDamage(int newMov_Speed, int newDamage, Player newTarget, String imgPath) {
        mov_speed = newMov_Speed;
        damage = newDamage;
        target = newTarget;
        setImage(imgPath);
        isAlive = true;
        turnNotDone = true;
    }
    
    public BulletDamage(int newMov_Speed, int newDamage, int newTurnDegree, Player newTarget, String imgPath) {
        mov_speed = newMov_Speed;
        damage = newDamage;
        target = newTarget;
        setImage(imgPath);
        isAlive = true;
        turnNotDone = true;
        turnDegree = newTurnDegree;
    }
    
    public void act() 
    {
        if(isAlive) {
       
        if(turnNotDone) {
            if(target!=null) {
                this.turnTowards(target.getX(), target.getY());
            }
            if(target==null) {
                this.turn(turnDegree);
            }
        
            turnNotDone = false;
            
        }
        
        move(mov_speed);
        
        List intersectingObjects = new ArrayList();
        intersectingObjects = this.getIntersectingObjects(Player.class);
        
        if(this.isAtEdge()) {
            this.getWorld().removeObject(this);
            isAlive = false;
        }
        
        for(Object a : intersectingObjects)  {
            if(a instanceof Player) {
                Player.get().damage(damage);
                this.getWorld().removeObject(this);
                isAlive = false;
            }
            if(a instanceof Obstacle) {
                this.getWorld().removeObject(this);
                isAlive = false;
            }
            
        }
    }
   
    }    
}
