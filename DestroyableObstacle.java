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
    
    public DestroyableObstacle(int x) {
        if(x==1) {
            setImage("img_utilities/rock.png");
        } else if (x==2) {
            setImage("img_utilities/barrel.png");
        } else if (x==3) {
            setImage("img_utilities/slime.png");
        }
    }
    
    public void damage(int damage) {
        lifeCount = lifeCount - damage;
        if(lifeCount < 0) {
            this.getWorld().removeObject(this);
        }
    }
}
