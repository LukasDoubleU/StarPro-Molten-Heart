import greenfoot.*;
import java.awt.Color;

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
    /*public Button(String buttontext){
        this();
        this.text = buttontext; 
    }*/
    public Button(String buttontext) {
        this(250, 50, buttontext);
    }
    
    public Button(int width, int height, String text){
        this();
        this.text = text;
        int cursorPosition = text.length();
        //getImage().clear();
        getImage().scale(width, height);
        int textFontSize = height - 10;
        GreenfootImage textImage = new GreenfootImage(text, textFontSize, Color.black, new Color(0, 0, 0, 0));
        GreenfootImage textBeforeCursor = new GreenfootImage(text.substring(0, 0), textFontSize, Color.black, new Color(0, 0, 0, 0));
        getImage().drawImage(textImage, (textImage.getWidth() > getImage().getWidth() - 10 ? -(textImage.getWidth() - getImage().getWidth()) - 10 : 5), (getImage().getHeight() / 2 - textImage.getHeight() / 2));
        getImage().setColor(Color.black);
        getImage().fillRect(0, 0, 3, getImage().getHeight()-2);
    }
    
    /**
     * Act - do whatever the Button wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        if(Greenfoot.mouseClicked(this)){
           Level.restartGame();
        }
    }    
}
