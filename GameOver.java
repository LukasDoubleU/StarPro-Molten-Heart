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
        super(true);
        setBackground("gameover.png"); 
    }
    
    /*
     * Der Finish macht bei Game Over nichts. 
     */
    public void finish(){}
}
