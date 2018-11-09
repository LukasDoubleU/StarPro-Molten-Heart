import greenfoot.*;

/**
 * Write a description of class StartingGame here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class StartingGame extends Level
{

    /**
     * Constructor for objects of class StartingGame.
     * 
     */
    public StartingGame()
    {    
        super("Start_Game.png");
        addObject(new Button("Start Game"),640,600);
    }
        
    /*
     * Der Finish macht bei Game Over nichts. 
     */
    public void finish(){}
}
