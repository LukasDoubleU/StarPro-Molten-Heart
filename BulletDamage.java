
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
    
    int damage;
    int turnDegree;
    Player target = null;
    boolean turnNotDone;
    boolean isAlive;

    public BulletDamage(int newMov_Speed, int newDamage, Player newTarget, String imgPath) {
        super(newMov_Speed, 1);
        damage = newDamage;
        target = newTarget;
        setImage(imgPath);
        if(imgPath.equals("tear.png")) {
            getImage().scale(15,15);
        }
        isAlive = true;
        turnNotDone = true;
    }
    
    public BulletDamage(int newMov_Speed, int newDamage, int newTurnDegree, Player newTarget, String imgPath) {
        super(newMov_Speed, 1);
        damage = newDamage;
        target = newTarget;
        setImage(imgPath);
        if(imgPath.equals("tear.png")) {
            getImage().scale(15,15);
        }
        isAlive = true;
        turnNotDone = true;
        turnDegree = newTurnDegree;
    }
    
    public void act() 
    {
        if(isAlive) {
       
            if(turnNotDone) {
                firstTurn();
            }
        
            move(mov_speed);
            checkCollision();
       
        }
   
    }
    public void firstTurn() {
        if(target!=null) {
                this.turnTowards(target.getX(), target.getY());
            }
            if(target==null) {
                this.turn(turnDegree);
            }
        
            turnNotDone = false;
    }
    
    public void checkCollision() {
        List intersectingObjects = new ArrayList();
        intersectingObjects = this.getObjectsInRange(20, null); 
        
        for(Object a : intersectingObjects)  {          
            if(a instanceof Player) {
                Player.get().damage(damage);
                this.getWorld().removeObject(this);
                isAlive = false;
                return;
            }
            if((a instanceof Obstacle) && !(a instanceof Enemy)) {
                this.getWorld().removeObject(this);
                isAlive = false;
                return;
            }
           
        }
        if(this.isAtEdge()) {
            this.getWorld().removeObject(this);
            isAlive = false;
        }
    }
    
}
