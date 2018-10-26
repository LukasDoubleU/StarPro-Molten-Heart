
import greenfoot.*;

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
    float damage;
    Player target = null;
    boolean turnNotDone = true;
    
    public BulletDamage(int newMov_Speed, float newdamage, Player newTarget) {
        mov_speed = newMov_Speed;
        damage = newdamage;
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
    }    
}
