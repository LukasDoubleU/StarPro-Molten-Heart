
import static greenfoot.Greenfoot.isKeyDown;

import java.util.ArrayList;
import java.util.List;

import greenfoot.Actor;
import greenfoot.GreenfootImage;

/**
 * Die vom Spieler gesteuerte Figur. Ist ein Singleton, d.h. es gibt nur eine
 * Instanz dieser Klasse. Auf diese kann über
 */
public class Player extends Actor {

    private static Player INSTANCE = new Player();

    /**
     * Gibt die Instanz der Spielers zurück. Diese ist "permanent".
     */
    public static Player get() {
        return INSTANCE;
    }

    /**
     * Erneuert die Instanz des Spielers. Diese Methode darf nur aufgerufen werden,
     * wenn das Spiel neu gestartet wird.
     */
    public static Player newInstance() {
        return INSTANCE = new Player();
    }

    int lifeCount = 5;
    int immortal = 0;

    int moveSpeed = 5, moveSpeedSlowed = 0, moveSpeedBonus = 0;
    int moveSpeedMin = 1, moveSpeedMax = 10;

    int oldX, oldY, oldRotation;

    /*
     * Equipment Slots
     */
    Weapon equippedWeapon = new Sword.Beginner();
    Armor equippedArmor = new Armor.None();
    Boots equippedBoots = new Boots.None();

    /*
     * Variablen für die Bilder zur Laufanimation
     */
    int firstUp = 1, lastUp = 9;
    int firstLeft = 10, lastLeft = 18;
    int firstDown = 19, lastDown = 27;
    int firstRight = 28, lastRight = 36;
    int currentImageIndex;
    GreenfootImage[] imageCache = new GreenfootImage[37];

    private Player() {
        refreshMoveAnimationImageCache();
        setImage(19);
    }

    private void setImage(int index) {
        setImage(imageCache[currentImageIndex = index]);
    }

    @Override
    public void act() {
        rememberPosition();
        move();
        checkForIntersectingObjects();
        checkCollisions();
        attack();
        processDots();
    }

    private void attack() {
        equippedWeapon.attemptAttack();
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
        if (immortal > 0) {
            immortal--;
        }
    }

