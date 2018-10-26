import java.util.HashSet;
import java.util.List;
import java.util.Set;

import greenfoot.Actor;
import greenfoot.GreenfootImage;

public abstract class Attack extends Actor {

    class Hit extends Actor {

        Hit() {
            setImage(getHitImage());
        }
    }

    int duration = 10;
    Set<Hit> hits = new HashSet<Hit>();

    public Attack() {
        setImage(getUsageImage());
    }

    @Override
    public void act() {

        // Prüfe auf Treffer
        @SuppressWarnings("unchecked")
        List<Enemy> enemiesHit = getIntersectingObjects(Enemy.class);
        for (Enemy enemy : enemiesHit) {
            Hit hit = new Hit();
            hits.add(hit);
            Player.get().getWorld().addObject(hit, enemy.getX(), getY());
            // TODO dem Gegner schaden zufügen
        }

        // Prüfe, ob die Dauer abläuft
        duration--;
        if (duration == 0) {
            Player.get().getWorld().removeObject(this);
            for (Hit hit : hits) {
                Player.get().getWorld().removeObject(hit);
            }
        }
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

}
