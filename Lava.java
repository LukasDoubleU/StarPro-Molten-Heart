import java.util.List;
import java.util.ArrayList;  
import java.util.Iterator; 
import greenfoot.GreenfootImage;

/**
 * Write a description of class Lava here.
 * 
 * @author (your name)
 * @version (a version number or a date)
 */
public class Lava extends InteractiveObjects {
    private String ground;
    private String lava_ground;

    private static final int COOLDOWN = 500; 
    private static final int RANGE = 40; 

    public boolean status; 
    public double type; 

    private int cooldownTimer = -COOLDOWN;

    public ArrayList<Lava> neighbours = new ArrayList<Lava>(); 

    public Lava(double pos){
        if(pos == 23.01){
            this.type = 23.8; 
        }else if(pos == 23.21){
            this.type = 23.6; 
        }else if(pos == 23.61){
            this.type = 23.2; 
        }else if(pos == 23.81){
            this.type = 23.0; 
        }else{
            this.type = pos; 
        }
        
        this.ground = "Interactive World Objects/lavarock_ground_" + pos + ".png";
        this.lava_ground = "Interactive World Objects/lava_ground_" + pos + ".png";
        setImage(ground);
        this.status = false;

    }

    public void act(){
        //Beim ersten Act-Durchlauf soll er sich alle seine Nachbarn suchen und speichern
        if(this.neighbours.size() == 0){
            neighbours.addAll(this.getNeighbours(RANGE,true,Lava.class));
        }

        if(status){
            dealDamageOnCollision();
        }

        //verringere Cooldown solange bis -500 erreicht ist. 
        if(cooldownTimer > -COOLDOWN){
            //Bisschen versetzt, damit sich die Lava nicht 
            //durch benachbarte Lava direkt wieder aktiviert
            this.cooldownTimer--;
        }
        
        if(cooldownTimer == -COOLDOWN && !(status) && neighbourIsHot()){
            transform();
        }

        if(cooldownTimer == 0 && status){
            transform(ground,false, 0); 
        }

    }

    //Checkt ob die Nachbarn auch heiß sind
    private boolean neighbourIsHot(){
        for(Lava neighbourLava : neighbours){
            if(neighbourLava.status){
                return true; 
            }
        }
        return false; 
    }

    //Zum anzünden verwenden
    public void transform(){
        this.transform(lava_ground,true,COOLDOWN);
    }

    //zum an und abzünden verwenden
    private void transform(String img, boolean status, int coolDownTimer){
        getImage().drawImage(new GreenfootImage(img), 0,0);
        this.status = status; 
        this.cooldownTimer = coolDownTimer; 
    }

    public void dealDamageOnCollision() {
        if (this.getOneObjectAtOffset(10, 10, Player.class) != null) {
            Player.get().damage(1);
        }

    }
    
    private double getType(){
        return this.type; 
    }
}