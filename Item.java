
import greenfoot.Actor;

/**
 * Items liegen in der Welt verteilt.
 * Items können z.B. Lebenstränke oder Waffen sein.
 */
public abstract class Item extends Actor {

    /**
     * @param image Name des Bildes, das das Item repräsentiert (auf dem Boden und
     *              auf dem Inventar)
     */
    public Item(String image) {
        if (image != null) {
            setImage(image);
        }
    }

    /**
     * Entfernt das Item aus der Welt
     */
    protected void remove() {
        Player.get().getWorld().removeObject(this);
    }

    protected String getSound() {
        return "item_pickup.wav";
    }
}
