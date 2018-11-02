import greenfoot.*;
import java.util.List;

public abstract class Level extends World {

    /**
     * Default Welt 0 = Leer 1 = Wand 2 = Spieler 3 = Gegnertyp 1 4 = Barrel 5 =
     * Wand 90° links 6 = Wand 90° rechts 7 = Wand 180°
     */

    private int[][] world = {
            /**
             * 18 Zeilen von Oben nach unten für 720 Pixel Die letzten 2 Zeilen sind für
             * Lifebar und Timer da
             *
             * 31 Elemente von Links nach Rechts für 1280 Pixel
             */
            { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 },
            { 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
            { 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
            { 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 4, 0, 0, 0, 0, 0, 1 },
            { 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
            { 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3, 1 },
            { 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
            {13, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 11},
            {14, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 12},
            { 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 4, 0, 0, 0, 0, 1 },
            { 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
            { 1, 0, 0, 0, 0, 0, 0, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
            { 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 0, 1 },
            { 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3, 1 },
            { 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
            { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 },
            { 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 95, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99,
                99, 99, 99, 99, 99, 99 },
            { 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99,
                99, 99, 99, 99, 99, 99 }, };

    /**
     * Zählt die Anzahl Monster auf der Welt. Fällt diese auf 0 so wird ein Weltwechsel gemacht.
     * Default ist 0 wird aber bei der Erschaffung der Welt hochgesetzt auf die reale Monsterzahl
     */
    private int monstercount = 0; 

    public Level(String image) {
        super(1280, 720, 1); 
        setBackground(image);
    }
    
    /**
     * Wenn getriggert wird das Level hochgesetzt
     */
    public abstract void finish();
    
    /**
     * Neustarten des Spiels beim ersten Level  
     */
    public static void restartGame(){
        Greenfoot.setWorld(new Level1());
    }
    
    /**
     * Startet die Game Over Welt beim Game Over und löscht vorher alle Objekte in der Welt
     */
    public static void runGameOverWorld(){
        Greenfoot.setWorld(new GameOver());
    }

    public void generateWorld(int[][] nWorld) {
        if(nWorld != null){this.world = nWorld;}
        if(world != null){
            for (int j = world.length - 1; j != -1; j--) {
                for (int i = world[j].length - 1; i != -1; i--) {
                    if (world[j][i] != 0) {
                        generateObject(world[j][i], i * 40, j * 40);
                    }
                }
            }
        }
    }

    /**
     * Generiert alle Objekte die sich für die Welt
     */
    public void generateObject(int obj, int x, int y) {
        
            Actor object = null;
            if (obj == 1) {
                object = new Wall();
            }   else if (obj == 2) {
                object = new DestroyableObstacle(1);
            }   else if (obj == 3) {
                object = new DestroyableObstacle(2);
            }   else if (obj == 4) {
                object = new DestroyableObstacle(3);
            }   else if (obj == 10) {
                object = Player.get();
            }   else if (obj == 11) {
                object = new Door(11);
            }  else if (obj == 12) {
                object = new Door(12);
            }  else if (obj == 12) {
                object = new Door(13);
            }  else if (obj == 13) {
                object = new Door(13);
            }  else if (obj == 14) {
                object = new Door(14);
            } else if (obj == 95){
                object = new MoltenHeart();
            }

            if (object != null) {
                addObject(object, 20 + x, 20 + y);
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
