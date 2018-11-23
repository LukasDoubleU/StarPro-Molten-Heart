
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
    int knockBackX;
    int knockBackY;
    Player target = null;
    boolean turnNotDone;
    boolean isAlive;
    boolean knockBack = false;

    public BulletDamage(int newMov_Speed, int newDamage, Player newTarget, String imgPath) {
        super(newMov_Speed, 1, imgPath);
        damage = newDamage;
        target = newTarget;
        if(imgPath.equals("tear.png")) {
            getImage().scale(15,15);
        }
        isAlive = true;
        turnNotDone = true;
    }
    
    
    
    public BulletDamage(int newMov_Speed, int newDamage, int newTurnDegree, Player newTarget, String imgPath) {
        super(newMov_Speed, 1, imgPath);
        damage = newDamage;
        target = newTarget;
        if(imgPath.equals("tear.png")) {
            getImage().scale(15,15);
        }
        isAlive = true;
        turnNotDone = true;
        turnDegree = newTurnDegree;
    }
    
    public BulletDamage(int newMov_Speed, int newDamage, int newTurnDegree, Player newTarget, String imgPath, int knockBackXX, int knockBackYY) {
        super(newMov_Speed, 5, imgPath);
        damage = newDamage;
        target = newTarget;
        isAlive = true;
        turnNotDone = true;
        turnDegree = newTurnDegree;
        this.knockBackX = knockBackXX;
        this.knockBackY = knockBackYY;
        this.knockBack = true;
        getImage().scale(90,90);
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
        if(this.knockBack){
            intersectingObjects = this.getObjectsInRange((this.getImage().getHeight()/2)+10, null);
        }

        
        for(Object a : intersectingObjects)  {          
            if(a instanceof Player) {
                if(this.knockBack){
                    Player.get().setLocation(this.knockBackX, this.knockBackY);
                    this.getWorld().removeObject(this);
                    isAlive = false;
                    return;
                }else{
                    Player.get().damage(damage);
                    this.getWorld().removeObject(this);
                    isAlive = false;
                    return;
                }
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
