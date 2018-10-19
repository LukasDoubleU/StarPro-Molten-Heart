import greenfoot.*;

/**
 * Items liegen in der Welt verteilt oder werden von Gegnern fallen gelassen.
 * Der Spieler kann sie aufsammeln und sammelt sie in seinem Inventar.
 * Der Spieler kann ein Item aus seinem Inventar ausrüsten und dieses dann mit Space benutzen.
 * Items können z.B. Lebenstränke oder Waffen sein.
 */
public abstract class Item extends Actor {

    int cooldownCounter = 0;

    @Override
    public void act() {
        // Verwende das Item mit Leertaste
        if ("space".equals(Greenfoot.getKey())) {
            // Prüfe, ob das Item Einsatzbereit ist
            if (cooldownCounter == 0) {
                use();
                cooldownCounter = getCooldown();
            }
            // Das Item ist noch nicht einsatzbereit, der Cooldown wird reduziert
            else {
                cooldownCounter--;
            }
        }
    }

    /**
     * Aktive Anwendung des Items. z.B. Angreifen bei Waffen, Trinken bei einem Trank
     */
    public abstract void use();

    /**
     * Die Zeit für die ein Item nach der Verwendung nicht benutzt werden kann
     */
    public abstract int getCooldown();

}
