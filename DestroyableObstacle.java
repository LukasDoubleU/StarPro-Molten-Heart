/**
 * Write a description of class DestroyableObstacle here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class DestroyableObstacle extends Enemy {
    
    public DestroyableObstacle(double x) {

        if (x == 1) {
            this(0, 1, "img_utilities/rock.png");
        } else if (x == 2) {
            this(0, 1, "img_utilities/barrel.png");
        } else if (x == 3) {
            this(0, 1, "img_utilities/slime.png");
        }else {
            this(0, 1, "img_utilities/slime.png");
        }
    }

    public DestroyableObstacle(int newMov_speed, int newLifeCount, String imgPath) {
        super(newMov_speed, newLifeCount, imgPath);
    }
    
}
