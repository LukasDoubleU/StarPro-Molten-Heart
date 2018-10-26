
import greenfoot.*;

/**
 * Write a description of class KugelSchaden here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class BulletDamage extends Projectiles
{
    /**
     * Act - do whatever the KugelSchaden wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    
    int mov_speed;
    float schaden;
    Player target = null;
    boolean turnNotDone = true;
    
    public KugelSchaden(int newMov_Speed, float newSchaden, Player newTarget) {
        mov_speed = newMov_Speed;
        schaden = newSchaden;
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
