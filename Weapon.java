/**
 * Waffen sind spezielle Items mit denen der Spieler Gegner angreifen kann.
 */
public abstract class Weapon extends Item {

    int damage, cooldown;
    int cooldownCounter = 0;

    public Weapon(String image, int damage, int cooldown) {
        super(image);
        this.damage = damage;
    }

    /**
     * Die Zeit für die ein Item nach der Verwendung nicht benutzt werden kann
     */
    public final int getCooldown() {
        return cooldown;
    }

    public void attack() {
        // Prüfe, ob das Item Einsatzbereit ist
        if (cooldownCounter == 0) {
            // Wenn Leertaste gedrückt wird, verwende das Item
            _attack();
            // Setzen den Cooldown auf den Item-spezifischen Wert
            cooldownCounter = getCooldown();
        }
        // Das Item ist noch nicht einsatzbereit, der Cooldown wird reduziert
        else {
            cooldownCounter--;
        }
    }

    private void _attack() {
        Player p = Player.get();

        int x = p.getX(), y = p.getY();
        Direction direction = p.getDirection();
        int rotation = 0, distance = 40;
        if (direction == Direction.Up) {
            y -= distance;
            rotation = 0;
        } else if (direction == Direction.Down) {
            y += distance;
            rotation = 180;
        } else if (direction == Direction.Right) {
            x += distance;
            rotation = 90;
        } else { // Left
            x -= distance;
            rotation = 270;
        }

        Attack attack = getAttack();
        attack.setRotation(rotation);
        p.getWorld().addObject(attack, x, y);
    }

    /**
     * Gibt die Attack zurück, die mit dieser Waffe erfolgen kann.
     */
    public abstract Attack getAttack();

    /**
     * Gibt an, wie viel Schaden diese Waffe bei einem Treffer verursacht.
     */
    public final int getDamage() {
        return damage;
    }
}
