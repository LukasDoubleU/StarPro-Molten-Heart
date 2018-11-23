import java.awt.Color;

import greenfoot.Actor;
import greenfoot.Greenfoot;
import greenfoot.GreenfootImage;

public abstract class Button extends Actor {

    private String text;

    private Button() {
        setImage("button.png");
    }

    /**
     * Erstellt einen Button mit einem Buttontext.
     */
    public Button(String buttontext) {
        this(250, 50, buttontext);
    }

    public Button(int width, int height, String text) {
        this();
        this.text = text;
        int cursorPosition = text.length();
        // getImage().clear();
        getImage().scale(width, height);
        int textFontSize = height - 10;
        GreenfootImage textImage = new GreenfootImage(text, textFontSize, Color.black, new Color(0, 0, 0, 0));
        GreenfootImage textBeforeCursor = new GreenfootImage(text.substring(0, 0), textFontSize, Color.black,
                new Color(0, 0, 0, 0));
        getImage().drawImage(textImage,
                textImage.getWidth() > getImage().getWidth() - 10 ? -(textImage.getWidth() - getImage().getWidth()) - 10
                        : 5,
                getImage().getHeight() / 2 - textImage.getHeight() / 2);
        getImage().setColor(Color.black);
        getImage().fillRect(0, 0, 3, getImage().getHeight() - 2);
    }

    @Override
    public void act() {
        if (Greenfoot.mouseClicked(this)) {
            onClick();
        }
    }

    public abstract void onClick();

}
