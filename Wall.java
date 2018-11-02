import greenfoot.*;

public class Wall extends Obstacle {

    
    
    
    public Wall() {
        
        setImage("door/door_left_headingEast.png");
        
    }
    
    public void Realwall(int x) {
        
        setRotation(x);
        setImage("realwall.png");
        
    }
    
    
    public void act() {
        
        
    }
    
    
}
