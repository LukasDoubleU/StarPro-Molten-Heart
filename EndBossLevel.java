import greenfoot.*;

/**
 * Write a description of class Level3 here.
 * 
 * @author (your name)
 * @version (a version number or a date)
 */
public class EndBossLevel extends Level {

    private double[][] world = {
            /**
             * 18 Zeilen von Oben nach unten für 720 Pixel Die letzten 2 Zeilen sind für
             * Lifebar und Timer da
             *
             * 32 Elemente von Links nach Rechts für 1280 Pixel
             */
            { 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9 },
            { 9, 23.0, 23.1, 23.1, 23.1, 23.1, 23.2, 0, 0, 23.0, 23.1, 23.1, 23.2, 23.0, 23.1, 23.1, 23.1, 23.1, 23.1,
                    23.2, 23.0, 23.1, 23.1, 23.1, 23.1, 23.1, 23.1, 23.1, 23.1, 23.1, 23.2, 9 },
            { 9, 23.3, 23.4, 23.4, 23.4, 23.4, 23.5, 0, 0, 23.3, 23.4, 23.4, 23.5, 23.3, 23.4, 23.4, 23.4, 23.4, 23.4,
                    23.5, 23.3, 23.4, 23.4, 23.4, 23.4, 23.4, 23.4, 23.4, 23.4, 23.4, 23.5, 9 },
            { 9, 23.3, 23.4, 23.4, 23.4, 23.4, 23.5, 0, 0, 23.3, 23.4, 23.4, 23.5, 23.3, 23.4, 23.4, 23.4, 23.4, 23.4,
                    23.5, 23.3, 23.4, 23.4, 23.4, 23.4, 23.4, 23.4, 23.4, 23.4, 23.4, 23.5, 9 },
            { 9, 23.3, 23.4, 23.4, 23.4, 23.4, 23.21, 23.1, 23.1, 23.01, 23.4, 23.4, 23.5, 23.3, 23.4, 23.4, 23.4, 23.4,
                    23.4, 23.5, 23.3, 23.4, 23.4, 23.4, 23.4, 23.4, 23.4, 23.4, 23.4, 23.4, 23.5, 9 },
            { 9, 23.3, 23.4, 23.4, 23.4, 23.4, 23.4, 23.4, 23.4, 23.4, 23.4, 23.4, 23.5, 23.3, 23.4, 23.4, 23.4, 23.4,
                    23.4, 23.5, 23.3, 23.4, 23.4, 23.4, 23.4, 23.81, 23.7, 23.7, 23.7, 23.61, 23.5, 9 },
            { 9, 23.3, 23.4, 23.4, 23.4, 23.4, 23.4, 23.4, 23.4, 23.4, 23.4, 23.4, 23.5, 23.3, 23.4, 23.81, 23.7, 23.7,
                    23.7, 23.8, 23.6, 23.7, 23.7, 23.7, 23.7, 23.8, 0, 0, 0, 23.3, 23.5, 9 },
            { 9, 23.6, 23.7, 23.7, 23.7, 23.7, 23.7, 23.7, 23.7, 23.7, 23.7, 23.7, 23.8, 23.3, 23.4, 23.5, 24, 23.0,
                    23.1, 23.1, 23.1, 23.1, 23.2, 23.0, 23.1, 23.2, 0, 10, 0, 23.6, 23.8, 9 },
            { 9, 23.0, 23.1, 23.1, 23.1, 23.1, 23.1, 23.1, 23.1, 23.2, 23.0, 23.1, 23.1, 23.01, 23.4, 23.21, 23.1,
                    23.01, 23.4, 23.4, 23.4, 23.4, 23.5, 23.3, 23.4, 23.5, 0, 0, 0, 23.0, 23.2, 9 },
            { 9, 23.3, 23.4, 23.4, 23.4, 23.4, 23.4, 23.4, 23.4, 23.5, 23.3, 23.4, 23.4, 23.4, 23.4, 23.4, 23.4, 23.4,
                    23.4, 23.4, 23.4, 23.4, 23.5, 23.3, 23.4, 23.21, 23.1, 23.1, 23.1, 23.01, 23.5, 9 },
            { 9, 23.3, 23.4, 23.4, 23.4, 23.4, 23.4, 23.4, 23.4, 23.5, 23.3, 23.4, 23.4, 23.4, 23.4, 23.4, 23.4, 23.81,
                    23.7, 23.7, 23.61, 23.4, 23.5, 23.3, 23.4, 23.4, 23.4, 23.4, 23.4, 23.4, 23.5, 9 },
            { 9, 23.3, 23.4, 23.4, 23.4, 23.4, 23.4, 23.4, 23.81, 23.8, 23.6, 23.61, 23.4, 23.4, 23.4, 23.4, 23.4, 23.5,
                    0, 0, 23.3, 23.4, 23.5, 23.3, 23.4, 23.4, 23.4, 23.4, 23.4, 23.4, 23.5, 9 },
            { 9, 23.3, 23.4, 23.4, 23.4, 23.4, 23.4, 23.4, 23.5, 0, 0, 23.3, 23.4, 23.4, 23.4, 23.4, 23.4, 23.5, 0, 0,
                    23.3, 23.4, 23.5, 23.3, 23.4, 23.4, 23.4, 23.4, 23.4, 23.4, 23.5, 9 },
            { 9, 23.6, 23.7, 23.61, 23.4, 23.4, 23.4, 23.4, 23.5, 0, 0, 23.3, 23.4, 23.4, 23.4, 23.4, 23.4, 23.21, 23.1,
                    23.1, 23.01, 23.4, 23.5, 23.3, 23.4, 23.4, 23.4, 23.4, 23.4, 23.4, 23.5, 9 },
            { 9, 0, 0, 23.3, 23.4, 23.4, 23.4, 23.4, 23.21, 23.1, 23.1, 23.01, 23.4, 23.4, 23.4, 23.4, 23.4, 23.4, 23.4,
                    23.4, 23.4, 23.4, 23.5, 23.3, 23.4, 23.4, 23.4, 23.4, 23.4, 23.4, 23.5, 9 },
            { 9, 0, 0, 23.6, 23.7, 23.7, 23.7, 23.7, 23.7, 23.7, 23.7, 23.7, 23.7, 23.7, 23.7, 23.7, 23.7, 23.7, 23.7,
                    23.7, 23.7, 23.7, 23.8, 23.6, 23.7, 23.7, 23.7, 23.7, 23.7, 23.7, 23.8, 9 },
            { 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99,
                    99, 99, 99, 99, 99, 99 },
            { 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99,
                    99, 99, 99, 99, 99, 99 }, };

    /**
     * Constructor for objects of class Level3.
     * 
     */
    public EndBossLevel() {
        super("background_final.png");
        generateWorld(world);
    }

    @Override
    public void finish() {
        Greenfoot.setWorld(new WinningScreenLevel());
    }
}
