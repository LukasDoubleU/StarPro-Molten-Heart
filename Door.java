import greenfoot.*;

public class Door extends Actor {

    public Door(int x) {
       
        if(x==11) {         
            setImage("door/door_left_headingEast.png");           
        } else if(x==12) {
            setImage("door/door_right_headingEast.png");
        } else if(x==13) {
            setImage("door/door_right_headingWest.png");
        } else if(x==14) {
            setImage("door/door_left_headingWest.png");
        }
    }
    
    
}
