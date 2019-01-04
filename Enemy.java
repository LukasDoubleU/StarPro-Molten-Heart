
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import greenfoot.Actor;
import greenfoot.World;

public abstract class Enemy extends Obstacle {

    String path;
    int moveSpeed;
    int damage;
    Player target = null;
    int lifeCount;
    int stalkRange;
    int counter;
    int imgCounter;
    int viewDistance;
    int currentPicture;
    boolean right;
    boolean left;
    boolean up;
    boolean down;
    boolean isMoving;
    Level level = null;

    public Enemy(int moveSpeed, int lifeCount, String imgPath) {
        this.moveSpeed = moveSpeed;
        this.lifeCount = lifeCount;
        currentPicture = 1;
        right = false;
        left = false;
        up = false;
        down = false;
        path = "/images/";
        setImage(imgPath);
        this.counter = 0;
    }

    public Enemy() {
    }

    @Override
    public void addedToWorld(World world) {
        level = (Level) world;
        level.increaseMonstercount(this);
        if (this instanceof RangedDamage) {
            path = "/eyeball/";
        } else if (this instanceof MeleeDamage) {
            path = "/ghost/";
        }
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

    public void refreshImage() {
        if (imgCounter < 20) {
            currentPicture = 1;
        } else if (imgCounter < 40) {
            currentPicture = 2;
        } else if (imgCounter < 60) {
            currentPicture = 3;
            imgCounter = 0;
        }
        if (isMoving) {
//            System.out.println("left: " + left); :TODO kann das weg?
//            System.out.println("right: " + right);
//            System.out.println("up: " + up);
//            System.out.println("down: " + down);
            if (left) {
                path = path + "left_" + currentPicture;
            } else if (right) {
                path = path + "right_" + currentPicture;
            } else if (up) {
                path = path + "up_" + currentPicture;
            } else if (down) {
                path = path + "down_" + currentPicture;
            }
            try {
                setImage(path + ".png");
            } catch (Exception e) {
                System.out.println("ERROR"); // TODO: Wird noch geworfen...
            }
            if (this instanceof RangedDamage) {
                path = "/eyeball/";
            } else if (this instanceof MeleeDamage) {
                path = "/ghost/";
            }
            right = false;
            left = false;
            up = false;
            down = false;
        }
    }

    public void followTarget() {
        int oldX = this.getX();
        int oldY = this.getY();
        if (this.getX() > target.getX()) {
            this.setLocation(this.getX() - moveSpeed + moveSpeed / 2, this.getY());
            left = true;
            if (checkCollision(stalkRange)) {
                this.setLocation(oldX, oldY);
            }
        } else if (this.getX() < target.getX()) {
            this.setLocation(this.getX() + moveSpeed - moveSpeed / 2, this.getY());
            right = true;
            if (checkCollision(stalkRange)) {
                this.setLocation(oldX, oldY);
            }
        }
        if (this.getY() > target.getY()) {
            this.setLocation(this.getX(), this.getY() - moveSpeed + moveSpeed / 2);
            up = true;
            if (checkCollision(stalkRange)) {
                this.setLocation(oldX, oldY);
            }
        } else if (this.getY() < target.getY()) {
            this.setLocation(this.getX(), this.getY() + moveSpeed - moveSpeed / 2);
            down = true;
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
