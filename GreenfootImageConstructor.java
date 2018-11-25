import java.awt.Color;
import java.awt.Font;
import java.io.InputStream;

import greenfoot.GreenfootImage;

public class GreenfootImageConstructor {

    public GreenfootImageConstructor() {

    }

    public static GreenfootImage getConstructedImg(int fontSize, String text, Color c, int strLength) {
        Font font = new Font("q", 0, 0);
        InputStream is = Player.get().getClass().getResourceAsStream("/images/PressStart2P.ttf");// location in project
                                                                                                 // folder
        Font uniFont = null;
        try {
            uniFont = Font.createFont(Font.TRUETYPE_FONT, is);
        } catch (java.awt.FontFormatException r) {
            System.err.println("FontFormatException: " + r.getMessage());
        } catch (java.io.IOException r) {
            System.err.println("FontFormatException: " + r.getMessage());
        }
        font = uniFont.deriveFont(Float.valueOf(fontSize + "f"));// desired font size
        GreenfootImage img = new GreenfootImage(generatePlaceHolder(strLength), 50, Color.BLACK, c); // <- placeholder
                                                                                                     // for timer lul.
        // i dont get this sheet
        img.setFont(font);
        img.drawString(text, 30, 40);
        return img;
    }

    public static String generatePlaceHolder(int charAmount) {
        String str = "";
        for (int i = 0; i <= charAmount; i++) {
            str += "  ";
        }
        return str;
    }

}
