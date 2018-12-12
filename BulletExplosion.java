import greenfoot.*;
import java.util.*;

/**
 * Write a description of class BulletExplosion here.
 * 
 * @author (your name)
 * @version (a version number or a date)
 */
public class BulletExplosion extends Projectiles {
    /**
     * Act - do whatever the BulletExplosion wants to do. This method is called
     * whenever the 'Act' or 'Run' button gets pressed in the environment.
     */
    int counter2;
    int oldCounter;
    int bombScale;
    int bombRange;
    int turnRate;
    boolean exploded;

    public BulletExplosion(int newMov_Speed, int newDamage, int newRotation, String imgPath) {
        super(newMov_Speed, 1, imgPath);
        damage = newDamage;
        target = null;
        counter2 = 0;
        bombScale = 3;
        oldCounter = 0;
        bombRange = randomNumber(5, 20);
        turnRate = newRotation;
        exploded = false;
        if (imgPath.equals("bomb_proj.png")) {
            getImage().scale(35, 35);
        }
    }

    public void act() {
        if (!exploded) {
            if (counter == 8) {
                this.getImage().setTransparency(255);
            }
            if (counter > bombRange && moveSpeed != 0) {
                counter2++;
                if (counter2 == 5) {
                    moveSpeed--;
                    counter2 = 0;
                }
            }
            if (!checkCollision()) {
                move(moveSpeed);
            }
            if (counter == oldCounter + 10) {
                oldCounter = counter;
                this.getImage().scale(30 + bombScale, 30 + bombScale);
                bombScale = bombScale * -1;
            }
            if (counter > 200) {
                explode();
                counter2++;
            }
        }
        counter++;
    }

    public void addedToWorld(World world) {
        this.setRotation(turnRate);
        this.getImage().rotate(-this.getRotation());
        this.getImage().setTransparency(0);
    }

    public void explode() {
        if (counter2 < 5) {
            setImage("bomb_explosion/1.png");
        } else if (counter2 == 5) {
            setImage("bomb_explosion/2.png");
        } else if (counter2 == 10) {
            setImage("bomb_explosion/3.png");
        } else if (counter2 == 15) {
            setImage("bomb_explosion/4.png");
        } else if (counter2 == 20) {
            List intersectingObjects = new ArrayList();
            intersectingObjects = this.getObjectsInRange(80, Player.class);

            for (Object a : intersectingObjects) {

                if ((a instanceof Player)) {
                    Player.get().damage(damage);
                }
            }

            this.getWorld().removeObject(this);
        }

    }

    public boolean checkCollision() {
        List intersectingObjects = new ArrayList();

        intersectingObjects = this.getObjectsInRange(20, null);

        for (Object a : intersectingObjects) {

            if ((a instanceof Obstacle) && !(a instanceof Enemy)) {
                return true;
            }
        }

        return false;
    }

    public int randomNumber(int minWert, int maxWert) {
        Random rand = new Random();
        return rand.nextInt(maxWert) + minWert;
    }

}
