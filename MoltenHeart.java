import greenfoot.*;

/**
 * Write a description of class MoltenHeart here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MoltenHeart extends Item
{
    public MoltenHeart(){
        setImage("Molten_Heart.png");
    }
    
    /**
     * Act - do whatever the MoltenHeart wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        // Add your action code here.
    }    
    
    
    /*TODOS
       Aktuell wurde nur das Skelett angelegt. Falls notwendig weitere Sachen ausfüllen
       */
      
      
    public void use(){
    
    }

    /**
     * Die Zeit für die ein Item nach der Verwendung nicht benutzt werden kann
     */
    public int getCooldown(){
        return 0; 
    }

    /**
     * Bild, das das Item repräsentiert, wenn es auf dem Boden liegt
     */
    public GreenfootImage getImageOnGround(){
        return null; 
    }

    /**
     * Bild, das das Item repräsentiert, wenn es sich im Inventar befindet
     */
    public GreenfootImage getImageInInventory(){
        return null; 
    }
    
    /**
     * Bild das angezeigt wird, wenn das Item verwendet wird
     */
    public GreenfootImage getUsageImage(){
        return null; 
    }
}
