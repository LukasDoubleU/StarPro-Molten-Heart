/**
 * Write a description of class DestroyableObstacle here.
 * 
 * @author (your name)
 * @version (a version number or a date)
 */
public class DestroyableObstacle extends Enemy {

    public DestroyableObstacle(int x) {
        
        if (x == 1) {
            super(0, 1, "img_utilities/rock.png");
        } else if (x == 2) {
            super(0, 1, "img_utilities/barrel.png");
        } else if (x == 3) {
            super(0, 1, "img_utilities/slime.png");
        }else {
            super(0, 1, "img_utilities/slime.png");
        }
    }
}
