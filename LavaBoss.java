import greenfoot.*;
import java.util.*;

import java.text.SimpleDateFormat;

/**
 * Write a description of class LavaBoss here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class LavaBoss extends Boss {
    /**
     * Diese Parameter dienen, um die verschiedenen Attacken zu varrieren
     *
     * @param attack1             : aktivierung/deaktivierung der Attacke
     * @param attack1             : aktivierung/deaktivierung der Attacke
     * @param attack1             : aktivierung/deaktivierung der Attacke
     * @param randomAttack        : gibt an, welche reihenfolge von attacken
     *                            durchgefhrt werden
     * @param attackTimer         : dient zum Timen
     * @param normalAttackCounter : timet, wie oft der Boss angreift
     */
    private boolean attack1 = true;
    private boolean attack2 = false;
    private boolean attack3 = false;
    private boolean attack4 = false;
    private int randomAttack = randomNumber(0);
    private int randomRotate = randomNumber(2);
    private int attackTimer = 0;
    private int normalAttackCounter = 40;

    /**
     * Diese parameter ist fuer die Klasse fireCircle()
     *
     * @param counter       : dient um die Schuesse in einer bestimmten zeit
     *                      abzufeuern
     * @param rotateBool    : dient fr anderes Verhalten des Schieens
     * @param rotateShootVV : Visuele Vorwahnung, gibt den Spieler Zeit in
     *                      sicherheit zu gehen, bevor er schiet
     * @param               shootAtRotation[] : sind extra position, wo abgeschossen
     *                      wird
     * @param target        : instanz Player wird gespeichert
     * @param fireTimer     : dient zum Timen der verschiedenen Bilder des Bosses
     */
    private int counter = 0;
    private int rotateShoot = 15;
    private boolean rotateBool = true;
    private boolean rotateShootVV = true;
    private final static int shootAtRotation[] = { 45, 135, 225, 315 };
    private Player target = null;
    private int fireTimer = 0;
    // private Level level = null;

    /**
     * Diese Parameter ist fr die Klasse knockBack()
     *
     * @param knockTimer    : dient zum Timen der verschiedenen Bilder des Bosses
     * @param knockShootVV  : Visuele Vorwahnung, gibt den Spieler Zeit in
     *                      sicherheit zu gehen, bevor er schiet
     * @param               playerX, playerY : speichert die x,y Koordinate vom
     *                      Player
     * @param               knockBackX, knockBackY : speichert die x,y Koordinate,
     *                      wo der Player geknockbackt werden soll
     * @param knockBackOver : dient dazu, um anzugeben, dass die Attacke vorbei ist
     */
    private int knockTimer = 0;
    private boolean knockShootVV = true;
    private int playerX;
    private int playerY;
    private int knockBackX;
    private int knockBackY;
    private boolean knockBackOver = false;

    /**
     * Instruktor des LavaBoss : image wird gesetzt
     */
    public LavaBoss() {
        super(1, 20, "boss/boss1.png");
    }

    /**
     * Funktion addedToWorld Speichert in der Variable level die World
     */
    /*
     * public void addedToWorld(World world) { level = (Level) world; }
     */

    /**
     * Act - do whatever the LavaBoss wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() {
        // Falls Spieler noch nicht in target gespeichert wurde, rufe getTarget() auf
        if (target == null) {
            target = getTarget();
        }

        if (randomAttack == 1) {
            if (attack1) {
                level.triggerLava();
                attack2 = false;
                attack3 = false;
                attack4 = false;
                /** normale attacke, 2-3 sekunden **/
                if (normalAttackCounter == 40) {
                    spawnBullet();
                }
                normalAttackCounter++;
                addAttackTimer();
                if (attackTimer == 100) {
                    attack1 = false;
                    attack2 = true;
                    attackTimer = 0;
                }
            } else if (attack2) {
                /** random lava aktivieren **/
                level.triggerLava();
                attack2 = false;
                attack3 = true;
            } else if (attack3) {
                /** fireCircle() aktivieren, 2-3 rotates **/
                fireCircle();
                if (counter == randomRotate) {
                    attack3 = false;
                    attack4 = true;
                    counter = 0;
                    rotateShootVV = true;
                    this.setImage("boss/boss1.png");
                }
            } else if (attack4) {
                /** knockBack() wird aktiviert **/
                if (attackTimer == 30) {
                    knockBack();
                    if (knockBackOver) {
                        attackTimer = 0;
                        randomAttack = randomNumber(0);
                        attack4 = false;
                        attack1 = true;
                        this.setRotation(0);
                        knockBackOver = false;
                    }
                } else {
                    addAttackTimer();
                }
            }
        } else {
            if (attack1) {
                attack2 = false;
                attack3 = false;
                attack4 = false;
                /** random Lava **/
                level.triggerLava();
                attack1 = false;
                attack2 = true;
            } else if (attack2) {
                if (attackTimer == 30) {
                    knockBack();
                    if (knockBackOver) {
                        attackTimer = 0;
                        attack2 = false;
                        attack3 = true;
                        this.setRotation(0);
                        knockBackOver = false;
                    }
                } else {
                    addAttackTimer();
                }
            } else if (attack3) {
                /** random Lava **/
                level.triggerLava();
                attack3 = false;
                attack4 = true;
            } else if (attack4) {
                /** fireCircle() **/
                fireCircle();
                if (counter == randomRotate) {
                    counter = 0;
                    attack4 = false;
                    attack1 = true;
                    this.setImage("boss/boss1.png");
                    rotateShootVV = true;
                    randomAttack = randomNumber(0);
                }
            }
        }
    }

    public void damage(int damage) {
        this.lifeCount = lifeCount - damage;
        if (lifeCount < 0) {
            this.getWorld().addObject(new MoltenHeart(), this.getX(), this.getY());
            this.getWorld().removeObject(this);
            level.decreaseMonstercount(this);
        }
    }

    /**
     * Funktion knockBack() Schiet eine Hand nach einer bestimmten Zeit und
     * knockbackt den Gegner auf eine bestimme x,y Koordinate
     */
    public void knockBack() {

        /**
         * VV = Virsuelle Vorwahnung Ermittelt die Position vom Spieler und speichert
         * dann die x,y Koordinate wo der Spieler geknockbackt werden soll
         **/
        if (knockShootVV == true) {
            playerX = target.getX();
            playerY = target.getY();
            this.turnTowards(playerX, playerY);
            int thisRotate = this.getRotation();

            if (this.getRotation() <= 45) {
                this.setRotation(22);
                knockBackX = 1093;
                knockBackY = 413;
            } else if (this.getRotation() <= 90 && this.getRotation() >= 46) {
                this.setRotation(67);
                knockBackX = 733;
                knockBackY = 527;
            } else if (this.getRotation() <= 135 && this.getRotation() >= 91) {
                this.setRotation(112);
                knockBackX = 541;
                knockBackY = 509;
            } else if (this.getRotation() <= 180 && this.getRotation() >= 136) {
                this.setRotation(157);
                knockBackX = 166;
                knockBackY = 509;
            } else if (this.getRotation() <= 225 && this.getRotation() >= 181) {
                this.setRotation(202);
                knockBackX = 163;
                knockBackY = 187;
            } else if (this.getRotation() <= 270 && this.getRotation() >= 226) {
                this.setRotation(247);
                knockBackX = 518;
                knockBackY = 120;
            } else if (this.getRotation() <= 315 && this.getRotation() >= 271) {
                this.setRotation(292);
                knockBackX = 644;
                knockBackY = 123;
            } else if (this.getRotation() >= 315) {
                this.setRotation(337);
                knockBackX = 931;
                knockBackY = 130;
            }
            knockShootVV = false;
        }

        /** Counter wird hochgecountet (ist die Dauer der Visuellen Vorwahung) **/
        addKnockTimer();
        // spawn hand neben den boss
        /*
         * Hand hand = new Hand(); this.getWorld().addObject(hand, this.getX()-70,
         * this.getY()); hand.turnTowards(playerX, playerY);
         */

        /**
         * Virsuelle Vorwahnung ist nach 1-2 sek vorbei und generiert dann eine Hand
         **/
        if (knockTimer == 50) { // 50 = 1-2 sek
            knockTimer = 0;
            BulletDamage b = new BulletDamage(4, 0, this.getRotation(), null, "boss/boss2hand.png", knockBackX,
                    knockBackY);
            this.getWorld().addObject(b, this.getX(), this.getY());
            this.setRotation(0);
            knockBackOver = true;
            knockShootVV = true;
        }
    }

    /**
     * Funktion fireCircle() der Boss dreht sich und schiet dabei Feuerkugeln
     */
    public void fireCircle() {
        /**
         * er ist kurz davor die Feuerkugeln zu schieen und gibt dem Player eine
         * visuelle Vorwanung
         **/
        if (rotateShootVV == true) {
            this.setImage("boss/boss1.1.png");
            addFireTimer();
            /**
             * Nachdem der Timer fertig ist, setzt er ein anderes Bild und feuert die Kugeln
             **/
            if (fireTimer == 60) { // 100 = 2-3 sek
                this.setImage("boss/boss1.2.png");
                fireTimer = 0;
                rotateShootVV = false;

            }
        } else {
            /** Hier dreht sich der Boss und schiet dabei mit Lavakugeln **/
            turn(3);
            int rotation = this.getRotation();
            if (rotation % rotateShoot == 0) {
                BulletDamage b = new BulletDamage(4, 1, rotation, null, "boss/lava_projectile.png");
                this.getWorld().addObject(b, this.getX(), this.getY());
            }

            if (rotation == 0) {
                counter++;
                if (rotateBool) {
                    for (int a : shootAtRotation) {
                        if (a == rotation) {
                            BulletDamage b = new BulletDamage(4, 1, rotation, null, "boss/lava_projectile.png");
                            this.getWorld().addObject(b, this.getX(), this.getY());
                        }
                    }
                    rotateShoot = rotateShoot - 3;
                    rotateBool = false;
                } else {
                    rotateShoot = rotateShoot + 3;
                    rotateBool = true;
                }
            }
        }
    }

    /**
     * Funktion spawnBullet() normale Attacke
     */
    public void spawnBullet() {
        BulletDamage b = new BulletDamage(6, damage, target, "boss/lava_projectile.png");
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
     * Funktion addFireTimer() Timer um die Variable fireTimer hochzucounten
     */
    public void addFireTimer() {
        fireTimer++;
    }

    /**
     * Funktion addKnockTimer() Timer um die Variable knockTimer hochzucounten
     */
    public void addKnockTimer() {
        knockTimer++;
    }

    /**
     * Funktion addAttackTimer() Timer um die Variable attackTimer hochzucounten
     */
    public void addAttackTimer() {
        attackTimer++;
    }

    /**
     * Funktion randomNumber gibt einen wert zwischen 0-1 + x zurueck
     */
    public int randomNumber(int x) {
        Random rand = new Random();
        return rand.nextInt(2) + x;
    }

}
