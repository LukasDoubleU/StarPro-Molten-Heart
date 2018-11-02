public class BeginnerSword extends Weapon {

    public BeginnerSword() {
        super("swords/image_part_001.png");
    }

    @Override
    public int getDamage() {
        return 1;
    }

    @Override
    public int getCooldown() {
        return 4;
    }

    @Override
    public Attack getAttack() {
        return new SwordAttack(this);
    }
}
