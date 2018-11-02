
import greenfoot.GreenfootImage;

public class Sword extends Weapon {

    GreenfootImage image = new GreenfootImage("swords/image_part_001.png");

    @Override
    public int getDamage() {
        return 1;
    }

    @Override
    public int getCooldown() {
        return 5;
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
        return null; // Gibt es nicht für Waffen
    }

    @Override
    public Attack getAttack() {
        return new SwordAttack(this);
    }
}
