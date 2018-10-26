import greenfoot.*;

/**
 * Write a description of class LavaBoss here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class LavaBoss extends Boss
{
    /**
     *  @param counter : dient um die Schuesse in einer bestimmten zeit abzufeuern
     */
    private int counter = 9;
    private static final int counterEnd = 9;
    
    public LavaBoss(){
        setImage("teddybear.png");
    }
    
    /**
     * Act - do whatever the LavaBoss wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    
    public void act() 
    {   
        turn(2);
        if(counter==counterEnd){
            BulletDamage b = new BulletDamage(4, 1,this.getRotation(), null, "Lava_Projectile.png");
            
            this.getWorld().addObject(b, this.getX(), this.getY());
            counter = 0;
        }
        counter++;
    }    
}
