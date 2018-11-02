/**
 * Waffen sind spezielle Items mit denen der Spieler Gegner angreifen kann.
 */
public abstract class Weapon extends Item {

    @Override
    public final void _use() {
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
    public abstract int getDamage();
}
