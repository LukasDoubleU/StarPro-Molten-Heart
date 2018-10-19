import greenfoot.*;

/**
 * Waffen sind spezielle Items mit denen der Spieler Gegner angreifen kann.
 */
public abstract class Weapon extends Item {

    @Override
    public final void use() {
        attack();
    }

    /**
     * Führt einen Angriff aus, in die Blickrichtung des Spielers.
     */
    public abstract void attack();

    /**
     * Gibt an, wie viel Schaden diese Waffe bei einem Treffer verursacht.
     */
    public abstract int getDamage();
}
