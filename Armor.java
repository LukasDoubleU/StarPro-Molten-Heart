
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

    public static class Dark extends Armor {

        public Dark() {
            super("armor/dark_armor.png");
        }

        @Override
        public int getDamageReduction() {
            return 2;
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
        public int getDamageReduction() {
            return 1;
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
        public int getDamageReduction() {
            return 0;
        }

        @Override
        public String getImageFolder() {
            return "soldier_blank";
        }

    }

}
