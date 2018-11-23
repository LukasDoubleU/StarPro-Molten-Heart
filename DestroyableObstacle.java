/**
 * Write a description of class DestroyableObstacle here.
 * 
 * @author (your name)
 * @version (a version number or a date)
 */
public class DestroyableObstacle extends Enemy
{
    int lifeCount;
    
    public DestroyableObstacle(double x) {
        super(0, 1);
        
        if(x==2) {
            setImage("img_utilities/rock.png");
        } else if (x==3) {
            setImage("img_utilities/barrel.png");
        } else if (x==4) {
            setImage("img_utilities/slime.png");
        }
    }
}
