import greenfoot.Actor;
import greenfoot.Greenfoot;
import greenfoot.World;
import java.util.List; 
import java.util.ArrayList; 
import java.util.Random; 

public abstract class Level extends World {

    /**
     * Default Welt 0 = World border 1 = Wand 2 = Spieler 3 = Gegnertyp 1 4 = Barrel
     * 5 = Wand 90° links 6 = Wand 90° rechts 7 = Wand 180°
     */

    private double[][] world = {
            /**
             * 18 Zeilen von Oben nach unten für 720 Pixel Die letzten 2 Zeilen sind für
             * Lifebar und Timer da
             *
             * 32 Elemente von Links nach Rechts für 1280 Pixel
             */
            { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 },
            { 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
            { 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
            { 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
            { 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
            { 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
            { 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
            { 13, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 11 },
            { 14, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 12 },
            { 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 4, 0, 0, 0, 0, 1 },
            { 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
            { 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
            { 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
            { 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
            { 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
            { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 },
            { 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 95, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99,
                    99, 99, 99, 99, 99, 99 },
            { 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99,
                    99, 99, 99, 99, 99, 99 }, };

    /**
     * Zählt die Anzahl Monster auf der Welt. Fällt diese auf 0 so wird ein
     * Weltwechsel gemacht. Default ist 0 wird aber bei der Erschaffung der Welt
     * hochgesetzt auf die reale Monsterzahl
     */
    public int monstercount = 0;

    public Level(String image) {
        super(1280, 720, 1);
        setBackground(image);
        // Zeige unten links die Leben des Spielers an
        addObject(PlayerHealth.get(), 30, 680);
        // Zeige unten rechts die Stats des Spielers an
        addObject(PlayerStats.get(), 1100, 700);
        // Mittig: Spielzeit
        addObject(TimerDisplay.get(), 640, 700);
        
    }

    /**
     * Wenn getriggert wird das Level hochgesetzt
     */
    public abstract void finish();

    /**
     * Neustarten des Spiels beim ersten Level
     */
    public static void restartGame() {
        Player.newInstance();
        Greenfoot.setWorld(new Level1());
    }

    /**
     * Startet die Game Over Welt beim Game Over und löscht vorher alle Objekte in
     * der Welt
     */
    public static void runGameOverWorld() {
        Greenfoot.setWorld(new GameOver());
    }

    public void generateWorld(double[][] nWorld) {
        if (nWorld != null) {
            this.world = nWorld;
        }
        if (world != null) {
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
    public void generateObject(double obj, int x, int y) {

        Actor object = null;
        if (obj == 1.1) {
            object = new Wall(1);
        } else if (obj == 1.2) {
            object = new Wall(2);
        } else if (obj == 1.3) {
            object = new Wall(3);
        } else if (obj == 1.4) {
            object = new Wall(4);
        } else if (obj == 1.5) {
            object = new Wall(5);
        } else if (obj == 2) {
            object = new DestroyableObstacle(1);
        } else if (obj == 3) {
            object = new DestroyableObstacle(2);
        } else if (obj == 4) {
            object = new DestroyableObstacle(3);
        } else if (obj == 5) {
            object = new MeleeDamage();
        } else if (obj == 6) {
            object = new RangedDamage();
        //Verlangsamungsgegner
        } else if (obj == 7) {
            object = new RangedSlow(obj); 
        }/** else if (obj == 8) {
        object = new RangedExplosion(); 
        }
        
        /**
           * else if (obj == 8) { object = new RangedExplosion(); }
           **/
        else if (obj == 9) {
            object = new Border();
        } else if (obj == 10) {
            object = Player.get();
        } else if (obj == 11) {
            object = new Door(11, false);
        } else if (obj == 12) {
            object = new Door(12, false);
        } else if (obj == 13) {
            object = new Door(13, true);
        } else if (obj == 14) {
            object = new Door(14, true);
        } else if (obj == 15) {
            object = new Armor.Bright();
        } else if (obj == 16) {
            object = new Armor.Dark();
        } else if (obj == 17) {
            object = new Sword.Big();
        } else if (obj == 18) {
            object = new Sword.Master();
        } else if (obj == 19) {
            object = new Potion.Damage();
        } else if (obj == 20) {
            object = new Potion.Health();
        } else if (obj == 21) {
            object = new Potion.Immortality();
        } else if (obj == 22) {
            object = new Potion.Speed();
        //Lavaboden
        } else if((int)obj == 23){
            /* Lavaboden kann beliebig vergrößert werden. dabei wird von einem 9-teiligen Quadrat ausgegangen
            23.0, 23.1, 23.2,
            23.3, 23,4, 23.5, = Lavaboden mit der Größe 3x3
            23.6, 23.7, 23.8 
            
            23.0, 23.2,= Lavaboden mit der Größe 2x2
            23.6, 23.8 
            
            */
            Lava lava = new Lava(obj);
            object = lava; 
            /**Lavaboden mit Wert 0(links-obere-Ecke) stellt den Aufruf für die spätere Lava dar. 
            Alle benachbarten LavaElemente entzünden sich dann einfach nur. 
            Will man dass das entsprechende LavaElement häufiger Lava spawn
            */
            if(obj == 23.0){
                lavarray.add(lava);
            }
        //Molten Heart
        }else if (obj == 95) {
            object = new MoltenHeart();
        }
        if (object != null) {
            addObject(object, 10 + x, 10 + y);
        }
        setPaintOrder(DestroyableObstacle.class);
        setPaintOrder(Enemy.class);
    }

    /**
     * Wird vom LavaBoss gespawnt um existierende Bereiche Zufällig mit Lava
     * auszuwechseln
     */
    public static void triggerLava() {
    }

    @Override
    public void act() {

    }
}
