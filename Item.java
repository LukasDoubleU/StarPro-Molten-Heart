
import greenfoot.Actor;

/**
 * Items liegen in der Welt verteilt oder werden von Gegnern fallen gelassen.
 * Items können z.B. Lebenstränke oder Waffen sein.
 */
public abstract class Item extends Actor {

    public Item(String image) {
        if (image != null) {
            setImage(image);
        }
    }

}
