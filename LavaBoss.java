import greenfoot.*;
import java.util.Random;
import java.util.List;
import java.util.Arrays;
import java.util.Timer;

/**
 * Write a description of class LavaBoss here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class LavaBoss extends Boss
{
    /**
     *  Diese parameter ist für die Klasse fireCircle()
     *  @param counter : dient um die Schuesse in einer bestimmten zeit abzufeuern
     */
    private int rotateShoot = 15;
    private boolean rotateBool = true;
    private final static int shootAtRotation[]= {45,135,225,315};
   
    public LavaBoss(){
        setImage("boss/boss3.png");
    }
    
    /**
     * Act - do whatever the LavaBoss wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    
    public void act() 
    {   
        fireCircle();
    }
    
    public void fireCircle() {
        turn(3);
        int rotation = this.getRotation();
        if(rotation%rotateShoot==0) {
            BulletDamage b = new BulletDamage(4, 1,rotation, null, "boss/lava_projectile.png");
            this.getWorld().addObject(b, this.getX(), this.getY());
        }               
        
        if(rotation==0) {
            if(rotateBool) {
                for(int a: shootAtRotation){
                    if(a == rotation) {
                        BulletDamage b = new BulletDamage(4, 1,rotation, null, "boss/lava_projectile.png");
                        this.getWorld().addObject(b, this.getX(), this.getY());
                    }
                }
                rotateShoot = rotateShoot-3;
                rotateBool=false;
            }else {
                rotateShoot = rotateShoot+3;
                rotateBool=true;
            }
        }
    }
}
