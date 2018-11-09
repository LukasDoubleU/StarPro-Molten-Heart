import greenfoot.*;

public class Wall extends Obstacle {

    public Wall(int x) {
        if(x==1) {
            setImage("bright_wall_width.png");
        } else if(x==2) {
            setImage("bright_wall_width_mirror.png");
        } else if(x==3) {
            setImage("bright_wall_height.png");
        }else if(x==4) {
            setImage("bright_wall_height_mirror.png");
        }
    }
    
    public void act() {
    }
}
