
import greenfoot.*;

import java.util.*;

public class MeleeDamage extends Melee {
   
    
    public MeleeDamage(int newMov_Speed, int newLifeCount) {
        mov_speed = newMov_Speed;
        xMov_speed = mov_speed;
        yMov_speed = mov_speed;
        damage = 1;
        setImage("ghost.png");
        lifeCount = newLifeCount;
        stalkRange = 38;
        viewDistance = 200;
        counter = 0;
    }
    
    public MeleeDamage(int newMov_Speed) {
        this(newMov_Speed, 3);
    }
    
    public MeleeDamage(){
        this(3, 3);
    }

    public void act() {
        
        if(target != null) {
            followTarget();
            damagePlayer();
        }
        else {
            movePattern();
            target = getTarget();
            
        }

    }

    public void damagePlayer() {
        Object o = this.getOneIntersectingObject(Player.class);
        
        
        if(o instanceof Player) {
            Player.get().damage(damage);
                
        }
            
    }
    
    public void damage(int damage) {
        lifeCount = lifeCount - damage;
        if(lifeCount < 0) {
            this.getWorld().removeObject(this);
        }
    }
    
    public void movePattern() {
        
        int oldX = this.getX();
        int oldY = this.getY();
        if(counter<=40){
            this.setLocation(this.getX()+xMov_speed, this.getY());
            if(checkCollision(stalkRange)) {
                this.setLocation(oldX, oldY);
            }
        }
        else if(counter<=80){
            this.setLocation(this.getX(), this.getY()+yMov_speed);
            if(checkCollision(stalkRange)) {
                this.setLocation(oldX, oldY);
            }
        }
        else if(counter<=120){
            this.setLocation(this.getX()-xMov_speed, this.getY());
            if(checkCollision(stalkRange)) {
                this.setLocation(oldX, oldY);
            }
        }
        else if(counter<=160){
            this.setLocation(this.getX(), this.getY()-yMov_speed);
            if(checkCollision(stalkRange)) {
                this.setLocation(oldX, oldY);
            }
        }
        else if(counter==161) {
            counter = 0;
        }
        counter++;
    }
    
  
}
