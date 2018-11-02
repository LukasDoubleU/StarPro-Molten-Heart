
public abstract class Armor extends Item {

    public Armor(String image) {
        super(image);
    }

    /**
     * @return Die Menge, um die die Rüstung den Schaden reduziert
     */
    public abstract int getDamageReduction();

    /**
     * @return Der Name des Ordners in dem sich die Bilder befinden
     */
    public abstract String getImageFolder();

}
