import greenfoot.Greenfoot;

/**
 * Waffen sind spezielle Items mit denen der Spieler Gegner angreifen kann.
 */
public abstract class Weapon extends Item {

    int damage, cooldown;
    int cooldownCounter = 0;

    public Weapon(String image, int damage, int cooldown) {
	super(image);
	this.damage = damage;
	this.cooldown = cooldown;
    }

    /**
     * Die Zeit für die ein Item nach der Verwendung nicht benutzt werden kann
     */
    public final int getCooldown() {
	return cooldown;
    }

    /**
     * Führe die Attack nur aus, wenn der Cooldown passt
     */
    public void attack() {

	// reduziere den Cooldown
	cooldownCounter--;

	// Leertaste gedrückt?
	if (!Greenfoot.isKeyDown("space")) {
	    return;
	}

	// Erlaubt der Cooldown den Angriff?
	if (!checkCooldown()) {
	    return;
	}

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

    private boolean checkCooldown() {
	// Cooldown abgelaufen?
	if (cooldownCounter <= 0) {
	    // Dann setze den Cooldown wieder
	    cooldownCounter = getCooldown();
	    // Und erlaube den Angriff
	    return true;
	} else {
	    // Verbietet den Angriff
	    return false;
	}
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
