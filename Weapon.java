/**
 * Waffen sind spezielle Items mit denen der Spieler Gegner angreifen kann.
 */
public abstract class Weapon extends Item {

    @Override
    public final void _use() {
        Player p = Player.get();

        int x = p.getX(), y = p.getY();
        Direction direction = p.getDirection();
        if (direction == Direction.Up) {
            y -= 10;
        } else if (direction == Direction.Down) {
            y += 10;
        } else if (direction == Direction.Right) {
            x += 10;
        } else { // Left
            y -= 10;
        }

        p.getWorld().addObject(getAttack(), x, y);
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
