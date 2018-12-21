import greenfoot.*;
import java.util.*;

/**
 * Write a description of class RangedSlow here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class RangedSlow extends Ranged {
    /**
     * Act - do whatever the RangedSlow wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    int slow;
    int spiderWallRange;
    double wallType;

    public RangedSlow(int moveSpeed, int lifeCount, double newWallType, String imgPath) {
        super(moveSpeed, lifeCount, imgPath);
        slow = 4;
        stalkRange = 0;
        viewDistance = 200;
        spiderWallRange = 20;
        isMoving = false;
        wallType = newWallType;
    }

    public RangedSlow(int moveSpeed, double wallType) {
        this(moveSpeed, 1, wallType, "spider.png");
    }

    public RangedSlow(double wallType) {
        this(1, 1, wallType, "spider.png");
    }

    public RangedSlow() {
        this(1.3);
    }

    public void addedToWorld(World world) {
        this.getWorld().addObject(new Wall(wallType), this.getX(), this.getY());
        level = (Level) world;
        level.monstercount++;
    }

    public void act() {
        if (target == null) {
            target = getTarget();
        }

        if (target != null) {
            followTarget();

            if (counter > 100) {
                spawnBullet();
            }
        }
        counter++;

    }

    public void followTarget() {
        int oldX = this.getX();
        int oldY = this.getY();
        if (this.getX() > target.getX()) {
            List wallList = new ArrayList();
            wallList = getObjectsAtOffset(-spiderWallRange, 0, Wall.class);
            if (!wallList.isEmpty()) {
                this.setLocation(this.getX() - moveSpeed + (moveSpeed / 2), this.getY());
            }
        } else if (this.getX() < target.getX()) {
            List wallList = new ArrayList();
            wallList = getObjectsAtOffset(spiderWallRange, 0, Wall.class);
            if (!wallList.isEmpty()) {
                this.setLocation(this.getX() + moveSpeed - (moveSpeed / 2), this.getY());
            }
        }
        if (this.getY() > target.getY()) {
            List wallList = new ArrayList();
            wallList = getObjectsAtOffset(0, -spiderWallRange, Wall.class);
            if (!wallList.isEmpty()) {
                this.setLocation(this.getX(), this.getY() - moveSpeed + (moveSpeed / 2));
            }
        } else if (this.getY() < target.getY()) {
            List wallList = new ArrayList();
            wallList = getObjectsAtOffset(0, spiderWallRange, Wall.class);
            if (!wallList.isEmpty()) {
                this.setLocation(this.getX(), this.getY() + moveSpeed - (moveSpeed / 2));
            }
        }
        this.setRotation(0);
    }
    
    public void spawnBullet() {
        BulletSlow b = new BulletSlow(6, slow, target, "spider_web.png");
        this.getWorld().addObject(b, this.getX(), this.getY());
        counter = 0;
    }

}
