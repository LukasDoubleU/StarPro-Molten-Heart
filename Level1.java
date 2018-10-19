
import greenfoot.*;

public class Level1 extends Level {
    
    public Level1() {
        generateWorld();
    }

    public void finish(){
        Greenfoot.setWorld(new Level2());
    }
}
