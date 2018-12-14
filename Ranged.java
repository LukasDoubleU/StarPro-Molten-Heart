
import greenfoot.*;

/**
 * Write a description of class Ranged here.
 * 
 * @author (your name)
 * @version (a version number or a date)
 */
public abstract class Ranged extends Enemy {
    /**
     * Act - do whatever the Ranged wants to do. This method is called whenever the
     * 'Act' or 'Run' button gets pressed in the environment.
     */
    int shootCounter;
    
    public Ranged(int movSpeed, int lifeCount, String imgPath) {
        super(movSpeed, lifeCount, imgPath);
        shootCounter = 0;
    }
}
