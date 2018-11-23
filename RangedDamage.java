
import greenfoot.*;

import java.util.*;

/**
 * Write a description of class Kamel here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class RangedDamage extends Ranged
{
    /**
     * Act - do whatever the Kamel wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    
    public RangedDamage(int newMov_speed, int newLifeCount, String imgPath) {
        super(newMov_speed, newLifeCount, imgPath);
        damage = 1;
        stalkRange = 150;
        viewDistance = 200;
    }
    
    public RangedDamage(int newMov_Speed) {
        this(newMov_Speed, 3, "eyeball.png");
    }
        
    
    public RangedDamage() {
        this(3, 3, "eyeball.png");
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
            
            if(counter >= 100) {
                spawnBullet();
            }
        }
        else {
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
        BulletDamage b = new BulletDamage(4, damage, target, "tear.png");
        this.getWorld().addObject(b, this.getX(), this.getY());
        counter = 0;
    }
}
