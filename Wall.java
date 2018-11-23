import greenfoot.*;

public class Wall extends Obstacle {

    public Wall(double x) {
        if(x==1.1) {
            //Transparents rechts
            setImage("bright_wall_width.png");
        } else if(x==1.2) {
            //Transparents links
            setImage("bright_wall_width_mirrored.png");
        } else if(x==1.3) {
            //Transparents oben
            setImage("bright_wall_height.png");
        }else if(x==1.4) {
            //Transparents unten
<<<<<<< HEAD
            setImage("bright_wall_height_mirrored.png");
        }else if (x==1.5) {
=======
            setImage("bright_wall_height_transp.png");
        }else if (x==5) {
>>>>>>> 4d08bd807c795aed160cd240ff688d856197d958
            setImage("bright_wall.png");
        }
    }
}
