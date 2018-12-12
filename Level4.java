import greenfoot.*;

public class Level4 extends Level {

    private double[][] world = {
            /**
             * 18 Zeilen von Oben nach unten für 720 Pixel Die letzten 2 Zeilen sind für
             * Lifebar und Timer da
             *
             * 32 Elemente von Links nach Rechts für 1280 Pixel
             */
            { 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9 },
            { 9, 0, 0, 1.3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1.3, 0, 0, 0, 4, 0, 9, 0, 0, 3, 3, 9 },
            { 9, 0, 0, 1.3, 0, 0, 5, 0, 0, 0, 0, 4, 4, 4, 0, 0, 0, 0, 6, 0, 1.3, 0, 0, 0, 1.3, 0, 0, 0, 0, 3, 19, 9 },
            { 9, 0, 0, 1.3, 0, 0, 0, 0, 0, 0, 0, 4, 8, 4, 0, 0, 0, 0, 0, 0, 4, 0, 0, 0, 1.3, 1.3, 1.3, 1.3, 1.3, 1.3,
                    1.3, 9 },
            { 9, 0, 0, 1.3, 0, 0, 0, 0, 0, 0, 0, 4, 4, 4, 0, 1.3, 0, 0, 0, 0, 4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 9 },
            { 9, 0, 0, 1.3, 3, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1.3, 0, 0, 0, 0, 1.3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 9 },
            { 9, 0, 0, 1.3, 22, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1.3, 0, 0, 0, 0, 1.3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 9 },
            { 13, 0, 0, 7.3, 1.3, 1.3, 1.3, 4, 7.3, 1.3, 1.3, 1.3, 1.3, 1.3, 1.3, 1.3, 1.3, 1.3, 7.3, 1.3, 1.3, 0, 0, 0,
                    0, 0, 0, 0, 0, 10, 0, 11 },
            { 14, 0, 0, 1.3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 0, 0, 1.3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 12 },
            { 9, 0, 0, 1.3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 0, 20, 1.3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 9 },
            { 9, 0, 0, 1.3, 0, 0, 0, 0, 0, 0, 4, 4, 4, 0, 0, 0, 1.3, 1.3, 1.3, 1.3, 1.3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
                    9 },
            { 9, 0, 0, 1.3, 0, 0, 0, 0, 0, 0, 4, 8, 4, 0, 0, 0, 0, 0, 0, 0, 1.3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 9 },
            { 9, 0, 0, 7.3, 0, 0, 0, 0, 0, 0, 4, 4, 4, 0, 0, 0, 0, 0, 0, 0, 1.3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 9 },
            { 9, 0, 0, 0, 5, 0, 0, 0, 0, 0, 0, 0, 6, 0, 0, 0, 0, 0, 0, 0, 1.3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 9 },
            { 9, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1.3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 9 },
            { 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 1.3, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9 },
            { 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99,
                    99, 99, 99, 99, 99, 99 },
            { 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99,
                    99, 99, 99, 99, 99, 99 }, };

    /**
     * Constructor for objects of class Level3.
     * 
     */
    public Level4() {
        super("background_final.png");
        generateWorld(world);
    }

    @Override
    public void finish() {
        Greenfoot.setWorld(new GameOverLevel());
        // Greenfoot.setWorld(new StartingGame());
    }
}
