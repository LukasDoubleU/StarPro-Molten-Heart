import greenfoot.GreenfootImage;

public class SwordAttack extends Attack {

    @Override
    public GreenfootImage getUsageImage() {
        return new GreenfootImage("sword_swipe/swipe005.png");
    }

    @Override
    public GreenfootImage getHitImage() {
        return new GreenfootImage("red_yellow_splash_small.png");
    }

}
