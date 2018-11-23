import java.awt.Color;

import greenfoot.Actor;
import greenfoot.GreenfootImage;
import greenfoot.World;

public class PlayerStats extends Actor {

    static PlayerStats instance = new PlayerStats();

    public static PlayerStats get() {
        return instance;
    }

    Actor armorDisplay, healthDisplay, damageDisplay;
    int previousArmor, previousSpeed, previousDamage;

    private PlayerStats() {
        setImage("transparent.png");
        Player p = Player.get();
        refreshImage(p.equippedArmor.getDamageReduction(), p.equippedWeapon.getDamage(), p.getMoveSpeed());
    }

    @Override
    public void act() {
        Player p = Player.get();

        World world = p.getWorld();
        if (world == null || world != getWorld()) {
            // Wenn der Spieler nicht in der Welt oder in einer anderen Welt
            // als der aktuellen ist
            setImage("transparent.png");
            return;
        }

        int newArmor = p.equippedArmor.getDamageReduction();
        int newDamage = p.equippedWeapon.getDamage();
        int newSpeed = p.getMoveSpeed();
        // Hat sich einer der Werte geändert?
        if (newArmor != previousArmor //
                || newDamage != previousDamage //
                || newSpeed != previousSpeed) {

            // Merke die neuen Werte
            previousArmor = newArmor;
            previousDamage = newDamage;
            previousSpeed = newSpeed;

            // Erstelle ein neues Bild
            refreshImage(newArmor, newDamage, newSpeed);
        }
    }

    private void refreshImage(int armor, int damage, int speed) {
        Color c = new Color(1f, 0f, 0f, 0f);
        setImage(new GreenfootImage("Armor " + armor //
                + " | Damage " + damage //
                + " | Speed " + speed //
                , 30, Color.BLACK, c));
    }
}
