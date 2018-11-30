import java.awt.Color;
import java.awt.Font;
import java.io.InputStream;

import greenfoot.GreenfootImage;

public class GreenfootImageConstructor {

    private GreenfootImageConstructor() {
    }

    public static GreenfootImage getConstructedImg(int fontSize, String text, Color c) {
        // Lade die eigene Font als InputStream
        InputStream is = Player.get().getClass().getResourceAsStream("/images/PressStart2P.ttf");
        Font uniFont = null;
        try {
            // Lade den InputStream in eine Font
            uniFont = Font.createFont(Font.TRUETYPE_FONT, is);
        } catch (java.awt.FontFormatException r) {
            System.err.println("FontFormatException: " + r.getMessage());
        } catch (java.io.IOException r) {
            System.err.println("FontFormatException: " + r.getMessage());
        }
        // Erzeuge eine Derived-Font, bei der die Schriftgröße angegeben werden kann
        Font font = uniFont.deriveFont((float) fontSize);
        // Erzeuge ein Bild auf das der Text geschrieben werden kann
        GreenfootImage img = new GreenfootImage(generatePlaceHolder(text), 50, Color.BLACK, c);
        // Schreibe den gewünschten Text auf das Bild
        img.setFont(font);
        img.drawString(text, 0, img.getHeight());
        return img;
    }

    private static String generatePlaceHolder(String text) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < text.length(); i++) {
            sb.append("  ");
        }
        return sb.toString();
    }

}
