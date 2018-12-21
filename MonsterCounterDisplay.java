import static java.lang.String.format;

import java.awt.Color;

import greenfoot.Actor;
import greenfoot.GreenfootImage;
import greenfoot.World;

/**
 * <p>
 * Zeigt die verbleibende Anzahl Monster an. {@link #show(int)} verwenden.
 * </p>
 *
 * @author Lukas Wulff
 */
public class MonsterCounterDisplay extends Actor {

    private static MonsterCounterDisplay previous;

    public static void show(int monstercount) {

        // Hat sich die Monsterzahl ggü. der vorigen Anzeige geändert?
        if (previous != null && previous.monstercount == monstercount) {
            return;
        }

        MonsterCounterDisplay display = new MonsterCounterDisplay(monstercount);
        World world = Player.get().getWorld();
        if (world != null) {
            // Entferne die vorige Anzeige
            if (previous != null) {
                previous.remove();
            }
            world.addObject(display, 640, 50);
            previous = display;
        }
    }

    int duration = 100;
    int monstercount;

    private MonsterCounterDisplay(int monstercount) {
        this.monstercount = monstercount;
        String s = monstercount == 0 ? "Alle Monster besiegt - die Tür ist offen!"
                : format("%s Monster verbleiben", monstercount);
        GreenfootImage img = new GreenfootImage(s, 36, Color.WHITE, null, Color.BLACK);
        setImage(img);
    }

    @Override
    public void act() {
        if (duration-- == 0) {
            remove();
        }
    }

    public void remove() {
        World world = getWorld();
        if (world != null) {
            world.removeObject(this);
        }
    }
}
