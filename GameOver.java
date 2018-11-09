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
        addObject(new Button("Restart Game"),640,600); 
    }
    
    /*
     * Der Finish macht bei Game Over nichts. 
     */
    public void finish(){}
}
