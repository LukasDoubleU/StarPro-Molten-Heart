
import greenfoot.*;

/**
 * Write a description of class Projectiles here.
 * 
 * @author (your name)
 * @version (a version number or a date)
 */
public abstract class Projectiles extends Melee {
    /**
     * Act - do whatever the Projectiles wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    boolean turnNotDone;

    public Projectiles(int movSpeed, int lifeCount, String imgPath) {
        super(movSpeed, lifeCount, imgPath);
        turnNotDone = true;

    }

    public void act() {
        // Add your action code here.
    }
}
