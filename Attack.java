import java.util.HashSet;
import java.util.List;
import java.util.Set;

import greenfoot.Actor;
import greenfoot.GreenfootImage;

public abstract class Attack extends Actor {

    Weapon weapon;
    int duration = 5;
    Set<Enemy> alreadyHit = new HashSet<Enemy>();

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
        List<Enemy> intersectingEnemies = getIntersectingObjects(Enemy.class);
        // Wenn min. 1 Gegner getroffen wird, spiele Sound
        if (!intersectingEnemies.isEmpty()) {
            SoundUtil.playSound("enemy_hit.wav");
        }
        for (Enemy enemy : intersectingEnemies) {
            if (!alreadyHit.contains(enemy)) {
                processHit(enemy);
            }
        }
    }

    protected void processHit(Enemy enemy) {
        Hit hit = new Hit();
        Player.get().getWorld().addObject(hit, enemy.getX(), getY());
        enemy.damage(weapon.getDamage());
        alreadyHit.add(enemy);
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
            return new GreenfootImage("sword_swipe/swipe.png");
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
