import greenfoot.*;

/**
 * Write a description of class Melee here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public abstract class Melee extends Enemy
{
    /**
     * Act - do whatever the Melee wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    
    public Melee(int moveSpeed, int newLifeCount, String imgPath) {
        super(moveSpeed, newLifeCount, imgPath);
    }
    
    public void act() 
    {
        // Add your action code here.
    }    
}
