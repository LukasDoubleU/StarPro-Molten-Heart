import greenfoot.*;

public abstract class Level extends World {

    /**
     * Default Welt 0 = Leer 1 = Wand 2 = Spieler 3 = Gegnertyp 1 4 = Barrel 5 =
     * Wand 90° links 6 = Wand 90° rechts 7 = Wand 180°
     */

    private int[][] world = {
            /**
             * 16 Zeilen von Oben nach unten für 720 Pixel Die letzten 2 Zeilen sind für
             * Lifebar und Timer da
             *
             * 31 Elemente von Links nach Rechts für 1280 Pixel
             */
            { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 },
            { 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
            { 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
            { 1, 0, 0, 0, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
            { 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
            { 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
            { 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
            { 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
            { 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
            { 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
            { 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
            { 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
            { 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
            { 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
            { 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
            { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 },
            { 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99,
                    99, 99, 99, 99, 99, 99 },
            { 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99,
                    99, 99, 99, 99, 99, 99 }, };

    /**
     * Zählt die Anzahl Monster auf de  r Welt. Fällt diese auf 0 so wird ein Weltwechsel gemacht.
     * Default ist 0 wird aber bei der Erschaffung der Welt hochgesetzt auf die reale Monsterzahl
     */
    private int monstercount = 0; 
    
    public Level() {
        super(1280, 720, 1);
        setBackground("background_final.png");
    }

    /**
     * Wenn getriggert wird das Level hochgesetzt
     */
    public abstract void finish();

    /**
     * Startet die Game Over Welt beim Game Over
     */
    public void runGameOverWorld(){
       
    }
    
    public void generateWorld() {
        for (int j = world.length - 1; j != -1; j--) {
            for (int i = world[j].length - 1; i != -1; i--) {
                if (world[j][i] != 0) {
                    generateObject(world[j][i], i * 40, j * 40);
                }
            }
        }
    }

    /**
     * Generiert alle Objekte die sich für die Welt
     */
    public void generateObject(int obj, int x, int y) {
        if (obj <= 9) {
            Actor object = null;
            if (obj == 1) {
                object = new Wall();
            } else if (obj == 2) {
                object = Player.get();
            } else if (obj == 3) {
                object = new Bomb(2);
            } else if (obj == 4) {
                object = new Barrel();
            }

            if (object != null) {
                addObject(object, 20 + x, 20 + y);
            }
        }
        /* 10 bis 99 generiert allerlei andere objekte */
        else if (obj > 9) {
            /* Wird für den Hintergrund des unteren Menus genutzt */
            if (obj == 99) {
                // addImage()
            }
        }
    }
}
