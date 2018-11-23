import greenfoot.*;

public class Wall extends Obstacle {

    public Wall(double x) {
        x = x-(int)x; 
        if(x<0.2) {
            //Transparents rechts
            setImage("bright_wall_width.png");
        } else if(x<0.3) {
            //Transparents links
            setImage("bright_wall_width_mirrored.png");
        } else if(x<0.4) {
            //Transparents oben
            setImage("bright_wall_height.png");
        }else if(x<0.5) {
            //Transparents unten
            setImage("bright_wall_height_mirrored.png");
        }else if (x>0.5) {
            setImage("bright_wall.png");
        }
    }
}
