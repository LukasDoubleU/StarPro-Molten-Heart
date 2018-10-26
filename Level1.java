import greenfoot.*;

public class Level1 extends Level {
    
    public Level1() {
        super();
        setBackground("background_final.png");
    }

    public void finish(){
        Greenfoot.setWorld(new Level2());
    }
}
