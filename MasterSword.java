public class MasterSword extends Weapon {

    public MasterSword() {
        super("swords/image_part_051.png");
    }

    @Override
    public Attack getAttack() {
        return new SwordAttack(this);
    }

    @Override
    public int getDamage() {
        return 6;
    }

    @Override
    public int getCooldown() {
        return 6;
    }

}
