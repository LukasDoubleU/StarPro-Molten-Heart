import greenfoot.*;

/**
 * Write a description of class Lava here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Lava extends InteractiveObjects
{
    private final String GROUND = "Interactive World Objects/lavarock_ground.png"; 
    private final String LAVA_GROUND = "Interactive World Objects/lava_ground.png"; 

    private boolean status; 

    private int cooldownTimer; 

    public Lava(){
        setImage(GROUND);
        this.status = false;
    }

    public void act(){
        if(status){
            dealDamageOnCollision();
            cooldownTimer--; 
        }

        if(cooldownTimer == 0 && status){
            setImage(GROUND);
            this.status = false;  
        }
    }

    public void transform(){
        getImage().drawImage(new GreenfootImage(LAVA_GROUND), 0,0);
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
