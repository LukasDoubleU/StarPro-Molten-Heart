
import greenfoot.*;

/**
 * Items liegen in der Welt verteilt oder werden von Gegnern fallen gelassen.
 * Der Spieler kann sie aufsammeln und sammelt sie in seinem Inventar. Der
 * Spieler kann ein Item aus seinem Inventar ausr�sten und dieses dann mit Space
 * benutzen. Items k�nnen z.B. Lebenstr�nke oder Waffen sein.
 */
public abstract class Item extends Actor {

    int cooldownCounter = 0;

    @Override
    public void act() {
        // Pr�fe, ob das Item Einsatzbereit ist
        if (cooldownCounter == 0) {
            // Wenn Leertaste gedr�ckt wird, verwende das Item
            if ("space".equals(Greenfoot.getKey())) {
                use();
                // Setzen den Cooldown auf den Item-spezifischen Wert
                cooldownCounter = getCooldown();
            }
        }
        // Das Item ist noch nicht einsatzbereit, der Cooldown wird reduziert
        else {
            cooldownCounter--;
        }
    }

    /**
     * Aktive Anwendung des Items. z.B. Angreifen bei Waffen, Trinken bei einem
     * Trank
     */
    public abstract void use();

    /**
     * Die Zeit f�r die ein Item nach der Verwendung nicht benutzt werden kann
     */
    public abstract int getCooldown();

    /**
     * Bild, das das Item repr�sentiert, wenn es auf dem Boden liegt
     */
    public abstract GreenfootImage getImageOnGround();

    /**
     * Bild, das das Item repr�sentiert, wenn es sich im Inventar befindet
     */
    public abstract GreenfootImage getImageInInventory();

    /**
     * Bild das angezeigt wird, wenn das Item verwendet wird
     */
    public abstract GreenfootImage getUsageImage();

}
