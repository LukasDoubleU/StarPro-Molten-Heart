import greenfoot.*;

/**
 * Write a description of class StartingGame here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class StartingGame extends World
{

    /**
     * Constructor for objects of class StartingGame.
     * 
     */
    public StartingGame()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(1280, 720, 1); 
        setBackground("gameover.png"); 
        
        addObject(new Button(),50,80);
    }
}
