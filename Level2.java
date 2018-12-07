import greenfoot.*;

public class Level2 extends Level {

    /**
     * Default Welt 0 = Leer 1 = Wand 2 = Spieler 3 = Gegnertyp 1 4 = Barrel 5 =
     * Wand 90° links 6 = Wand 90° rechts 7 = Wand 180°
     */

    private double[][] world = {
            /**
             * 18 Zeilen von Oben nach unten für 720 Pixel Die letzten 2 Zeilen sind für
             * Lifebar und Timer da
             *
             * 31 Elemente von Links nach Rechts für 1280 Pixel
             */
            { 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9 },
            { 9, 20, 17, 0, 1.3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1.3, 0, 6, 0, 0, 1.3, 0, 0, 0, 0, 0, 9 },
            { 9, 0, 0, 0, 1.3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1.3, 0, 0, 0, 0, 1.3, 0, 0, 0, 0, 0, 9 },
            { 9, 4, 4, 4, 1.3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1.3, 0, 0, 0, 0, 1.3, 0, 0, 0, 0, 0, 9 },
            { 9, 0, 0, 7.3, 1.3, 0, 6, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1.3, 0, 0, 0, 0, 1.3, 0, 0, 0, 0, 0, 9 },
            { 9, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1.3, 0, 0, 0, 0, 1.3, 0, 0, 0, 0, 0, 9 },
            { 9, 1.3, 1.3, 1.3, 1.3, 1.3, 1.3, 1.3, 1.3, 1.3, 1.3, 1.3, 1.3, 0, 0, 0, 1.3, 1.3, 1.3, 1.3, 1.3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 9 },
            { 13, 3, 3, 1.3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1.3, 0, 0, 0, 0, 0, 11 },
            { 14, 6, 6, 1.3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1.3, 0, 0, 0, 0, 10, 12 },
            { 9, 3, 3, 1.3, 0, 0, 1.3, 1.3, 1.3, 1.3, 1.3, 1.3, 7.3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1.3, 0, 0, 0, 0, 0, 9 },
            { 9, 0, 0, 1.3, 0, 0, 0, 0, 0, 0, 0, 0, 1.3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 7.3, 0, 0, 0, 0, 0, 9 },
            { 9, 0, 0, 1.3, 0, 0, 0, 0, 0, 0, 0, 0, 1.3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1.3, 0, 0, 0, 0, 0, 9 },
            { 9, 0, 0, 7.3, 1.3, 1.3, 1.3, 1.3, 1.3, 0, 0, 0, 1.3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1.3, 1.3, 1.3, 1.3, 1.3, 1.3, 0, 0, 0, 9 },
            { 9, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1.3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3, 21, 1.3, 0, 0, 0, 0, 9 },
            { 9, 5, 0, 0, 5, 0, 0, 0, 0, 0, 0, 0, 1.3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3, 15, 1.3, 0, 0, 0, 0, 9 },
            { 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 1.4, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 1.3, 9, 9, 9, 9, 9 },
            { 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99,
                    99, 99, 99, 99, 99 },
            { 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99,
                    99, 99, 99, 99, 99, 99 }, };

    public Level2() {
        super("background_final.png");
        generateWorld(world);
    }

    public void finish() {
        if (monstercount == 0) {
            Greenfoot.setWorld(new MiniBossLevel());
        }
    }
}
