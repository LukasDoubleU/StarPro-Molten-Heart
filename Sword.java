
public class Sword extends Weapon {

    public Sword(String image, int damage, int cooldown) {
        super(image, damage, cooldown);
    }

    @Override
    protected String getAttackSound() {
        return "melee_attack.wav";
    }

    @Override
    public Attack getAttack() {
        return new Attack.Sword(this);
    }

    public static class Big extends Sword {

        public Big() {
            super("sword_resize/image_part_033.png", 3, 75);
        }
    }

    public static class Beginner extends Sword {

        public Beginner() {
            super("sword_resize/image_part_001.png", 1, 50);
        }

    }

    public static class Master extends Sword {

        public Master() {
            super("sword_resize/image_part_051.png", 3, 50);
        }

    }

}
