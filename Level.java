import greenfoot.*;

public abstract class Level extends World {

    public Level() {    
        super(800, 600, 1);
        addObject(new Barrel(), 100, 100);
        // das hier ist ein test
    }
}
