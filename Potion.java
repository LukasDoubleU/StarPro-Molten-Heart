public abstract class Potion extends Item {

    public Potion(String image) {
        super(image);
    }

    public abstract void drink(Player p);

    public class HealthPotion extends Potion {

        public HealthPotion() {
            super("potions/red_potion.png");
        }

        @Override
        public void drink(Player p) {
            p.heal(1); // TODO Amount?
        }
    }

    public class SpeedPotion extends Potion {

        public SpeedPotion() {
            super("potions/pink_potion.png");
        }

        @Override
        public void drink(Player p) {
            p.speedUp(1); // TODO Amount?
        }
    }

    public class DamagePotion extends Potion {

        public DamagePotion() {
            super("potions/green_potion.png");
        }

        @Override
        public void drink(Player p) {
            p.equippedWeapon.damage += 1; // TODO Amount?
        }
    }

}