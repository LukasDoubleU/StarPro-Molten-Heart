
import greenfoot.*;

import java.util.*;

/**
 * Write a description of class Kamel here.
 * 
 * @author (your name)
 * @version (a version number or a date)
 */
public class RangedDamage extends Ranged {
    /**
     * Act - do whatever the Kamel wants to do. This method is called whenever the
     * 'Act' or 'Run' button gets pressed in the environment.
     */
    int moveSpeedX;
    int moveSpeedY;

    public RangedDamage(int moveSpeed, int lifeCount, String imgPath) {
        super(moveSpeed, lifeCount, imgPath);
        this.moveSpeedY = moveSpeed;
        this.moveSpeedX = moveSpeed;
        damage = 1;
        isMoving = true;
        stalkRange = 150;
        viewDistance = 200;
    }

    public RangedDamage(int moveSpeed) {
        this(moveSpeed, 3, "eyeball.png");
    }

    public RangedDamage() {
        this(2, 1, "eyeball.png");
    }

    public void act() {
        if (target == null) {
            target = getTarget();
        }

        if (target != null) {
            shootCounter++;
            followTarget();
            if (shootCounter >= 100) {
                spawnBullet();
            }
        } else {
            if (!checkCollision(stalkRange)) {
                movePattern();
            }
        }
        refreshImage();
        imgCounter++;
        counter++;
    }

    public void movePattern() {
        int oldX = this.getX();
        int oldY = this.getY();
        if (counter == 80) {
            moveSpeedX = -moveSpeedX;
        }
        if (counter == 160) {
            moveSpeedY = -moveSpeedY;
        }
        if (counter == 240) {
            moveSpeedX = -moveSpeedX;
        }
        if (counter > 320) {
            moveSpeedY = -moveSpeedY;
            counter = 0;
        }
        if(moveSpeedX < 0) {
            left = true;
        } else {
            right = true;
        }
        this.setLocation(this.getX() + moveSpeedX, this.getY() + moveSpeedY);
        if (checkCollision(stalkRange)) {
            this.setLocation(oldX, oldY);
        }

    }

    public void spawnBullet() {
        BulletDamage b = new BulletDamage(4, damage, target, "tear.png");
        this.getWorld().addObject(b, this.getX(), this.getY());
        shootCounter = 0;
    }
    
    public boolean checkCollision(int stalkRange) {
        List<Actor> intersectingObjects = this.getObjectsInRange(this.getImage().getWidth(), null);
         
        for (Object a : intersectingObjects) {
            
            if (a instanceof Obstacle) {
                   List<Actor> leftList = this.getObjectsAtOffset(-this.getImage().getWidth()-10, 0, null);
                   List<Actor> rightList = this.getObjectsAtOffset(this.getImage().getWidth()-10, 0, null);
                   List<Actor> topList = this.getObjectsAtOffset(0, -this.getImage().getHeight()-10, null);
                   List<Actor> downList = this.getObjectsAtOffset(0, this.getImage().getHeight()+10, null);
                   for (Object leftA : leftList) {
                       if ((leftA instanceof Obstacle || leftA instanceof Enemy) && !(leftA instanceof Projectiles)) {
                           moveSpeedX = moveSpeedX * -1;
                           left = false;
                           right = true;
                           return true;
                        }
                   }
                   for (Object rightA : rightList) {
                       if ((rightA instanceof Obstacle || rightA instanceof Enemy) && !(rightA instanceof Projectiles)) {
                           moveSpeedX = moveSpeedX * -1;
                           left = true;
                           right = false;
                           return true;
                        }
                   }
                   for (Object topA : topList) {
                       if ((topA instanceof Obstacle  || topA instanceof Enemy) && !(topA instanceof Projectiles)) {
                           moveSpeedY = moveSpeedY * -1;
                           return true;
                        }
                   }
                   for (Object downA : downList) {
                       if ((downA instanceof Obstacle || downA instanceof Enemy) && !(downA instanceof Projectiles)) {
                           moveSpeedY = moveSpeedY * -1;
                           return true;
                        }
                   }
                   //counter = counter - randomNumber(200, 100);
                return true;
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
}
