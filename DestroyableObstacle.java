/**
 * Write a description of class DestroyableObstacle here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class DestroyableObstacle extends Enemy {
    
    public DestroyableObstacle(double x) {
        this(0,1,"");
        String imgPath = ""; 
        if (x == 1) {
            imgPath = "img_utilities/rock.png";
        } else if (x == 2) {
            imgPath = "img_utilities/barrel.png";
        } else if (x == 3) {
            imgPath = "img_utilities/slime.png";
        }
        setImage(imgPath);
    }

    public DestroyableObstacle(int newMov_speed, int newLifeCount, String imgPath) {
        //super(newMov_speed, newLifeCount, imgPath);
        this();
    }
    
    public DestroyableObstacle(){}
    
}
