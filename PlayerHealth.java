import java.util.ArrayList;
import java.util.List;

import greenfoot.Actor;
import greenfoot.World;

public class PlayerHealth extends Actor {

    static PlayerHealth instance = new PlayerHealth();

    public static PlayerHealth get() {
        return instance;
    }

    private class HealthImage extends Actor {

        private final int defaultGlobalBlinkCooldown = 15;
        private int globalBlinkCooldown = 0;

        private boolean blinking;
        private boolean transparent;

        private HealthImage() {
            setImage("heart.png");
        }

        @Override
        public void act() {

            // Soll das Herz blinken?
            if (blinking) {

                // Ist der Cooldown abgelaufen? (Damit das Herz nicht zu schnell blinkt)
                if (--globalBlinkCooldown <= 0) {
                    globalBlinkCooldown = defaultGlobalBlinkCooldown;
                } else {
                    return;
                }

                // Tausche das Image transparent <> heart
                if (transparent) {
                    setImage("heart.png");
                } else {
                    setImage("transparent.png");
                }
            }
            // Stelle sicher, dass das Herz Bild wiederhergestellt wird
            else if (!blinking && transparent) {
                setImage("heart.png");
            }
        }

        public void setBlinking(boolean active) {
            blinking = active;
        }

        @Override
        public void setImage(String filename) throws IllegalArgumentException {
            transparent = filename.equals("transparent.png");
            super.setImage(filename);
        }
    }

    int previousLifeCount;
    World previousWorld;
    List<HealthImage> currentHealthImages = new ArrayList<HealthImage>();

    private PlayerHealth() {
        setImage("transparent.png");
        refreshHealth(Player.get().getLifeCount());
    }

    @Override
    public void act() {
        Player p = Player.get();

        int lifeCount = p.getLifeCount();
        World world = getWorld();
        if (lifeCount != previousLifeCount || world != previousWorld) {
            previousWorld = world;
            previousLifeCount = lifeCount;
            refreshHealth(lifeCount);
        }

        for (HealthImage img : currentHealthImages) {
            img.setBlinking(p.isImmortal());
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
        currentHealthImages.clear();

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
