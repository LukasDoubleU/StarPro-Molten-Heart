import greenfoot.Greenfoot;

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
            { 9, 23.0, 23.1, 23.1, 23.1, 23.1, 23.1, 23.1, 23.1, 23.1, 23.1, 23.1, 23.1, 23.1, 23.1, 23.1, 23.1, 23.1,
                    23.1, 23.1, 23.1, 23.1, 23.1, 23.1, 23.1, 23.1, 23.1, 23.1, 23.1, 23.1, 23.2, 9 },
            { 9, 23.3, 23.4, 23.81, 23.7, 23.7, 23.7, 23.7, 23.7, 23.7, 23.7, 23.7, 23.7, 23.7, 23.7, 23.7, 23.7, 23.7,
                    23.7, 23.7, 23.7, 23.7, 23.7, 23.7, 23.7, 23.7, 23.7, 23.7, 23.61, 23.4, 23.5, 9 },
            { 9, 23.3, 23.4, 23.5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 23.3, 23.4,
                    23.5, 9 },
            { 9, 23.3, 23.81, 23.8, 0, 23.0, 23.1, 23.2, 0, 23.0, 23.2, 0, 23.0, 23.2, 0, 23.0, 23.2, 0, 23.0, 23.2, 0,
                    23.0, 23.2, 0, 23.0, 23.1, 23.2, 0, 23.6, 23.61, 23.5, 9 },
            { 9, 23.3, 23.5, 0, 0, 23.3, 23.4, 23.5, 0, 23.3, 23.5, 0, 23.3, 23.5, 0, 23.6, 23.8, 0, 23.3, 23.5, 0,
                    23.3, 23.5, 0, 23.3, 23.4, 23.5, 0, 0, 23.3, 23.5, 9 },
            { 9, 23.3, 23.5, 0, 0, 23.6, 23.7, 23.8, 0, 23.3, 23.5, 0, 23.6, 23.8, 0, 0, 0, 0, 23.6, 23.8, 0, 23.3,
                    23.5, 0, 23.6, 23.7, 23.8, 0, 0, 23.3, 23.5, 9 },
            { 9, 23.3, 23.5, 0, 0, 0, 0, 0, 0, 23.3, 23.5, 0, 0, 0, 0, 24, 0, 0, 0, 0, 0, 23.3, 23.5, 0, 0, 0, 0, 10, 0,
                    23.3, 23.5, 9 },
            { 9, 23.3, 23.5, 0, 0, 0, 0, 0, 0, 23.3, 23.5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 23.3, 23.5, 0, 0, 0, 0, 0, 0,
                    23.3, 23.5, 9 },
            { 9, 23.3, 23.5, 0, 0, 23.0, 23.1, 23.2, 0, 23.6, 23.8, 0, 23.0, 23.2, 0, 0, 0, 0, 23.0, 23.2, 0, 23.6,
                    23.8, 0, 23.0, 23.1, 23.2, 0, 0, 23.3, 23.5, 9 },
            { 9, 23.3, 23.5, 0, 0, 23.3, 23.4, 23.5, 0, 0, 0, 0, 23.3, 23.5, 0, 23.0, 23.2, 0, 23.3, 23.5, 0, 0, 0, 0,
                    23.3, 23.4, 23.5, 0, 0, 23.3, 23.5, 9 },
            { 9, 23.3, 23.21, 23.2, 0, 23.3, 23.4, 23.21, 23.1, 23.1, 23.2, 0, 23.3, 23.5, 0, 23.3, 23.5, 0, 23.3, 23.5,
                    0, 23.0, 23.1, 23.1, 23.01, 23.4, 23.5, 0, 23.0, 23.01, 23.5, 9 },
            { 9, 23.3, 23.4, 23.5, 0, 23.6, 23.7, 23.7, 23.7, 23.7, 23.8, 0, 23.6, 23.8, 0, 23.6, 23.8, 0, 23.6, 23.8,
                    0, 23.6, 23.7, 23.7, 23.7, 23.7, 23.8, 0, 23.3, 23.4, 23.5, 9 },
            { 9, 23.3, 23.4, 23.5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 23.3, 23.4,
                    23.5, 9 },
            { 9, 23.3, 23.4, 23.21, 23.1, 23.1, 23.1, 23.1, 23.1, 23.1, 23.1, 23.1, 23.1, 23.1, 23.1, 23.1, 23.1, 23.1,
                    23.1, 23.1, 23.1, 23.1, 23.1, 23.1, 23.1, 23.1, 23.1, 23.1, 23.01, 23.4, 23.5, 9 },
            { 9, 23.6, 23.7, 23.7, 23.7, 23.7, 23.7, 23.7, 23.7, 23.7, 23.7, 23.7, 23.7, 23.7, 23.7, 23.7, 23.7, 23.7,
                    23.7, 23.7, 23.7, 23.7, 23.7, 23.7, 23.7, 23.7, 23.7, 23.7, 23.7, 23.7, 23.8, 9 },
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
        setStatDisplay();
        generateWorld(world);
        generateWorld(new double[][] { {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {},
                { 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9 }, {},
                {}, });
    }

    @Override
    public void finish() {
        Level.BACKGROUND_SOUND.stop();
        if (Level.LAVA_SOUND.isPlaying()) {
            Level.LAVA_SOUND.stop();
        }
        Greenfoot.setWorld(new WinningScreenLevel());
    }
}
