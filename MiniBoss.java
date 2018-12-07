import greenfoot.*;
import java.util.*;

/**
 * Write a description of class MiniBoss here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MiniBoss extends Boss
{
    /**
     * @param attackTimer : dient zum timen der Attacken
     * @param summonTimer : dient zum timen, nachdem die 3 nahkaempfer beschworen wurden
     * @param normalAttackCounter : dient zum timen der normale attacke
     * @meleeCounter
     * 
     * @param attack1 : dient zum steuern der zwei verschiedenen Attacken
     * @param summonEnd : 
     */
    private int attackTimer = 0;
    private int summonTimer = 0;
    private int normalAttackCounter = 30;
    private boolean meleeSpawned = false;
    private boolean attack1 = true;
    private boolean summonEnd = false;
    
    
    
    public MiniBoss(){
        super(1,10,"boss/boss2.1.png");
    }
    
    /**
     * Act - do whatever the MiniBoss wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        //Falls Spieler noch nicht in target gespeichert wurde, rufe getTarget() auf
        if(target==null){
            target = getTarget();
        }
        
        if(attack1){
            if(normalAttackCounter==30){
                spawnBullet();
            }
            addNormalAttackCounter();
            addAttackTimer();
            if(attackTimer==120){
                attack1 = false;
                attackTimer = 0;
            }
        }else {
            summon();
            if(summonEnd){
                attack1 = true;
                meleeSpawned = false;
                summonEnd = false;
            }
        }
    }
    
    /**
     * Funktion summon()
     * beschwoert 3 Gegner an bestimmten stellen
     */
    public void summon(){
        this.setImage("boss/boss2.2.png");
        addAttackTimer();
        if(attackTimer>=30){
            this.setImage("boss/boss2.3.png");
            //spawn gegner
            if(!meleeSpawned){
                for(int i=0;i<3;i++){
                    MeleeDamage b = new MeleeDamage(4,3, "boss/boss2.5.png");
                    if(i==0){
                        this.getWorld().addObject(b, this.getX()-100, this.getY());    
                    }else if (i==1){
                        this.getWorld().addObject(b, this.getX()+100, this.getY());
                    }else{
                        this.getWorld().addObject(b, this.getX(), this.getY()-100);
                    }
                }
                meleeSpawned = true;
            }
            
            if(attackTimer==65){
                this.setImage("boss/boss2.1.png");
                summonEnd = true;
                attackTimer = 0;
            }
        }
    }
    
    /**
     * Funktion spawnBullet()
     * normale Attacke
     */
    public void spawnBullet() {
        BulletDamage b = new BulletDamage(6, damage, target, "boss/lava_projectile2.png");
        this.getWorld().addObject(b, this.getX(), this.getY());
        normalAttackCounter = 0;
    }
    
    /**
     * Funktion getTarget()
     * Gibt den Player zurckzugeben
     */
    public Player getTarget() {
        List actorinrange = new ArrayList();
        actorinrange = this.getObjectsInRange(4000, Player.class);

        for(Object a : actorinrange)  {
            if(a instanceof Player) {
                return (Player) a;
            }

        }
        return null;
    }
    
    /**
     * Funktion addAttackTimer()
     * Timer um die Variable attackTimer hochzucounten
     */
    public void addAttackTimer(){
        attackTimer++;
    }
    
    /**
     * Funktion addSummonTimer()
     * Timer um die Variable summonTimer hochzucounten
     */
    public void addSummonTimer(){
        summonTimer++;
    }
    
    /**
     * Funktion addNormalAttackCounter()
     * Timer um die Variable summonTimer hochzucounten
     */
    public void addNormalAttackCounter(){
        normalAttackCounter++;
    }
    
}
