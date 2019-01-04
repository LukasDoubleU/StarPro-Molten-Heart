/**
 * Write a description of class DestroyableObstacle here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class DestroyableObstacle extends Enemy {

    double x = 0;

    public DestroyableObstacle(double x) {
        this(0, 1, "");
        String imgPath = "";
        if (x == 2) {
            imgPath = "img_utilities/rock.png";
        } else if (x == 3) {
            imgPath = "img_utilities/barrel.png";
        } else if (x == 4) {
            imgPath = "img_utilities/slime.png";
        } else {
            imgPath = "img_utilities/rock.png";
        }
        this.x = x;
        setImage(imgPath);
    }

    public double getObjectX() {
        return x;
    }

    public DestroyableObstacle(int moveSpeed, int lifeCount, String imgPath) {
        // super(moveSpeed, lifeCount, imgPath);
        this();
    }

    public DestroyableObstacle() {
    }

}
