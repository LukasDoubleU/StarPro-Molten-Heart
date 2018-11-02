
import java.util.List;

import greenfoot.Actor;
import greenfoot.Greenfoot;
import greenfoot.GreenfootImage;

/**
 * Die vom Spieler gesteuerte Figur. Ist ein Singleton, d.h. es gibt nur eine
 * Instanz dieser Klasse. Auf diese kann über
 */
public class Player extends Actor {

    private static final Player INSTANCE = new Player();

    /**
     * Gibt die Instanz der Spielers zurück. Diese ist "permanent".
     */
    public static Player get() {
        return INSTANCE;
    }

    int lifeCount = 5;

    int moveSpeed = 7, moveSpeedSlowed = 0, moveSpeedBonus = 0;

    int oldX, oldY, oldRotation;

    /*
     * Equipment Slots
     */
    Weapon equippedWeapon = new BeginnerSword();
    Armor equippedArmor = new NoArmor();
    SpeedBoots equippedBoots = null;
    Item equippedGear; // TODO

    /*
     * Variablen für die Bilder zur Laufanimation
     */
    int firstUp = 1, lastUp = 9;
    int firstLeft = 10, lastLeft = 18;
    int firstDown = 19, lastDown = 27;
    int firstRight = 28, lastRight = 36;
    int currentImageIndex;
    GreenfootImage[] imageCache = new GreenfootImage[37];
    {
        for (int i = 1; i < 37; i++) {
            imageCache[i] = new GreenfootImage(getArmorImagePrefix() + String.format("%03d", i) + ".png");
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
        attack();
        processDots();
    }

    private void attack() {
        if (Greenfoot.isKeyDown("space")) {
            equippedWeapon.attack();
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
     * Fügt dem Spieler Schaden zu (zieht ihm Leben ab)
     */
    public void damage(int dmg) {
        lifeCount -= dmg - equippedArmor.getDamageReduction();
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
     * Setzt die Position und Blickrichtung auf die letzten bekannten Werte zurück.
     */
    private void resetPosition() {
        setLocation(oldX, oldY);
        setRotation(oldRotation);
    }

    /**
     * Prüft & reagiert auf Kollisionen
     */
    private void checkCollision() {
        checkObstacle();
    }

    /**
     * Prüft & reagiert auf Kollision mit Hindernissen
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
     * Fährt eine Bewegung in Abhängigkeit zu den gedrückten Tasten aus
     */
    private void move() {
        if (Greenfoot.isKeyDown("w")) {
            setLocation(getX(), getY() - getMoveSpeed());
            setNextImage(firstUp, lastUp);
        } else if (Greenfoot.isKeyDown("a")) {
            setLocation(getX() - getMoveSpeed(), getY());
            setNextImage(firstLeft, lastLeft);
        } else if (Greenfoot.isKeyDown("s")) {
            setLocation(getX(), getY() + getMoveSpeed());
            setNextImage(firstDown, lastDown);
        } else if (Greenfoot.isKeyDown("d")) {
            setLocation(getX() + getMoveSpeed(), getY());
            setNextImage(firstRight, lastRight);
        }
    }

    public int getMoveSpeed() {
        // Der Basis Move Speed
        return moveSpeed
                // Zuzüglich Move Speed Bonus (z.B. Trank)
                + moveSpeedBonus
                // Abzgl. Move Speed Penalty (z.B. durch Gegner)
                - moveSpeedSlowed
                // Und zzgl. Move Speed Bonus von Boots
                + (equippedBoots != null ? equippedBoots.getMoveSpeedBonus() : 0);
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

    public String getArmorImagePrefix() {
        return equippedArmor.getImageFolder() + "/image_part_";
    }
}