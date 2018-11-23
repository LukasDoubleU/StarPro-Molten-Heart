import greenfoot.*;
import java.util.List; 

/**
 * Write a description of class Lava here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Lava extends InteractiveObjects
{
    private String ground; 
    private String lava_ground; 

    public boolean status; 

    private int cooldownTimer; 

    public Lava(double pos){
        this.ground = "Interactive World Objects/lavarock_ground_" + pos + ".png";
        this.lava_ground = "Interactive World Objects/lava_ground_" + pos + ".png";
        setImage(ground);
        this.status = false;
    }

    public void act(){
        List<Lava> lavaInRange = getObjectsInRange(40, Lava.class);
        if(status){
            dealDamageOnCollision();
            cooldownTimer--; 
        }else{
            for(Lava neighbourLava : lavaInRange){
                if(neighbourLava.status){
                    this.transform();
                    break;
                }
            }
        }

        if(cooldownTimer == 0 && status){
            setImage(ground);
            this.status = false;  
        }
    }

    public void transform(){
        getImage().drawImage(new GreenfootImage(lava_ground), 0,0);
        status = true; 
        cooldownTimer = 500; 
    }

    public void dealDamageOnCollision()
    {
        if(this.getOneObjectAtOffset(10, 10, Player.class) != null) {
            Player.get().damage(1);
        }

    }
}
