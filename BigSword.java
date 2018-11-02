public class BigSword extends Weapon {

    public BigSword() {
        super("swords/image_part_033.png");
    }

    @Override
    public Attack getAttack() {
        return new SwordAttack(this);
    }

    @Override
    public int getDamage() {
        return 3;
    }

    @Override
    public int getCooldown() {
        return 6;
    }

}
