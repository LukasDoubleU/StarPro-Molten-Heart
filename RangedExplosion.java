import greenfoot.*;
import java.util.*;

/**
 * Write a description of class RangedExplosion here.
 * 
 * @author (your name)
 * @version (a version number or a date)
 */
public class RangedExplosion extends Ranged {
    /**
     * Act - do whatever the RangedExplosion wants to do. This method is called
     * whenever the 'Act' or 'Run' button gets pressed in the environment.
     */
    int rotation;

    public RangedExplosion(int newMov_speed, int newLifeCount, String imgPath) {
        super(newMov_speed, newLifeCount, imgPath);
        damage = 3;
        rotation = 0;
        stalkRange = 150;
        this.getImage().scale(100, 100);
        viewDistance = 200;
    }

    public RangedExplosion(int newMov_Speed) {
        this(newMov_Speed, 3, "cannon.png");
    }

    public RangedExplosion() {
        this(2, 3, "cannon.png");
    }

    public void addedToWorld(World world) {
        this.getImage().rotate(90);
        rotation = randomizeRotation();
    }

    public void act() {
        if (counter > 100) {
            if (counter == 115) {
                spawnBullet();
                rotation = randomizeRotation();
            }
            if (counter > 125) {
                counter = 0;
            }

        } else {
            this.turn(rotation);
        }
        counter++;
    }

    public void spawnBullet() {
        BulletExplosion b = new BulletExplosion(6, damage, this.getRotation(), "bomb_proj.png");
        this.getWorld().addObject(b, this.getX(), this.getY());
    }

    public int randomizeRotation() {
        Random rand = new Random();
        int vorzeichen = rand.nextInt(100) + 1;
        if (vorzeichen >= 50) {
            return (rand.nextInt(2) + 2);
        } else {
            return ((rand.nextInt(2 ) + 2) * -1);
        }

    }

}
