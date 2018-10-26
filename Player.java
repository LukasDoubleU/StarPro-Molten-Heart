
import java.util.List;

import greenfoot.Actor;
import greenfoot.Greenfoot;
import greenfoot.GreenfootImage;

/**
 * Die vom Spieler gesteuerte Figur. Ist ein Singleton, d.h. es gibt nur eine
 * Instanz dieser Klasse. Auf diese kann �ber
 *
 * TODO: Beim Einsammeln von Ausrüstung ändert sich das Spieler Model
 */
public class Player extends Actor {

    private static final Player INSTANCE = new Player();

    /**
     * Gibt die Instanz der Spielers zur�ck. Diese ist "permanent".
     */
    public static Player get() {
        return INSTANCE;
    }

    int lifeCount = 5;

    int moveSpeed = 8, moveSpeedSlowed = 0, moveSpeedBonus = 0;

    int oldX, oldY, oldRotation;

    /*
     * Equipment Slots
     */
    Weapon equippedWeapon = new Sword();
    Item equippedGear; // TODO

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
        String key = Greenfoot.getKey();

        rememberPosition();
        move(key);
        checkCollision();
        useItem(key);
        processDots();
    }

    private void useItem(String key) {
        if ("space".equals(key)) {
            equippedWeapon.use();
        }
    }

    /**
     * Aktualisiert Über-Zeit-Effekte
     */
    private void processDots() {
        if (moveSpeedBonus > 0) {
            moveSpeedBonus--;
        }
        if (moveSpeedSlowed > 0) {
            moveSpeedSlowed--;
        }
    }

    /**
     * F�gt dem Spieler Schaden zu (zieht ihm Leben ab)
     */
    public void damage(int dmg) {
        lifeCount -= dmg;
        if (lifeCount <= 0) {
            // TODO GameOver Methode aufrufen
        }
    }

    /**
     * Verlangsamt den Spieler um die angegebene Menge. Wird über Zeit abgebaut.
     */
    public void slow(int amount) {
        moveSpeedSlowed += amount;
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
        @SuppressWarnings("unchecked")
        List<Obstacle> obstacles = getIntersectingObjects(Obstacle.class);
        // Kollidieren wir mit irgendeinem Hindernis?
        if (!obstacles.isEmpty()) {
            resetPosition();
        }
    }

    /**
     * F�hrt eine Bewegung in Abh�ngigkeit zu den gedr�ckten Tasten aus
     */
    private void move(String key) {

        if (key == null) {
            return;
        }
        if ("w".equals(key)) {
            setLocation(getX(), getY() - getMoveSpeed());
            setNextImage(firstUp, lastUp);
        }
        if ("a".equals(key)) {
            setLocation(getX() - getMoveSpeed(), getY());
            setNextImage(firstLeft, lastLeft);
        }
        if ("s".equals(key)) {
            setLocation(getX(), getY() + getMoveSpeed());
            setNextImage(firstDown, lastDown);
        }
        if ("d".equals(key)) {
            setLocation(getX() + getMoveSpeed(), getY());
            setNextImage(firstRight, lastRight);
        }
    }

    public int getMoveSpeed() {
        return moveSpeed + moveSpeedBonus - moveSpeedSlowed;
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

    /**
     * @return Die Anzahl Leben des Spielers
     */
    public int getLifeCount() {
        return lifeCount;
    }

    /**
     * Gibt an, in welche Richtung der Spieler gerade schaut. {@link #getRotation()}
     * kann nicht verwendet werden!
     */
    public Direction getDirection() {
        if (currentImageIndex < 1 || currentImageIndex > 36) {
            throw new ArrayIndexOutOfBoundsException();
        }
        if (currentImageIndex >= 28) {
            return Direction.Right;
        }
        if (currentImageIndex >= 19) {
            return Direction.Down;
        }
        if (currentImageIndex >= 10) {
            return Direction.Left;
        }
        return Direction.Up;
    }
}