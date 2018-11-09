import greenfoot.*;

public class Door extends Actor {

    private boolean closedoor; 
    
    public Door(int x,boolean y) {
       this.closedoor = y; 
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
   
    
    public void act() {
        boolean Player;
        if(getOneIntersectingObject(Player.class)==null) {
            Player=false;
        } else {
            Player=true;
        }
        if(closedoor && Player) {
            ((Level)getWorld()).finish();
        }
    }
    
}
