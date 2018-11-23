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
     * Diese Parameter dienen, um die verschiedenen Attacken zu varrieren
     * @param attack1 : aktivierung/deaktivierung der Attacke
     * @param attack1 : aktivierung/deaktivierung der Attacke
     * @param attack1 : aktivierung/deaktivierung der Attacke
     * @param randomAttack : gibt an, welche reihenfolge von attacken durchgef�hrt werden
     * @param attackTimer : dient zum Timen
     */
    private boolean attack1 = false;
    private boolean attack2 = false;
    private boolean attack3 = false;
    private boolean attack4 = false;
    private int randomAttack = 0;
    private int attackTimer = 0;
    
    /**
     *  Diese parameter ist fuer die Klasse fireCircle()
     *  @param counter : dient um die Schuesse in einer bestimmten zeit abzufeuern
     *  @param rotateBool : dient f�r anderes Verhalten des Schie�ens
     *  @param rotateShootVV : Visuele Vorwahnung, gibt den Spieler Zeit in sicherheit zu gehen, bevor er schie�t 
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
     * Diese Parameter ist f�r die Klasse knockBack()
     * @param knockTimer : dient zum Timen der verschiedenen Bilder des Bosses
     * @param playerX, playerY : speichert die x,y Koordinate vom Player
     */
    private int knockTimer = 0;
    private boolean knockShootVV = true;
    private int playerX;
    private int playerY;
    
    /**
     * Instruktor des LavaBoss : image wird gesetzt
     */
    public LavaBoss(){
        super(1,10);
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
            playerX = target.getX();
            playerY = target.getY();
            
            this.turnTowards(playerX, playerY);
            int thisRotate = this.getRotation();
            
            System.out.println("DAVOR: "+this.getRotation());
            if(this.getRotation()<= 45){
                this.setRotation(22);
                System.out.println(this.getRotation());
                target.setLocation(1100,500);
            }else if(this.getRotation()<= 90 && this.getRotation()>= 46){
                this.setRotation(67);
                System.out.println(this.getRotation());
            }else if(this.getRotation()<= 135 && this.getRotation()>= 91){
                this.setRotation(112);
                System.out.println(this.getRotation());
            }else if(this.getRotation()<= 180 && this.getRotation()>= 136){
                this.setRotation(157);
                System.out.println(this.getRotation());
            }else if(this.getRotation()<= 225 && this.getRotation()>= 181){
                this.setRotation(202);
                System.out.println(this.getRotation());
            }else if(this.getRotation()<= 270 && this.getRotation()>= 226){
                this.setRotation(247);
                System.out.println(this.getRotation());
            }else if(this.getRotation()<= 315 && this.getRotation()>= 271){
                this.setRotation(292);
                System.out.println(this.getRotation());
            }else if(this.getRotation()>= 315){
                this.setRotation(337);
                System.out.println(this.getRotation());
            }
            
            //test
            knockShootVV=false;
        
            
       
           //nicht knockBackRotate(thisRotate);
            }
           
            //spawn hand neben den boss
           /* Hand hand = new Hand();
            this.getWorld().addObject(hand, this.getX()-70, this.getY());
            hand.turnTowards(playerX, playerY);*/
            if(knockTimer==50){ //50 = 1-2 sek
                knockTimer = 0;
                knockShootVV = false;
            }else{
                setImage("boss/boss1.png");
        }
    
    }
    
    
    /**
     * Funktion fireCircle
     * der Boss dreht sich und schie�t dabei Feuerkugeln
     */
    public void fireCircle() {
        //er ist kurz davor die Feuerkugeln zu schie�en und gibt dem Player eine visuelle Vorwanung 
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
             * Hier dreht sich der Boss und schie�t dabei mit Lavakugeln
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
     * Funktion um den Player zur�ckzugeben
     */
    public Player getTarget() {
        List actorinrange = new ArrayList();
        actorinrange = this.getObjectsInRange(1900, Player.class);
        
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

