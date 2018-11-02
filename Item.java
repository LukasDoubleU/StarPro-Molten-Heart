
import greenfoot.Actor;

/**
 * Items liegen in der Welt verteilt oder werden von Gegnern fallen gelassen.
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

}
