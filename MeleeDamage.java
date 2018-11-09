
import greenfoot.*;

import java.util.*;

public class MeleeDamage extends Melee {
    
    int counter = 0;
    boolean lockedOntoPlayer = false;
    int mov_speed;
    int xMov_speed;
    int yMov_speed;
    int damage = 1;
    int lifeCount;
    Player target = null;
    
    public MeleeDamage(int newMov_Speed, int newLifeCount) {
        mov_speed = newMov_Speed;
        xMov_speed = mov_speed;
        yMov_speed = mov_speed;
        setImage("ghost.png");
        lifeCount = newLifeCount;
    }
    
    public MeleeDamage(int newMov_Speed) {
        this(newMov_Speed, 3);
    }
    
    public MeleeDamage(){
        this(3, 3);
    }

    public void act() {
        
        if(lockedOntoPlayer) {
            moveTowardsTarget();
            damagePlayer();
        }
        else {
            movePattern();
            List actorinrange = new ArrayList();
            actorinrange = this.getObjectsInRange(200, Player.class);
        
            for(Object a : actorinrange)  {
                if(a instanceof Player) {
                    lockedOntoPlayer = true;
                    target =(Player) a;
                }
            
            }
            
        }

    }
    
    public void moveTowardsTarget() {
        int oldX = this.getX();
        int oldY = this.getY();
        if(this.getX() > target.getX()) {
            this.setLocation(this.getX()-mov_speed, this.getY());
            if(checkCollision()) {
                this.setLocation(oldX, oldY);
            }
        }
        else {
            this.setLocation(this.getX()+mov_speed, this.getY());
            if(checkCollision()) {
                this.setLocation(oldX, oldY);
            }
        }
        if(this.getY() > target.getY()) {
            this.setLocation(this.getX(), this.getY()-mov_speed);
            if(checkCollision()) {
                this.setLocation(oldX, oldY);
            }
        }
        else {
            this.setLocation(this.getX(), this.getY()+mov_speed);
            if(checkCollision()) {
                this.setLocation(oldX, oldY);
            }
        }
        this.setRotation(0);
        counter++;
    }
    
    public void damagePlayer() {
        List intersectingObjects = new ArrayList();
        intersectingObjects = this.getIntersectingObjects(Player.class);
        
        for(Object a : intersectingObjects)  {
            if(a instanceof Player) {
                Player.get().damage(damage);
                
            }
            
        }
    }

    public void movePattern() {
        
        int oldX = this.getX();
        int oldY = this.getY();
        if(counter<=40){
            this.setLocation(this.getX()+xMov_speed, this.getY());
            if(checkCollision()) {
                this.setLocation(oldX, oldY);
            }
        }
        else if(counter<=80){
            this.setLocation(this.getX(), this.getY()+yMov_speed);
            if(checkCollision()) {
                this.setLocation(oldX, oldY);
            }
        }
        else if(counter<=120){
            this.setLocation(this.getX()-xMov_speed, this.getY());
            if(checkCollision()) {
                this.setLocation(oldX, oldY);
            }
        }
        else if(counter<=160){
            this.setLocation(this.getX(), this.getY()-yMov_speed);
            if(checkCollision()) {
                this.setLocation(oldX, oldY);
            }
        }
        else if(counter==161) {
            counter = 0;
        }
        counter++;
    }
    
    public boolean checkCollision() {
        List intersectingObjects = new ArrayList();
        intersectingObjects = this.getObjectsInRange(25, null); 
        
        for(Object a : intersectingObjects)  {          
            if(a instanceof Player) {
                return true;
            }
            if(a instanceof Obstacle) {
                return true;
            }
           
        }
        if(this.isAtEdge()) {
            return true;
        }
        return false;
    }
}
