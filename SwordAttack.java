import greenfoot.GreenfootImage;

public class SwordAttack extends Attack {

    @Override
    public GreenfootImage getUsageImage() {
        return new GreenfootImage("red_yellow_splash_small.png");
    }

    @Override
    public GreenfootImage getHitImage() {
        return new GreenfootImage("red_yellow_splash_small.png");
    }

}
