import greenfoot.*;
import java.util.*;

/**
 * Write a description of class MiniBoss here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class MiniBoss extends Boss {
    /**
     * @param attackTimer         : dient zum timen der Attacken
     * @param summonTimer         : dient zum timen, nachdem die 3 nahkaempfer
     *                            beschworen wurden
     * @param normalAttackCounter : dient zum timen der normale attacke
     * @meleeSpawned : gibt an, ob die Melee
     * @param attack1   : dient zum steuern der zwei verschiedenen Attacken
     * @param summonEnd :
     * @param enemyAlive : ueberprueft ob die 3 Gegner noch am leben sind
     */
    private int attackTimer;
    private int summonTimer;
    private int normalAttackCounter;
    private int damage;
    private boolean meleeSpawned;
    private boolean attack1;
    private boolean summonEnd;
    private boolean enemyAlive;
    private int miniCounter;

    public MiniBoss() {
        super(1, 9, "boss/boss2.1.png");
        summonTimer = 0;
        attackTimer = 0;
        normalAttackCounter = 30;
        damage = 2;
        meleeSpawned = false;
        attack1 = true;
        summonEnd = false;
        miniCounter = 0;
        this.getImage().scale(60, 60);        
    }

    /**
     * Act - do whatever the MiniBoss wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() {
        // Falls Spieler noch nicht in target gespeichert wurde, rufe getTarget() auf
        if (target == null) {
            target = getTarget();
        }

        if (attack1) {
            if (normalAttackCounter == 30) {
                spawnBullet();
            }
            addNormalAttackCounter();
            addAttackTimer();
            if (attackTimer == 180) {
                attack1 = false;
                attackTimer = 0;
            }
        } else {
            summon();
            if (summonEnd) {
                attack1 = true;
                meleeSpawned = false;
                normalAttackCounter = -60;
                attackTimer = -60;
                summonEnd = false;
            }
        }
    }

    public void damage(int damage) {
        this.lifeCount = lifeCount - damage;
        if (lifeCount <= 0) {
            this.getWorld().addObject(new Bow(), 125, 196);
            this.getWorld().addObject(new Armor.Dark(), 125, 341);
            this.getWorld().addObject(new Sword.Master(), 125, 512);
            this.getWorld().addObject(new MeleeDamage(4, 3, "boss/boss2.5.png"), this.getX(), this.getY() - 100);
            this.getWorld().removeObject(this);
            level.decreaseMonstercount(this);
        }
    }

    /**
     * Funktion summon() beschwoert 3 Gegner an bestimmten stellen
     */
    public void summon() {
        if(miniCounter==0){
            this.setImage("boss/boss2.2.png");
            this.getImage().scale(60, 60);        
        }
        addAttackTimer();
        if (attackTimer >= 30) {
            if(miniCounter==0){
                this.setImage("boss/boss2.3.png");
                this.getImage().scale(60, 60);        
                // spawn gegner
                if (!meleeSpawned) {
                    for (int i = 0; i < 3; i++) {
                        MeleeDamage b = new MeleeDamage(3, 2, "boss/boss2.5.png",1);
                        if (i == 0) {
                            this.getWorld().addObject(b, this.getX() - 150, this.getY());
                            miniCounter++;
                        } else if (i == 1) {
                            this.getWorld().addObject(b, this.getX() + 150, this.getY());
                            miniCounter++;
                        } else {
                            this.getWorld().addObject(b, this.getX(), this.getY() - 150);
                            miniCounter++;
                        }
                    }
                    meleeSpawned = true;
                }
            }
            if (attackTimer == 65) {
                this.setImage("boss/boss2.1.png");
                this.getImage().scale(60, 60);        
                summonEnd = true;
                attackTimer = 0;
            }
        }
    }

    /**
     * Funktion spawnBullet() normale Attacke
     */
    public void spawnBullet() {
        BulletDamage b = new BulletDamage(6, damage, target, "boss/lava_projectile2.png");
        this.getWorld().addObject(b, this.getX(), this.getY());
        normalAttackCounter = 0;
    }

    /**
     * Funktion getTarget() Gibt den Player zurckzugeben
     */
    public Player getTarget() {
        List actorinrange = new ArrayList();
        actorinrange = this.getObjectsInRange(4000, Player.class);

        for (Object a : actorinrange) {
            if (a instanceof Player) {
                return (Player) a;
            }

        }
        return null;
    }

    /**
     * Funktion addAttackTimer() Timer um die Variable attackTimer hochzucounten
     */
    public void addAttackTimer() {
        attackTimer++;
    }

    /**
     * Funktion addSummonTimer() Timer um die Variable summonTimer hochzucounten
     */
    public void addSummonTimer() {
        summonTimer++;
    }

    /**
     * Funktion addNormalAttackCounter() Timer um die Variable summonTimer
     * hochzucounten
     */
    public void addNormalAttackCounter() {
        normalAttackCounter++;
    }

    public void setMiniCounter(){
        miniCounter--;
    }
}
