import greenfoot.GreenfootImage;

public class MasterSword extends Weapon {

    GreenfootImage image = new GreenfootImage("swords/image_part_051.png");

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
