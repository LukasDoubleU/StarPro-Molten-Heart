public abstract class Potion extends Item {

    public Potion(String image) {
        super(image);
    }

    public void drink(Player p) {
        SoundUtil.playSound("drink_sound.wav");
        _drink(p);
    };

    protected abstract void _drink(Player p);

    public static class Health extends Potion {

        public Health() {
            super("potions/red_potion.png");
        }

        @Override
        public void _drink(Player p) {
            p.heal(1);
        }
    }

    public static class Speed extends Potion {

        public Speed() {
            super("potions/pink_potion.png");
        }

        @Override
        public void _drink(Player p) {
            p.speedUp(5);
        }
    }

    public static class Damage extends Potion {

        public Damage() {
            super("potions/green_potion.png");
        }

        @Override
        public void _drink(Player p) {
            p.equippedWeapon.damage += 1;
        }
    }

    public static class Immortality extends Potion {

        public Immortality() {
            super("potions/invincible.png");
        }

        @Override
        public void _drink(Player p) {
            p.immortal(120);
        }
    }

}