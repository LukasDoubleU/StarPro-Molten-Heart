
import greenfoot.*;

public class Sword extends Weapon {

    GreenfootImage image = new GreenfootImage("swords/image_part_001.png");

    @Override
    public void attack() {
        // TODO
    }

    @Override
    public int getDamage() {
        return 3;
    }

    @Override
    public int getCooldown() {
        return 10;
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
        return null; // TODO
    }
}
