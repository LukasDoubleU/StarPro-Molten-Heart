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
    int slow;
    
    public RangedSlow(int newMov_Speed, int newLifeCount) {
        mov_speed = newMov_Speed;
        slow = 4;
        setImage("spider.png");
        lifeCount = newLifeCount;
        xMov_speed = mov_speed;
        yMov_speed = mov_speed;
        stalkRange = 150;
        viewDistance = 200;
        counter = 0;
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
            if(!checkCollision(stalkRange)) {
                followTarget();
            }

            if(counter == 100) {
                spawnBullet();
            }
        }else {
            if(!checkCollision(stalkRange)) {
                movePattern();
            }
        }
        counter++;
        
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
        if(checkCollision(stalkRange)) {
                this.setLocation(oldX, oldY);
        }
    }
    
    public void spawnBullet() {
        BulletSlow b = new BulletSlow(6, slow, target, "spider_web.png");
        this.getWorld().addObject(b, this.getX(), this.getY());
        counter = 0;
    }
    

}
