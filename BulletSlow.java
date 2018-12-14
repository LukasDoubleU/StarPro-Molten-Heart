import greenfoot.*;
import java.util.*;

/**
 * Write a description of class BulletSlow here.
 * 
 * @author (your name)
 * @version (a version number or a date)
 */
public class BulletSlow extends Projectiles {
    /**
     * Act - do whatever the BulletSlow wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    // Add your action code here.int moveSpeed;
    int slow;
    int turnDegree;
    Player target = null;
    boolean turnNotDone;
    boolean isAlive;

    public BulletSlow(int moveSpeed, int newSlow, Player newTarget, String imgPath) {
        super(moveSpeed, 1, imgPath);
        slow = newSlow;
        target = newTarget;
        if (imgPath.equals("tear.png")) {
            getImage().scale(15, 15);
        }
        if (imgPath.equals("spider_web.png")) {
            getImage().scale(40, 40);
        }
        isAlive = true;
        turnNotDone = true;
    }

    public BulletSlow(int moveSpeed, int newSlow, int newTurnDegree, Player newTarget, String imgPath) {
        super(moveSpeed, 1, imgPath);
        slow = newSlow;
        target = newTarget;
        if (imgPath.equals("tear.png")) {
            getImage().scale(15, 15);
        }
        isAlive = true;
        turnDegree = newTurnDegree;
    }

    public void act() {
        if (isAlive) {

            if (turnNotDone) {
                firstTurn();
                
            }

            move(moveSpeed);
            checkCollision();

        }

    }

    public void firstTurn() {
        if (target != null) {
            this.turnTowards(target.getX(), target.getY());
        }
        if (target == null) {
            this.turn(turnDegree);
        }
        turnNotDone = false;
    }

    public void checkCollision() {
        List intersectingObjects = new ArrayList();
        intersectingObjects = this.getObjectsInRange(10, null);

        for (Object a : intersectingObjects) {
            if (a instanceof Player) {
                Player.get().slow(slow);
                this.getWorld().removeObject(this);
                isAlive = false;
                return;
            }
        }
        if (this.isAtEdge()) {
            this.getWorld().removeObject(this);
            isAlive = false;
        }
    }
}
