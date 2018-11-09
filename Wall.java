import greenfoot.*;

public class Wall extends Obstacle {

    public Wall(double x) {
        if(x==1) {
            //Transparents rechts
            setImage("bright_wall_width.png");
        } else if(x==2) {
            //Transparents links
            setImage("bright_wall_width_mirrored.png");
        } else if(x==3) {
            //Transparents oben
            setImage("bright_wall_height.png");
        }else if(x==4) {
            //Transparents unten
            setImage("bright_wall_height_mirrored.png");
        }else if (x==5) {
            setImage("bright_wall.png");
        }
    }
    
    public void act() {
    }
}
