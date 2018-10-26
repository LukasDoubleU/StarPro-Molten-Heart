import greenfoot.*;
import javafx.scene.transform.Rotate;

public class Wall extends Obstacle {

    
    
    
    public Wall() {
        
        setImage("transparent.png");
        
    }
    
    public void Realwall(int x) {
        
        setRotation(x);
        setImage("realwall.png");
        
    }
    
    
    public void act() {
        
        
    }
    
    
}
