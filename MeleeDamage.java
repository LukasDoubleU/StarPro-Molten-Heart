
import greenfoot.*;
import java.util.*;

public class MeleeDamage extends Melee {
   
    
    public MeleeDamage(int newMov_Speed, int newLifeCount, String imgPath) {
        super(newMov_Speed, newLifeCount, imgPath);
        damage = 1;
        stalkRange = 38;
        viewDistance = 200;
    }
    
    public MeleeDamage(int newMov_Speed) {
        this(newMov_Speed, 3, "ghost.png");
    }
    
    public MeleeDamage(){
        this(4, 3, "ghost.png");
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
        List playerInRange = new ArrayList();
        playerInRange = this.getObjectsInRange(stalkRange + 10, Player.class);     
        for(Object p : playerInRange) {
            if(p instanceof Player) {
                Player.get().damage(damage);
            }                
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
