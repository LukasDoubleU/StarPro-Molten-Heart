
public abstract class Armor extends Item {

    public Armor(String image) {
        super(image);
    }

    /**
     * @return Zwischen 0-1, wird prozentual vom kassierten Schaden abgezogen
     */
    public abstract float getDamageReduction();

    /**
     * @return Der Name des Ordners in dem sich die Bilder befinden
     */
    public abstract String getImageFolder();

    public static class Dark extends Armor {

        public Dark() {
            super("armor/dark_armor.png");
        }

        @Override
        public float getDamageReduction() {
            return 0.5f;
        }

        @Override
        public String getImageFolder() {
            return "soldier_dark";
        }

    }

    public static class Bright extends Armor {

        public Bright() {
            super("armor/bright_armor.png");
        }

        @Override
        public float getDamageReduction() {
            return 0.3f;
        }

        @Override
        public String getImageFolder() {
            return "soldier_bright";
        }

    }

    public static class None extends Armor {

        public None() {
            super(null);
        }

        @Override
        public float getDamageReduction() {
            return 0;
        }

        @Override
        public String getImageFolder() {
            return "soldier_blank";
        }

    }

    public int getDamageReductionPercent() {
        return (int) getDamageReduction() * 100;
    }

}
