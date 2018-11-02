public abstract class Potion extends Item {

    public Potion(String image) {
        super(image);
    }

    public abstract void drink(Player p);

    public static class Health extends Potion {

        public Health() {
            super("potions/red_potion.png");
        }

        @Override
        public void drink(Player p) {
            p.heal(1); // TODO Amount?
        }
    }

    public static class Speed extends Potion {

        public Speed() {
            super("potions/pink_potion.png");
        }

        @Override
        public void drink(Player p) {
            p.speedUp(1); // TODO Amount?
        }
    }

    public static class Damage extends Potion {

        public Damage() {
            super("potions/green_potion.png");
        }

        @Override
        public void drink(Player p) {
            p.equippedWeapon.damage += 1; // TODO Amount?
        }
    }

    public static class Immortality extends Potion {

        public Immortality() {
            super("potions/invincible.png");
        }

        @Override
        public void drink(Player p) {
            // TODO implement
        }
    }

}