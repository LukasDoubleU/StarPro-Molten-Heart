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

    private int cooldownTimer = 0; 

    public Lava(double pos){
        if(pos == 23.01 || pos == 23.21 || pos == 23.61 || pos == 23.81){
            pos = 23.4; 
        }
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
        }else if(cooldownTimer > -500){
            //Bisschen versetzt, damit sich die Lava nicht 
            //durch benachbarte Lava direkt wieder aktiviert
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
