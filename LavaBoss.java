import greenfoot.*;

/**
 * Write a description of class LavaBoss here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class LavaBoss extends Boss
{
    
    public LavaBoss(){
        setImage("teddybear.png");
    }
    
    /**
     * Act - do whatever the LavaBoss wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        turn(2);
    }    
}
