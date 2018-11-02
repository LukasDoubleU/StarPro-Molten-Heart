import greenfoot.GreenfootImage;

public class BigSword extends Weapon {

    GreenfootImage image = new GreenfootImage("swords/image_part_033.png");

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

    @Override
    public GreenfootImage getImageOnGround() {
        return image;
    }

    @Override
    public GreenfootImage getImageInInventory() {
        return image;
    }

    @Override
    public GreenfootImage getUsageImage() {
        return null;
    }

}
