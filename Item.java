
import greenfoot.Actor;
import greenfoot.GreenfootImage;

/**
 * Items liegen in der Welt verteilt oder werden von Gegnern fallen gelassen.
 * Der Spieler kann sie aufsammeln und sammelt sie in seinem Inventar. Der
 * Spieler kann ein Item aus seinem Inventar ausrüsten und dieses dann mit Space
 * benutzen. Items können z.B. Lebenstränke oder Waffen sein.
 */
public abstract class Item extends Actor {

    int cooldownCounter = 0;

    /**
     * Aktive Anwendung des Items. z.B. Angreifen bei Waffen, Trinken bei einem
     * Trank
     */
    public final void use() {
        // Prüfe, ob das Item Einsatzbereit ist
        if (cooldownCounter == 0) {
            // Wenn Leertaste gedrückt wird, verwende das Item
            _use();
            // Setzen den Cooldown auf den Item-spezifischen Wert
            cooldownCounter = getCooldown();
        }
        // Das Item ist noch nicht einsatzbereit, der Cooldown wird reduziert
        else {
            cooldownCounter--;
        }
    };

    protected abstract void _use();

    /**
     * Die Zeit für die ein Item nach der Verwendung nicht benutzt werden kann
     */
    public abstract int getCooldown();

    /**
     * Bild, das das Item repräsentiert, wenn es auf dem Boden liegt
     */
    public abstract GreenfootImage getImageOnGround();

    /**
     * Bild, das das Item repräsentiert, wenn es sich im Inventar befindet
     */
    public abstract GreenfootImage getImageInInventory();

    /**
     * Bild das angezeigt wird, wenn das Item verwendet wird
     */
    public abstract GreenfootImage getUsageImage();

}
