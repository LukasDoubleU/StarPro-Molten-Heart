import greenfoot.GreenfootImage;

/**
 * Write a description of class MoltenHeart here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class MoltenHeart extends Item {
    public MoltenHeart() {
        setImage("Molten_Heart.png");
    }

    /**
     * Act - do whatever the MoltenHeart wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    @Override
    public void act() {
        // Add your action code here.
    }

    /*
     * TODOS Aktuell wurde nur das Skelett angelegt. Falls notwendig weitere Sachen
     * ausfüllen
     */

    @Override
    public void _use() {

    }

    /**
     * Die Zeit für die ein Item nach der Verwendung nicht benutzt werden kann
     */
    @Override
    public int getCooldown() {
        return 0;
    }

    /**
     * Bild, das das Item repräsentiert, wenn es auf dem Boden liegt
     */
    @Override
    public GreenfootImage getImageOnGround() {
        return null;
    }

    /**
     * Bild, das das Item repräsentiert, wenn es sich im Inventar befindet
     */
    @Override
    public GreenfootImage getImageInInventory() {
        return null;
    }

    /**
     * Bild das angezeigt wird, wenn das Item verwendet wird
     */
    @Override
    public GreenfootImage getUsageImage() {
        return null;
    }
}
