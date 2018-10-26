
import java.util.List;

import greenfoot.*;

/**
 * Die vom Spieler gesteuerte Figur. Ist ein Singleton, d.h. es gibt nur eine
 * Instanz dieser Klasse. Auf diese kann �ber
 */
public class Player extends Actor {

    private static final Player INSTANCE = new Player();

    /**
     * Gibt die Instanz der Spielers zur�ck. Diese ist "permanent".
     */
    public static Player get() {
        return INSTANCE;
    }

    int lifeCount = 4;
    int moveSpeed = 10;
    Item equippedItem = new Sword();

    int oldX, oldY, oldRotation;

    /*
     * Variablen f�r die Bilder zur Laufanimation
     */
    String prefix = "soldier_bright/image_part_", suffix = ".png";
    int firstUp = 1, lastUp = 9;
    int firstLeft = 10, lastLeft = 18;
    int firstDown = 19, lastDown = 27;
    int firstRight = 28, lastRight = 36;
    int currentImageIndex;
    GreenfootImage[] imageCache = new GreenfootImage[37];
    {
        for (int i = 1; i < 37; i++) {
            imageCache[i] = new GreenfootImage(prefix + String.format("%03d", i) + suffix);
        }
    }

    private Player() {
        setImage(19);
    }

    private void setImage(int index) {
        setImage(imageCache[currentImageIndex = index]);
    }

    @Override
    public void act() {
        rememberPosition();
        move();
        checkCollision();
    }

    /**
     * F�gt dem Spieler Schaden zu (zieht ihm Leben ab)
     */
    public void damage(int dmg) {
        // TODO: Gegner Team zur Verf�gung stellen, Parameter definieren
    }

    /**
     * Slow wird über Zeit abgebaut, verringert den moveSpeed
     */
    public void slow(int amount) {
        // TODO: Implemetieren. Wird vom Gegner Team verwendet
    }

    /**
     * Merkt sich die aktuelle Position & Blickrichtung des Spielers
     */
    private void rememberPosition() {
        oldX = getX();
        oldY = getY();
        oldRotation = getRotation();
    }

    /**
     * Setzt die Position und Blickrichtung auf die letzten bekannten Werte zur�ck.
     */
    private void resetPosition() {
        setLocation(oldX, oldY);
        setRotation(oldRotation);
    }

    /**
     * Pr�ft & reagiert auf Kollisionen
     */
    private void checkCollision() {
        checkObstacle();
    }

    /**
     * Pr�ft & reagiert auf Kollision mit Hindernissen
     */
    private void checkObstacle() {
        List<Obstacle> obstacles = getIntersectingObjects(Obstacle.class);
        // Kollidieren wir mit irgendeinem Hinderniss?
        if (!obstacles.isEmpty()) {
            resetPosition();
        }
    }

    /**
     * F�hrt eine Bewegung in Abh�ngigkeit zu den gedr�ckten Tasten aus
     */
    private void move() {

        String key = Greenfoot.getKey();
        if (key == null) {
            return;
        }
        if ("w".equals(key) || "up".equals(key)) {
            setLocation(getX(), getY() - moveSpeed);
            setNextImage(firstUp, lastUp);
        }
        if ("a".equals(key) || "left".equals(key)) {
            setLocation(getX() - moveSpeed, getY());
            setNextImage(firstLeft, lastLeft);
        }
        if ("s".equals(key) || "down".equals(key)) {
            setLocation(getX(), getY() + moveSpeed);
            setNextImage(firstDown, lastDown);
        }
        if ("d".equals(key) || "right".equals(key)) {
            setLocation(getX() + moveSpeed, getY());
            setNextImage(firstRight, lastRight);
        }

        // TODO Das Bild aktualisieren (Blickrichtung + Laufanimation)
    }

    private void setNextImage(int firstImageIndex, int lastImageIndex) {
        // Wir haben uns auch vorher in diese Richtung bewegt
        if (currentImageIndex >= firstImageIndex && currentImageIndex <= lastImageIndex) {
            // Nehme das nächste Bild aus der Richtung,
            // bzw. das Erste, wenn das Ende erreicht wurde
            int index = ++currentImageIndex > lastImageIndex ? firstImageIndex : currentImageIndex;
            setImage(index);
        } else {
            setImage(firstImageIndex);
        }
    }

    public int getLifeCount() {
        return lifeCount;
    }
}