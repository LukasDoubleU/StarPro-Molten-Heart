
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import greenfoot.Actor;
import greenfoot.World;

public abstract class Enemy extends Obstacle {

    int moveSpeed;
    int damage;
    Player target = null;
    int lifeCount;
    int stalkRange;
    int counter;
    int viewDistance;
    Level level = null;

    public Enemy(int moveSpeed, int lifeCount, String imgPath) {
        this.moveSpeed = moveSpeed;
        this.lifeCount = lifeCount;
        setImage(imgPath);
        this.counter = 0;
    }

    public Enemy() {
    }

    @Override
    public void addedToWorld(World world) {
        level = (Level) world;
        level.increaseMonstercount(this);
        // damage = damage + level.damageModifier;
        counter = randomNumber(200, 1);
    }

    public void damage(int damage) {
        this.lifeCount = lifeCount - damage;
        if (lifeCount <= 0) {
            if (level != null) {
                level.decreaseMonstercount(this);
            }
            this.getWorld().removeObject(this);
            SoundUtil.playSound("splash_sound.wav");
        }
    }

    public void followTarget() {
        boolean right = false;
        boolean left = false;
        boolean up = false;
        boolean down = false;
        int oldX = this.getX();
        int oldY = this.getY();
        if (this.getX() > target.getX()) {
            this.setLocation(this.getX() - moveSpeed + moveSpeed / 2, this.getY());
            right = true;
            if (checkCollision(stalkRange)) {
                this.setLocation(oldX, oldY);
            }
        } else if (this.getX() < target.getX()) {
            this.setLocation(this.getX() + moveSpeed - moveSpeed / 2, this.getY());
            left = true;
            if (checkCollision(stalkRange)) {
                this.setLocation(oldX, oldY);
            }
        }
        if (this.getY() > target.getY()) {
            this.setLocation(this.getX(), this.getY() - moveSpeed + moveSpeed / 2);
            down = true;
            if (checkCollision(stalkRange)) {
                this.setLocation(oldX, oldY);
            }
        } else if (this.getY() < target.getY()) {
            this.setLocation(this.getX(), this.getY() + moveSpeed - moveSpeed / 2);
            up = true;
            if (checkCollision(stalkRange)) {
                this.setLocation(oldX, oldY);
            }
        }

        this.setRotation(0);
    }

    public boolean checkCollision(int stalkRange) {
        List<Actor> intersectingObjects = this.getObjectsInRange(25, null);
        if (intersectingObjects.size() > 0) {
            for (Object a : intersectingObjects) {
                if (a instanceof Obstacle) {
                    return true;
                }

            }
        }
        if (this.isAtEdge()) {
            return true;
        }

        List<Player> playerInRange = this.getObjectsInRange(stalkRange, Player.class);
        for (Object p : playerInRange) {
            if (p instanceof Player) {
                return true;
            }
        }
        return false;
    }

    public Player getTarget() {
        List actorinrange = new ArrayList();
        actorinrange = this.getObjectsInRange(viewDistance, Player.class);

        for (Object a : actorinrange) {
            if (a instanceof Player) {
                return (Player) a;
            }

        }
        return null;
    }

    public int randomNumber(int minWert, int maxWert) {
        Random rand = new Random();
        return rand.nextInt(maxWert) + minWert;
    }

}
