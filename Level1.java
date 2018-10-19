import greenfoot.*;

public class Level1 extends Level {

    public Level1() {
        addObject(new Player(), 100, 100);
    }

    public void finish(){
        Greenfoot.setWorld(new Level2());
    }
}
