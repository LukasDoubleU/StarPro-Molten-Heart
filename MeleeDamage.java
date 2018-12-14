
import greenfoot.*;
import java.util.*;

public class MeleeDamage extends Melee {

    public MeleeDamage(int moveSpeed, int lifeCount, String imgPath) {
        super(moveSpeed, lifeCount, imgPath);
        //AENDERN NOCH AUF 2
        damage = 0;
        stalkRange = 38;
        viewDistance = 200;
    }
    
    public MeleeDamage(int moveSpeed, int lifeCount, String imgPath, int miniCounte) {
        this(moveSpeed, lifeCount, imgPath);
        damage = 2;
        stalkRange = 38;
        viewDistance = 200;
        miniEnemy = true;
    }

    public MeleeDamage(int moveSpeed) {
        this(moveSpeed, 3, "ghost.png");
    }

    public MeleeDamage() {
        this(4, 3, "ghost.png");
    }

    public void act() {

        if (target != null) {
            followTarget();
            damagePlayer();
        } else {
            movePattern();
            target = getTarget();

        }

    }

    public void damagePlayer() {
        List playerInRange = new ArrayList();
        playerInRange = this.getObjectsInRange(stalkRange+2, Player.class);
        for (Object p : playerInRange) {
            if (p instanceof Player) {
                Player.get().damage(damage);
            }
        }

    }
    
    public void damage(int damage) {
        this.lifeCount = lifeCount - damage;
        if (lifeCount < 0) {
            if(miniEnemy){
                MiniBoss a = getBoss();
                a.setMiniCounter();
            }
            level.monstercount--;
            this.getWorld().removeObject(this);
        }
    }

    public void movePattern() {

        int oldX = this.getX();
        int oldY = this.getY();
        if (counter <= 40) {
            this.setLocation(this.getX() + moveSpeed, this.getY());
            if (checkCollision(stalkRange)) {
                this.setLocation(oldX, oldY);
            }
        } else if (counter <= 80) {
            this.setLocation(this.getX(), this.getY() + moveSpeed);
            if (checkCollision(stalkRange)) {
                this.setLocation(oldX, oldY);
            }
        } else if (counter <= 120) {
            this.setLocation(this.getX() - moveSpeed, this.getY());
            if (checkCollision(stalkRange)) {
                this.setLocation(oldX, oldY);
            }
        } else if (counter <= 160) {
            this.setLocation(this.getX(), this.getY() - moveSpeed);
            if (checkCollision(stalkRange)) {
                this.setLocation(oldX, oldY);
            }
        } else if (counter == 161) {
            counter = 0;
        }
        counter++;
    }
    
    public MiniBoss getBoss() {
        List actorinrange = new ArrayList();
        actorinrange = this.getObjectsInRange(4000, MiniBoss.class);

        for (Object a : actorinrange) {
            if (a instanceof MiniBoss) {
                return (MiniBoss) a;
            }

        }
        return null;
    }

}
