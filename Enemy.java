
import greenfoot.*;
import java.util.*;

public abstract class Enemy extends Obstacle {
    
    int mov_speed;
    int xMov_speed;
    int yMov_speed;
    int damage;
    Player target = null;
    int lifeCount;
    int stalkRange;
    int counter;
    int viewDistance;
    
    
    public void damage(int damage) {
        lifeCount = lifeCount - damage;
        if(lifeCount < 0) {
            this.getWorld().removeObject(this);
        }
    }
    
    public void followTarget() {
        int oldX = this.getX();
        int oldY = this.getY();
        if(this.getX() > target.getX()) {
            this.setLocation(this.getX()-mov_speed+(mov_speed/2), this.getY());
            if(checkCollision(stalkRange)) {
                this.setLocation(oldX, oldY);
            }
        }
        else {
            this.setLocation(this.getX()+mov_speed-(mov_speed/2), this.getY());
            if(checkCollision(stalkRange)) {
                this.setLocation(oldX, oldY);
            }
        }
        if(this.getY() > target.getY()) {
            this.setLocation(this.getX(), this.getY()-mov_speed+(mov_speed/2));
            if(checkCollision(stalkRange)) {
                this.setLocation(oldX, oldY);
            }
        }
        else {
            this.setLocation(this.getX(), this.getY()+mov_speed-(mov_speed/2));
            if(checkCollision(stalkRange)) {
                this.setLocation(oldX, oldY);
            }
        }
        this.setRotation(0);
    }
    
    public boolean checkCollision(int stalkRange) {
        List intersectingObjects = new ArrayList();
        intersectingObjects = this.getObjectsInRange(25, null); 
        
        for(Object a : intersectingObjects)  {          
            if(a instanceof Obstacle) {
                return true;
            }
           
        }
        if(this.isAtEdge()) {
            return true;
        }
        
        List playerInRange = new ArrayList();
        playerInRange = this.getObjectsInRange(stalkRange, Player.class); 
        for(Object p: playerInRange)  {          
            if(p instanceof Player) {
                return true;
            }
           
        }
        return false;
    }
    
    public Player getTarget() {
        List actorinrange = new ArrayList();
        actorinrange = this.getObjectsInRange(viewDistance, Player.class);
        
        for(Object a : actorinrange)  {
            if(a instanceof Player) {
                return (Player)a;
            }
        
        }
        return null;
        
    }
    
}
