
public class Sword extends Weapon {

    public Sword(String image, int damage, int cooldown) {
        super(image, damage, cooldown);
    }

    @Override
    public Attack getAttack() {
        return new SwordAttack(this);
    }

    public static class BigSword extends Sword {

        public BigSword() {
            super("swords/image_part_033.png", 3, 5);
        }

        @Override
        public Attack getAttack() {
            return new SwordAttack(this);
        }

    }

    public static class BeginnerSword extends Sword {

        public BeginnerSword() {
            super("swords/image_part_001.png", 1, 3);
        }

        @Override
        public Attack getAttack() {
            return new SwordAttack(this);
        }
    }

    public static class MasterSword extends Sword {

        public MasterSword() {
            super("swords/image_part_051.png", 3, 3);
        }

        @Override
        public Attack getAttack() {
            return new SwordAttack(this);
        }

    }

}