    /**
     * Fügt dem Spieler Schaden zu (zieht ihm Leben ab)
     */
    public void damage(int dmg) {
        if (isImmortal()) {
            // Der Spieler nimmt keinen Schaden, solange er unsterblich ist
            return;
        }
        // Füge dem Spieler Schaden zu
        lifeCount -= Math.max(0, dmg - equippedArmor.getDamageReduction());
        // Sinken die Leben auf 0 (oder weniger) ist das Spiel verloren
        if (lifeCount <= 0) {
            Level.runGameOverWorld();
        }
        // Nachdem der Spieler Schaden nimmt, ist er für eine kurze Zeit unsterblich
        immortal(15);
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
    private void checkCollisions() {
        checkObstacle();
        checkItem();
    }

    /**
     * Prüft & reagiert auf Kollision mit Item. Items werden nach der Kollision auf
     * der Spielwelt entfernt.
     */
    private void checkItem() {
        @SuppressWarnings("unchecked")
        List<Item> items = getIntersectingObjects(Item.class);
        for (Item item : items) {
            // Lege Rüstung an
            if (item instanceof Armor) {
                equipArmor((Armor) item);
            }
            // Trinke Tränke
            else if (item instanceof Potion) {
                ((Potion) item).drink(this);
            }
            // Lege Waffen an
            else if (item instanceof Weapon) {
                equipWeapon((Weapon) item);
            }

            // Entferne das "aufgehobene" Objekt aus der Welt
            getWorld().removeObject(item);
        }
    }

    private void equipWeapon(Weapon item) {
        equippedWeapon = item;
    }

    private void equipArmor(Armor item) {
        equippedArmor = item;
        refreshMoveAnimationImageCache();
    }

    /**
     * Prüft & reagiert auf Kollision mit Hindernissen
     */
    private void checkObstacle() {
        @SuppressWarnings("unchecked")
        // Ziehe moveSpeedMax vom Radius ab, um pauschal sicher sein zu können,
        // dass der MoveSpeed effektiv keine Hitbox-Verschiebung verursachen kann
        List<Obstacle> obstacles = getNeighbours(getPlayerHitboxSize(), true, Obstacle.class);

        // Ausnahme: Ignoriere Kollisionen mit Projektilen (Ranged)
        List<Projectiles> ranged = new ArrayList<Projectiles>();
        for (Obstacle obstacle : obstacles) {
            if (obstacle instanceof Projectiles) {
                ranged.add((Projectiles) obstacle);
            }
        }
        obstacles.removeAll(ranged);

        // Kollidieren wir mit irgendeinem Hindernis?
        if (!obstacles.isEmpty()) {
            resetPosition();
        }
    }

    /**
     * returns the player hitbox for calculation purposes
     * 
     * @return
     */
    public int getPlayerHitboxSize() {
        return (39 - moveSpeedMax);
    }

    /**
     * Fährt eine Bewegung in Abhängigkeit zu den gedrückten Tasten aus
     */
    private void move() {
        if (isKeyDown("w") || isKeyDown("up")) {
            setLocation(getX(), getY() - getMoveSpeed());
            setNextImage(firstUp, lastUp);
        } else if (isKeyDown("a") || isKeyDown("left")) {
            setLocation(getX() - getMoveSpeed(), getY());
            setNextImage(firstLeft, lastLeft);
        } else if (isKeyDown("s") || isKeyDown("down")) {
            setLocation(getX(), getY() + getMoveSpeed());
            setNextImage(firstDown, lastDown);
        } else if (isKeyDown("d") || isKeyDown("right")) {
            setLocation(getX() + getMoveSpeed(), getY());
            setNextImage(firstRight, lastRight);
        }
    }

    /**
     * checks for intersecting objects relative to the player and changes the paint
     * order.
     */
    private void checkForIntersectingObjects() {
        if (!getObjectsAtOffset(0, -getPlayerHitboxSize(), Obstacle.class).isEmpty()) { // wall above player?
            getWorld().setPaintOrder(Player.class, Obstacle.class);
        }
        if (!getObjectsAtOffset(0, +getPlayerHitboxSize(), Obstacle.class).isEmpty()) { // wall underneath player?
            getWorld().setPaintOrder(Obstacle.class, Player.class);
        }
    }

    public int getMoveSpeed() {
        // Es gilt: 0 < moveSpeed < 10
        return Math.min(moveSpeedMax, Math.max(moveSpeedMin,
                // Der Basis Move Speed
                moveSpeed
                        // Zuzüglich Move Speed Bonus (z.B. Trank)
                        + moveSpeedBonus
                        // Abzgl. Move Speed Penalty (z.B. durch Gegner)
                        - moveSpeedSlowed
                        // Und zzgl. Move Speed Bonus von Boots
                        + equippedBoots.getMoveSpeedBonus()));
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

    /**
     * Heilt den Spieler um die angegebene Menge.
     */
    public void heal(int amount) {
        lifeCount += amount;
    }

    /**
     * Verschnellert den Spieler um die angegebene Menge.
     */
    public void speedUp(int amount) {
        moveSpeedBonus += amount;
    }

    private void refreshMoveAnimationImageCache() {
        for (int i = 1; i < 37; i++) {
            imageCache[i] = new GreenfootImage(getArmorImagePrefix() + String.format("%03d", i) + ".png");
        }
    }

    public void immortal(int duration) {
        immortal += duration;
    }

    public boolean isImmortal() {
        // Der Spieler gilt als unsterblich, solange immortal > 0 ist
        return immortal > 0;
    }

}