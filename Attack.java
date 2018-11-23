import java.util.List;

import greenfoot.Actor;
import greenfoot.GreenfootImage;

public abstract class Attack extends Actor {

    Weapon weapon;
    int duration = 5;

    public Attack(Weapon weapon) {
        this.weapon = weapon;
        setImage(getUsageImage());
    }

    /**
     * Entfernt die Attacke aus der Welt
     */
    protected void remove() {
        Player.get().getWorld().removeObject(this);
    }

    @Override
    public void act() {

        // Prüfe auf Treffer
        checkHit();

        // Überprüfe die "Dauer" der Attacke
        processDuration();
    }

    protected void processDuration() {
        // Prüfe, ob die Dauer abläuft
        if (--duration == 0) {
            remove();
        }
    }

    protected void checkHit() {
        @SuppressWarnings("unchecked")
        List<Enemy> enemiesHit = getIntersectingObjects(Enemy.class);
        for (Enemy enemy : enemiesHit) {
            processHit(enemy);
        }
    }

    protected void processHit(Enemy enemy) {
        Hit hit = new Hit();
        Player.get().getWorld().addObject(hit, enemy.getX(), getY());
        enemy.damage(weapon.getDamage());
    }

    /**
     * @return Bild, das bei der Verwendung einer Attacke angezeigt wird
     */
    public abstract GreenfootImage getUsageImage();

    /**
     * @return Bild das angezeigt wird, wenn mit einer Attacke ein Gegner getroffen
     *         wird
     */
    public abstract GreenfootImage getHitImage();

    public static class Sword extends Attack {

        public Sword(Weapon weapon) {
            super(weapon);
        }

        @Override
        public GreenfootImage getUsageImage() {
            return new GreenfootImage("sword_swipe/swipe005.png");
        }

        @Override
        public GreenfootImage getHitImage() {
            return new GreenfootImage("red_yellow_splash_small.png");
        }

    }

    protected class Hit extends Actor {

        int lifespan = 5;

        Hit() {
            setImage(getHitImage());
        }

        @Override
        public void act() {
            lifespan--;
            if (lifespan <= 0) {
                Player.get().getWorld().removeObject(this);
            }
        }
    }
}
