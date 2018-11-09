import greenfoot.*;
import java.util.*;
import java.util.Random;
import java.util.List;
import java.util.Arrays;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Write a description of class LavaBoss here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class LavaBoss extends Boss
{
    /**
     *  Diese parameter ist fuer die Klasse fireCircle()
     *  @param counter : dient um die Schuesse in einer bestimmten zeit abzufeuern
     *  @param rotateBool : dient für anderes Verhalten des Schießens
     *  @param rotateShootVV : Visuele Vorwahnung, gibt den Spieler Zeit in sicherheit zu gehen, bevor er schießt 
     *  @param shootAtRotation[] : sind extra position, wo abgeschossen wird
     *  @param target : instanz Player wird gespeichert
     *  @param fireTimer : dient zum Timen der verschiedenen Bilder des Bosses
     */
    private int rotateShoot = 15;
    private boolean rotateBool = true;
    private boolean rotateShootVV = false;
    private final static int shootAtRotation[]= {45,135,225,315};
    private Player target = null;
    private int fireTimer = 0;
   
    /**
     * Diese Parameter ist für die Klasse knockBack()
     * @param knockTimer : dient zum Timen der verschiedenen Bilder des Bosses
     * 
     */
    private int knockTimer = 0;
    private boolean knockShootVV = true;
    
    /**
     * Instruktor des LavaBoss : image wird gesetzt
     */
    public LavaBoss(){
        setImage("boss/boss1.png");
    }
    
    /**
     * Act - do whatever the LavaBoss wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    
    public void act() 
    {   
        if(target==null){
            target = getTarget();
        }
        addKnockTimer();
        
        knockBack();
    }
    

    
    public void knockBack(){
        if(knockShootVV==true){
            int playerX = target.getX();
            int playerY = target.getY();
            this.turnTowards(playerX, playerY);
            int thisRotate = this.getRotation();
           // knockBackRotate(thisRotate);
            }
           
            setImage("boss/boss1.3.png");
            if(knockTimer==50){ //50 = 1-2 sek
                knockTimer = 0;
                knockShootVV = false;
            }else{
                setImage("boss/boss1.png");
        }
    
    }
    
    
    /**
     * Funktion fireCircle
     * der Boss dreht sich und schießt dabei Feuerkugeln
     */
    public void fireCircle() {
        //er ist kurz davor die Feuerkugeln zu schießen und gibt dem Player eine visuelle Vorwanung 
        if(rotateShootVV==true){
            this.setImage("boss/boss1.1.png");
            //Nachdem der Timer fertig ist, setzt er ein anderes Bild und feuert die Kugeln
            if(fireTimer==60){ //100 = 2-3 sek
                this.setImage("boss/boss1.2.png");
                fireTimer = 0;
                rotateShootVV=false;
          
            }
        }else{
            /**
             * Hier dreht sich der Boss und schießt dabei mit Lavakugeln
             */
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
    
    /**
     * Funktion um den Player zurückzugeben
     */
    public Player getTarget() {
        List actorinrange = new ArrayList();
        actorinrange = this.getObjectsInRange(1000, Player.class);
        
        for(Object a : actorinrange)  {
            if(a instanceof Player) {
                return (Player) a;
            }
            
        }
        return null;
    }
    
    /**
     * Timer um die Variable fireTimer hochzucounten
     */
    public void addFireTimer(){
        fireTimer++;
    }
    
    /**
     * Timer um die Variable knockTimer hochzucounten
     */
    public void addKnockTimer(){
        knockTimer++;
    }
}

