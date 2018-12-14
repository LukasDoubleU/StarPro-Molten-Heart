
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

    private static int speeduptimer = 0;
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

    final int processDotsCooldownDefault = 15;
    int processDotsCooldown = processDotsCooldownDefault;
    int moveSpeed = 3, moveSpeedSlowed = 0, moveSpeedBonus = 0;
    final int moveSpeedMin = 1, moveSpeedMax = 10;

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

        if (--processDotsCooldown <= 0) {
            if (moveSpeedSlowed > 0) {
                moveSpeedSlowed--;
            }
            if (moveSpeedBonus > 0) {
                speeduptimer++;
                if (speeduptimer == 100) {
                    moveSpeedBonus = 0;
                    speeduptimer = 0;
                }
            }
            if (immortal > 0) {
                immortal--;
            }

            processDotsCooldown = processDotsCooldownDefault;
        }
    }

    /**
     * Fügt dem Spieler Schaden zu (zieht ihm Leben ab)
     */
    public void damage(int dmg) {
        if (isImmortal() || dmg < 1) {
            // Der Spieler nimmt keinen Schaden, solange er unsterblich ist
            return;
        }
        // Füge dem Spieler Schaden zu
        lifeCount -= calculateReducedDamage(dmg);
        // Spiele Sound
        SoundUtil.playSound("damage_taken.wav");
        // Wenn der Spieler jetzt LowHP ist, spiele den Sound in Loop
        if (isLowHP()) {
            SoundUtil.loop("low_hp_sound.wav");
        }
        // Sinken die Leben auf 0 (oder weniger) ist das Spiel verloren
        if (lifeCount <= 0) {
            SoundUtil.playSound("death_fall_sound.wav");
            Level.runGameOverWorld();
        }
        // Nachdem der Spieler Schaden nimmt, ist er für eine kurze Zeit unsterblich
        immortal(10);
    }

    public boolean isLowHP() {
        return getLifeCount() <= 2;
    }

    private long calculateReducedDamage(int dmg) {
        // Die Rüstung wird prozentual vom zuzufügenden Schaden abgezogen
        double reduced = dmg * equippedArmor.getDamageReduction();
        // Runde kaufmännisch
        long rounded = Math.round(reduced);
        // Stelle sicher, dass mindestens 1 Schaden zugefügt wird
        return Math.max(1l, rounded);
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
            // Ziehe Schuhe an
            else if (item instanceof Boots) {
                equipBoots((Boots) item);
            }

            // Entferne das "aufgehobene" Objekt aus der Welt
            getWorld().removeObject(item);
        }
    }

    private void equipBoots(Boots item) {
        equippedBoots = item;
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
            SoundUtil.playSound("collision_sound.wav");
            resetPosition();
        }
    }

    /**
     * returns the player hitbox for calculation purposes
     *
     * @return
     */
    public int getPlayerHitboxSize() {
        return 39 - moveSpeedMax;
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
        if (!getObjectsAtOffset(0, -(getPlayerHitboxSize() - 10), Obstacle.class).isEmpty()) { // wall above player?
            getWorld().setPaintOrder(Player.class, Enemy.class, Barrel.class, DestroyableObstacle.class,
                    Obstacle.class);
        }
        if (!getObjectsAtOffset(0, +(getPlayerHitboxSize() - 10), Obstacle.class).isEmpty()) { // wall underneath
            // player?
            getWorld().setPaintOrder(RangedSlow.class, Enemy.class, DestroyableObstacle.class, Obstacle.class,
                    Player.class);
        }
    }

    /**
     * @return player's current movement speed
     */
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

    /**
     * animates the player's movement for each direction therefore checks whether
     * the player is still moving in the same direction. Different intervall for
     * each direction.
     *
     * @param firstImageIndex
     * @param lastImageIndex
     */
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

    /**
     * returns url string for armor to use with corresponding part id.
     *
     * @return
     */
    public String getArmorImagePrefix() {
        return equippedArmor.getImageFolder() + "/image_part_";
    }

    /**
     * Heilt den Spieler um die angegebene Menge.
     */
    public void heal(int amount) {
        lifeCount += amount;
        // Wenn der Spieler nach dem Heilen nicht mehr Low HP ist, beende die Sound Loop
        if (!isLowHP()) {
            SoundUtil.stopLoop("low_hp_sound.wav");
        }
    }

    /**
     * Verschnellert den Spieler um die angegebene Menge.
     */
    public void speedUp(int amount) {
        moveSpeedBonus += amount;

    }

    /**
     * creates a container with player's armor to display for each walk animation.
     */
    private void refreshMoveAnimationImageCache() {
        for (int i = 1; i < 37; i++) {
            imageCache[i] = new GreenfootImage(getArmorImagePrefix() + String.format("%03d", i) + ".png");
        }
    }

    /**
     * sets the player's invincibility for the given duration
     *
     * @param duration
     */
    public void immortal(int duration) {
        immortal += duration;
    }

    /**
     * returns whether or not the player is still immortal.
     *
     * @return
     */
    public boolean isImmortal() {
        // Der Spieler gilt als unsterblich, solange immortal > 0 ist
        return immortal > 0;
    }
}
