import java.util.Timer;
import java.util.TimerTask;

import greenfoot.Actor;

public class Door extends Actor {

    private boolean closedoor;
    private double x = 0.0;

    public Door(Level l, double x, boolean y) {
        this.closedoor = y;
        this.x = x;
        if (l.getMonstercount() == 0) {
        } else {
            if (x == 11) {
                setClosingTimer("transparent.png");
                setImage("door/upper_east_new.png");
            } else if (x == 12) {
                setClosingTimer("transparent.png");
                setImage("door/lower_east_new.png");
            } else if (x == 13) {
                setImage("transparent.png");
            } else if (x == 14) {
                setImage("transparent.png");
            }
        }
    }

    private void setClosingTimer(final String str) {
        Timer t = new Timer();
        TimerTask task = new TimerTask() {

            @Override
            public void run() {
                setImage(str);
            }
        };
        t.schedule(task, 5000);
    }

    @Override
    public void act() {
        boolean Player;
        Level l = (Level) getWorld();
        if (l.getMonstercount() == 0) {
            if (x == 13) {
                setImage("door/upper_west_new.png");
            } else if (x == 14) {
                setImage("door/lower_west_new.png");
            }
        }
        if (getOneIntersectingObject(Player.class) == null) {
            Player = false;
        } else {
            Player = true;
        }
        if (closedoor && Player) {
            ((Level) getWorld()).finish();
        }
    }
}
