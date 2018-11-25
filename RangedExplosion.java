import greenfoot.*;

/**
 * Write a description of class RangedExplosion here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class RangedExplosion extends Ranged
{
    /**
     * Act - do whatever the RangedExplosion wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    
    public RangedExplosion(int newMov_speed, int newLifeCount, String imgPath) {
        super(newMov_speed, newLifeCount, imgPath);
        damage = 3;
        stalkRange = 150;
        viewDistance = 200;
    }
    
    public RangedExplosion(int newMov_Speed) {
        this(newMov_Speed, 3, "teddybear.png");
    }
    
    public RangedExplosion() {
        this(2, 3, "teddybear.png");
    }
    
    public void act() 
    {
        if(counter == 100) {
            spawnBullet();
            
        }
        counter++;
    }
    
    public void spawnBullet() {
        BulletExplosion b = new BulletExplosion(6, damage, "bomb_proj.png");
        this.getWorld().addObject(b, this.getX(), this.getY());
        counter = 0;
    }

}
