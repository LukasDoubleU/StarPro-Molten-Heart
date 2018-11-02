import greenfoot.*;

public class Door extends Actor {

    public Door(int x) {
       
        if(x==11) {         
            setImage("door/upper_east.png");           
        } else if(x==12) {
            setImage("door/lower_east.png");
        } else if(x==13) {
            setImage("door/upper_west.png");
        } else if(x==14) {
            setImage("door/lower_west.png");
        }
    }
    
    
}
