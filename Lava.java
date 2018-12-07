import greenfoot.*;
import java.util.List;
import java.util.ArrayList;  

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

    private static final int COOLDOWN = 500; 

    public boolean status; 
    public double type; 

    private int cooldownTimer = 0; 

    public ArrayList<Lava> neighbours = new ArrayList<Lava>(); 

    public Lava(double pos){
        this.type = pos; 
        if(pos == 23.01 || pos == 23.21 || pos == 23.61 || pos == 23.81){
            pos = 23.4; 
        }
        this.ground = "Interactive World Objects/lavarock_ground_" + pos + ".png";
        this.lava_ground = "Interactive World Objects/lava_ground_" + pos + ".png";
        setImage(ground);
        this.status = false;

    }

    public void act(){
        //Beim ersten Act-Durchlauf soll er sich alle seine Nachbarn suchen und speichern
        if(this.neighbours.size() == 0){
            setAllNeighbours();
        }

        if(status){
            dealDamageOnCollision();
            cooldownTimer--; 
        }

        if(cooldownTimer > -1*COOLDOWN){
            //Bisschen versetzt, damit sich die Lava nicht 
            //durch benachbarte Lava direkt wieder aktiviert
            cooldownTimer--;
        }else{
            if(neighbourIsHot()){
                transform();
            }
        }

        if(cooldownTimer == 0 && status){
            transform(ground,false, -1*COOLDOWN); 
        }

    }

    //Checkt ob die Nachbarn auch heiß sind
    private boolean neighbourIsHot(){
        boolean ret = false; 
        for(Lava neighbourLava : neighbours){
            if(neighbourLava.status){
                ret = true; 
                break; 
            }
        }
        return ret; 
    }

    //Setzt alle Nachbarn um davon die Stats zu prüfen
    private void setAllNeighbours(){
        if(this.type == 23.0 || this.type == 23.81){ 
            neighbours.addAll(getObjectsAtOffset(-40,0,Lava.class));
            neighbours.addAll(getObjectsAtOffset(0,40,Lava.class));
            neighbours.addAll(getObjectsAtOffset(40,40,Lava.class));
        }else if(this.type == 23.1){ 
            neighbours.addAll(getObjectsAtOffset(40,-40,Lava.class));
            neighbours.addAll(getObjectsAtOffset(-40,0,Lava.class));
            neighbours.addAll(getObjectsAtOffset(40,0,Lava.class));
            neighbours.addAll(getObjectsAtOffset(0,40,Lava.class));
            neighbours.addAll(getObjectsAtOffset(40,40,Lava.class));
        }else if(this.type == 23.2 || this.type == 23.61){ 
            neighbours.addAll(getObjectsAtOffset(-40,0,Lava.class));
            neighbours.addAll(getObjectsAtOffset(0,-40,Lava.class));
            neighbours.addAll(getObjectsAtOffset(-40,40,Lava.class));
        }else if(this.type == 23.3){ 
            neighbours.addAll(getObjectsAtOffset(0,-40,Lava.class));
            neighbours.addAll(getObjectsAtOffset(0,40,Lava.class));
            neighbours.addAll(getObjectsAtOffset(40,-40,Lava.class));
            neighbours.addAll(getObjectsAtOffset(40,40,Lava.class));
            neighbours.addAll(getObjectsAtOffset(40,0,Lava.class));
        }else if(this.type == 23.5){ 
            neighbours.addAll(getObjectsAtOffset(0,-40,Lava.class));
            neighbours.addAll(getObjectsAtOffset(0,40,Lava.class));
            neighbours.addAll(getObjectsAtOffset(-40,-40,Lava.class));
            neighbours.addAll(getObjectsAtOffset(-40,40,Lava.class));
            neighbours.addAll(getObjectsAtOffset(-40,0,Lava.class));
        }else if(this.type == 23.6 || this.type == 23.21){ 
            neighbours.addAll(getObjectsAtOffset(40,0,Lava.class));
            neighbours.addAll(getObjectsAtOffset(0,-40,Lava.class));
            neighbours.addAll(getObjectsAtOffset(40,-40,Lava.class));
        }else if(this.type == 23.7){ 
            neighbours.addAll(getObjectsAtOffset(-40,0,Lava.class));
            neighbours.addAll(getObjectsAtOffset(40,0,Lava.class));
            neighbours.addAll(getObjectsAtOffset(-40,40,Lava.class));
            neighbours.addAll(getObjectsAtOffset(0,40,Lava.class));
            neighbours.addAll(getObjectsAtOffset(40,40,Lava.class));
        }else if(this.type == 23.8 || this.type == 23.01){ 
            neighbours.addAll(getObjectsAtOffset(-40,0,Lava.class));
            neighbours.addAll(getObjectsAtOffset(0,-40,Lava.class));
            neighbours.addAll(getObjectsAtOffset(-40,-40,Lava.class));
        }else{ //z.b. 23.4
            neighbours.addAll(getNeighbours(40,true,Lava.class));
        }

    }

    //Zum anzünden verwenden
    public void transform(){
        this.transform(lava_ground,true,COOLDOWN);
    }

    //zum an und abzünden verwenden
    private void transform(String img, boolean status, int coolDownTimer){
        getImage().drawImage(new GreenfootImage(img), 0,0);
        this.status = status; 
        cooldownTimer = coolDownTimer; 
    }

    public void dealDamageOnCollision()
    {
        if(this.getOneObjectAtOffset(10, 10, Player.class) != null) {
            Player.get().damage(1);
        }

    }
}