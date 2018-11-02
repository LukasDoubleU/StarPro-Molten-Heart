import greenfoot.*;

/**
 * Write a description of class DestroyableObstacle here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class DestroyableObstacle extends Enemy
{
    int lifeCount;
    
    public void act() 
    {
        
        
    }    
    
    public void damage(int damage) {
        lifeCount = lifeCount - damage;
        if(lifeCount < 0) {
            this.getWorld().removeObject(this);
        }
    }
}
