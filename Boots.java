public abstract class Boots extends Item {

    public Boots(String img) {
        super(img);
    }

    public abstract int getMoveSpeedBonus();

    public static class None extends Boots {

        public None() {
            super("transparent.png");
        }

        @Override
        public int getMoveSpeedBonus() {
            return 0;
        }
    }

    public static class Speed extends Boots {

        public Speed() {
            super("swift_boots.png");
        }

        @Override
        public int getMoveSpeedBonus() {
            return 2;
        }
    }

}
