
import greenfoot.*;

public abstract class Enemy extends Obstacle {
    
    int mov_speed;
    float damage;
    Player target = null;
    int lifeCount;
    
    public void damage(int damage) {
        lifeCount = lifeCount - damage;
        if(lifeCount < 0) {
            this.getWorld().removeObject(this);
        }
    }
    
}
