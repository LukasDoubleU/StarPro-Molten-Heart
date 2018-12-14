
import java.util.ArrayList;
import java.util.List;

public class MeleeDamage extends Melee {

    public MeleeDamage(int moveSpeed, int lifeCount, String imgPath) {
        super(moveSpeed, lifeCount, imgPath);
        // AENDERN NOCH AUF 2
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

    @Override
    public void act() {

        if (target != null) {
            moveSpeed = 4;
            followTarget();
            damagePlayer();
        } else {
            movePattern();
            target = getTarget();

        }

    }

    public void damagePlayer() {
        List playerInRange = new ArrayList();
        playerInRange = this.getObjectsInRange(stalkRange + 2, Player.class);
        for (Object p : playerInRange) {
            if (p instanceof Player) {
                Player.get().damage(damage);
            }
        }

    }

    @Override
    public void damage(int damage) {
        this.lifeCount = lifeCount - damage;
        if (lifeCount < 0) {
            if (miniEnemy) {
                MiniBoss a = getBoss();
                if (a != null) {
                    a.setMiniCounter();
                }
            }
            level.monstercount--;
            this.getWorld().removeObject(this);
            SoundUtil.playSound("splash_sound.wav");
        }
    }

    public void movePattern() {

        int oldX = this.getX();
        int oldY = this.getY();
        if (counter <= 60) {
            this.setLocation(this.getX() + moveSpeed, this.getY());
            if (checkCollision(stalkRange)) {
                this.setLocation(oldX, oldY);
            }
        } else if (counter <= 120) {
            this.setLocation(this.getX(), this.getY() + moveSpeed);
            if (checkCollision(stalkRange)) {
                this.setLocation(oldX, oldY);
            }
        } else if (counter <= 180) {
            this.setLocation(this.getX() - moveSpeed, this.getY());
            if (checkCollision(stalkRange)) {
                this.setLocation(oldX, oldY);
            }
        } else if (counter <= 240) {
            this.setLocation(this.getX(), this.getY() - moveSpeed);
            if (checkCollision(stalkRange)) {
                this.setLocation(oldX, oldY);
            }
        } else if (counter > 161) {
            counter = 0;
        }
        counter++;
    }
    
     public boolean checkCollision(int stalkRange) {
        List<Actor> intersectingObjects = this.getObjectsInRange(35, null);
            for (Object a : intersectingObjects) {
                if (a instanceof Obstacle || a instanceof Door) {
                    if(target == null) {
                        List<Actor> left = this.getObjectsAtOffset(-this.getImage().getWidth()-5, 0, null);
                        List<Actor> right = this.getObjectsAtOffset(this.getImage().getWidth()-5, 0, null);
                        List<Actor> top = this.getObjectsAtOffset(0, -this.getImage().getHeight()-5, null);
                        List<Actor> down = this.getObjectsAtOffset(0, this.getImage().getHeight()+5, null);
                        for (Object leftA : left) {
                            if (leftA instanceof Obstacle) {
                                moveSpeed = moveSpeed * -1;
                                return true;
                            }
                        }
                        for (Object rightA : right) {
                            if (rightA instanceof Obstacle) {
                                moveSpeed = moveSpeed * -1;
                                return true;
                            }
                        }
                        for (Object topA : top) {
                            if (topA instanceof Obstacle) {
                                moveSpeed = moveSpeed * -1;
                                return true;
                            }
                        }
                        for (Object downA : down) {
                            if (downA instanceof Obstacle) {
                                moveSpeed = moveSpeed * -1;
                                return true;
                            }
                        }
                        //counter = counter - randomNumber(200, 100);
                    }   
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
