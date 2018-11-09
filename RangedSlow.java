import greenfoot.*;
import java.util.*;

/**
 * Write a description of class RangedSlow here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class RangedSlow extends Ranged
{
    /**
     * Act - do whatever the RangedSlow wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    int counter = 0;
    int mov_speed;
    int xMov_speed;
    int yMov_speed;
    int slow;
    int lifeCount;
    Player target = null;
    
    public RangedSlow(int newMov_Speed, int newLifeCount) {
        mov_speed = newMov_Speed;
        slow = 4;
        setImage("spider.png");
        lifeCount = newLifeCount;
        xMov_speed = mov_speed;
        yMov_speed = mov_speed;
    }
    
    public RangedSlow(int newMov_Speed) {
        this(newMov_Speed, 3);
    }
        
    
    public RangedSlow() {
        this(3, 3);
    }
        
    public void act() 
    {
        if(target == null) {
            target = getTarget();
        }
        
        if(target != null) {
            if(!checkCollision()) {
                moveTowardsTarget();
            }
            else {
                counter++;
            }
            if(counter == 100) {
                spawnBullet();
            }
        }else {
            if(!checkCollision()) {
                movePattern();
            }
        }
        
        
        
      
    }

    public void movePattern() {
        int oldX = this.getX();
        int oldY = this.getY();
        if(counter==30){
            xMov_speed = -xMov_speed;
        }
        if(counter==60){
            yMov_speed = -yMov_speed;
        }
        if(counter==90){
            xMov_speed = -xMov_speed;
        }
        if(counter==120){
            yMov_speed = -yMov_speed;
            counter = 0;
        }
        this.setLocation(this.getX()+xMov_speed, this.getY()+yMov_speed);
        if(checkCollision()) {
                this.setLocation(oldX, oldY);
        }
        counter = counter + 1;
    }
    
    public void moveTowardsTarget() {
        int oldX = this.getX();
        int oldY = this.getY();
        if(this.getX() > target.getX()) {
            this.setLocation(this.getX()-mov_speed+(mov_speed/2), this.getY());
            if(checkCollision()) {
                this.setLocation(oldX, oldY);
            }
        }
        else {
            this.setLocation(this.getX()+mov_speed-(mov_speed/2), this.getY());
            if(checkCollision()) {
                this.setLocation(oldX, oldY);
            }
        }
        if(this.getY() > target.getY()) {
            this.setLocation(this.getX(), this.getY()-mov_speed+(mov_speed/2));
            if(checkCollision()) {
                this.setLocation(oldX, oldY);
            }
        }
        else {
            this.setLocation(this.getX(), this.getY()+mov_speed-(mov_speed/2));
            if(checkCollision()) {
                this.setLocation(oldX, oldY);
            }
        }
        this.setRotation(0);
        counter++;
    }
    
    public void spawnBullet() {
        BulletSlow b = new BulletSlow(6, slow, target, "spider_web.png");
        this.getWorld().addObject(b, this.getX(), this.getY());
        counter = 0;
    }
    
    public Player getTarget() {
        List actorinrange = new ArrayList();
        actorinrange = this.getObjectsInRange(400, Player.class);
        
        for(Object a : actorinrange)  {
            if(a instanceof Player) {
              
                return (Player) a;
            }
            
        }
        return null;
        
    }
    
    public boolean checkCollision() {
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
        playerInRange = this.getObjectsInRange(150, Player.class); 
        for(Object p: playerInRange)  {          
            if(p instanceof Player) {
                return true;
            }
           
        }
        return false;
    }
}
