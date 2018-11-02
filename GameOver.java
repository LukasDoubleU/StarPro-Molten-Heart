import greenfoot.*;

/**
 * Write a description of class GameOver here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class GameOver extends Level
{
    
    /**
     * Constructor for objects of class GameOver.
     * 
     */
    public GameOver(){
        super("gameover.png");
        addObject(new Button(),50,80); 
    }
    
    /*
     * Der Finish macht bei Game Over nichts. 
     */
    public void finish(){}
    
    public void restartGame(){
        Greenfoot.setWorld(new Level1());
    }
}
