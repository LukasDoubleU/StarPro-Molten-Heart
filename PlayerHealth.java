import java.util.ArrayList;
import java.util.List;

import greenfoot.Actor;
import greenfoot.GreenfootImage;
import greenfoot.World;

public class PlayerHealth extends Actor {

    static PlayerHealth instance = new PlayerHealth();

    public static PlayerHealth get() {
        return instance;
    }

    private class HealthImage extends Actor {

        private HealthImage() {
            setImage(new GreenfootImage("heart.png"));
        }
    }

    int previousLifeCount;
    List<HealthImage> currentHealthImages = new ArrayList<HealthImage>();

    private PlayerHealth() {
        setImage("transparent.png");
        refreshHealth(Player.get().getLifeCount());
    }

    @Override
    public void act() {
        Player p = Player.get();
        int lifeCount = p.getLifeCount();
        if (lifeCount != previousLifeCount) {
            previousLifeCount = lifeCount;
            refreshHealth(lifeCount);
        }
    }

    private void refreshHealth(int lifeCount) {
        World world = Player.get().getWorld();

        // Blende die Leben aus, wenn der Spieler nicht in der Welt ist
        if (world == null) {
            return;
        }

        // Entferne die bisherigen Bilder
        world.removeObjects(currentHealthImages);

        // Erzeuge ein neues Herz für jeden Lebenspunkt
        for (int i = 0; i < lifeCount; i++) {
            HealthImage img = new HealthImage();
            // Die X Koordinate muss immer etwas weiter nach rechts gehen,
            // damit die Herzen rechts nebeneinander angezeigt werden
            int imgWidth = img.getImage().getWidth();
            int x = getX() + i * imgWidth + imgWidth / 2;
            world.addObject(img, x, getY());
            currentHealthImages.add(img);
        }
    }

}
