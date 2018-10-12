import java.util.List;

import greenfoot.*;

public class Player extends Actor {

    public void act() {
        move();
    }

    private void move() {

        String key = Greenfoot.getKey();
        if (key == null) {
            return;
        }
        switch (key) {
        case "w":
        case "up":
            setRotation(270);
            break;
        case "a":
        case "left":
            setRotation(180);
            break;
        case "s":
        case "down":
            setRotation(90);
            break;
        case "d":
        case "right":
            setRotation(0);
            break;
        }

        move(10);
    }
}