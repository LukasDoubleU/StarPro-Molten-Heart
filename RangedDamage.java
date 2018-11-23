
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
    
    public RangedDamage(int moveSpeed, int lifeCount, String imgPath) {
        super(moveSpeed, lifeCount, imgPath);
        damage = 1;
        stalkRange = 150;
        viewDistance = 200;
    }
    
    public RangedDamage(int moveSpeed) {
        this(moveSpeed, 3, "eyeball.png");
    }
        
    
    public RangedDamage() {
        this(2, 3, "eyeball.png");
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
            moveSpeed = -moveSpeed;
        }
        if(counter==60){
            moveSpeed = -moveSpeed;
        }
        if(counter==90){
            moveSpeed = -moveSpeed;
        }
        if(counter==120){
            moveSpeed = -moveSpeed;
            counter = 0;
        }
        this.setLocation(this.getX()+moveSpeed, this.getY()+moveSpeed);
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
