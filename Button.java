import greenfoot.*;

/**
 * Write a description of class Button here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Button extends Actor
{
    
    private String text; 
    
    private Button(){
        setImage("button.png");
    }
    
    /**
       *Erstellt einen Button mit einem Buttontext. 
       */
    public Button(String buttontext){
        this();
        this.text = buttontext; 
    }
    
    /**
     * Act - do whatever the Button wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        if(Greenfoot.mouseClicked(this)){
           ((GameOver) getWorld()).restartGame();
        }
    }    
}
