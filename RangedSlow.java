import greenfoot.*;
import java.util.*;

/**
 * Write a description of class RangedSlow here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class RangedSlow extends Ranged
{
    /**
     * Act - do whatever the RangedSlow wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    int slow;
    int spiderWallRange;
    int wallType;
    boolean test = true;
    
    
    public RangedSlow(int newMov_Speed, int newLifeCount, int newWallType) {
        super(newMov_Speed, newLifeCount);
        slow = 4;
        setImage("spider.png");
        stalkRange = 0;
        viewDistance = 200;
        spiderWallRange = 20;
        wallType = newWallType;
    }
    
    public RangedSlow(int newMov_Speed, int wallType) {
        this(newMov_Speed, 1, wallType);
    }
        
    
    public RangedSlow(int wallType) {
        this(3, 1, wallType);
    }
        
    public RangedSlow(){
        this(3);
    }
    public void act() 
    {
        if(test) {
            this.getWorld().addObject(new Wall(wallType), this.getX(), this.getY());
            test = false;
        }
        
        if(target == null) {
            target = getTarget();
        }
        
        if(target != null) {
            //if(checkCollision(stalkRange)) {
                followTarget();
            //}

            if(counter > 100) {
                spawnBullet();
            }
        }else {
            if(!checkCollision(stalkRange)) {
                movePattern();
            }
        }
        counter++;
        
    }
    
    public void followTarget() {
        int oldX = this.getX();
        int oldY = this.getY();
        if(this.getX() > target.getX()) {
            List wallList = new ArrayList();
            wallList = getObjectsAtOffset(-spiderWallRange, 0, Wall.class);
            if(!wallList.isEmpty()) {
                this.setLocation(this.getX()-mov_speed+(mov_speed/2), this.getY());
            }
        }
        else if(this.getX() < target.getX()){
            List wallList = new ArrayList();
            wallList = getObjectsAtOffset(spiderWallRange, 0, Wall.class);
            if(!wallList.isEmpty()) {
                this.setLocation(this.getX()+mov_speed-(mov_speed/2), this.getY());
            }
        }
        if(this.getY() > target.getY()) {
            List wallList = new ArrayList();
            wallList = getObjectsAtOffset(0, -spiderWallRange, Wall.class);
            if(!wallList.isEmpty()) {
                this.setLocation(this.getX(), this.getY()-mov_speed+(mov_speed/2));
            }
        }
        else if(this.getY() < target.getY()){
            List wallList = new ArrayList();
            wallList = getObjectsAtOffset(0, spiderWallRange, Wall.class);
            if(!wallList.isEmpty()) {
                this.setLocation(this.getX(), this.getY()+mov_speed-(mov_speed/2));
            }
        }
        this.setRotation(0);
    }
    
    public void followTtarget() {
        
        int xDistance;
        int yDistance;
        boolean targetHigher = false;
        boolean targetLower = false;
        boolean targetLeft = false;
        boolean targetRight = false;
        if(target.getX() > this.getX()) {
            xDistance = target.getX() - this.getX();
            targetRight = true;
        } else if(target.getX() < this.getX()){
            xDistance = this.getX() - target.getX();
            targetLeft = true;
        } else {
            xDistance = 0;
        }
        
        if(target.getY() > this.getY()) {
            yDistance = target.getY() - this.getY();
            targetLower = true;
        } else if(target.getY() < this.getY()){
            yDistance = this.getY() - target.getY();
            targetHigher = true;
        } else {
            yDistance = 0;
        }
        
        if(xDistance > yDistance) {
            if(targetLower) {
                List wallList = new ArrayList();
                wallList = getObjectsAtOffset(0, spiderWallRange, Wall.class);
                if(!wallList.isEmpty()) {
                    this.setLocation(this.getX(), this.getY()+yMov_speed);
                }
            }
            if(targetHigher) {
                List wallList = new ArrayList();
                wallList = getObjectsAtOffset(0, -spiderWallRange, Wall.class);
                if(!wallList.isEmpty()) {
                    this.setLocation(this.getX(), this.getY()-yMov_speed);
                }
            }
            
        }
        if(xDistance < yDistance) {
            if(targetLeft) {
                List wallList = new ArrayList();
                wallList = getObjectsAtOffset(-spiderWallRange, 0, Wall.class);
                if(!wallList.isEmpty()) {
                    this.setLocation(this.getX()-xMov_speed, this.getY());
                }
            }
            if(targetRight) {
                List wallList = new ArrayList();
                wallList = getObjectsAtOffset(spiderWallRange, 0, Wall.class);
                if(!wallList.isEmpty()) {
                    this.setLocation(this.getX()+xMov_speed, this.getY());
                }
            }
            
        }
    }
    
    public void movePattern() {
        int oldX = this.getX();
        int oldY = this.getY();
        if(counter==30){
            xMov_speed = -xMov_speed;
        }
        if(counter==60){
            yMov_speed = -yMov_speed;
        }
        if(counter==90){
            xMov_speed = -xMov_speed;
        }
        if(counter==120){
            yMov_speed = -yMov_speed;
            counter = 0;
        }
        this.setLocation(this.getX()+xMov_speed, this.getY()+yMov_speed);
        if(checkCollision(stalkRange)) {
                this.setLocation(oldX, oldY);
        }
    }
    
    public void spawnBullet() {
        BulletSlow b = new BulletSlow(6, slow, target, "spider_web.png");
        this.getWorld().addObject(b, this.getX(), this.getY());
        counter = 0;
    }
    

}
