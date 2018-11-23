
import greenfoot.Actor;

/**
 * Items liegen in der Welt verteilt oder werden von Gegnern fallen gelassen.
 * Items können z.B. Lebenstränke oder Waffen sein.
 */
public abstract class Item extends Actor {

    public enum Rarity {

        Common(0.8f, new Sword.Big(), new Potion.Health()),

        Rare(0.5f, new Armor.Bright(), new Potion.Speed(), new Potion.Damage()),

        Legendary(0.2f, new Sword.Master(), new Armor.Dark(), new Potion.Immortality());

        float dropChance;
        Item[] items;

        Rarity(float dropChance, Item... items) {
            this.dropChance = dropChance;
            this.items = items;
        }
    }

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
}
