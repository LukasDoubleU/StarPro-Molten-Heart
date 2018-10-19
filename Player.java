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
        if ("w".equals(key) || "up".equals(key)) {
            setRotation(270);
        }
        if ("a".equals(key) || "left".equals(key)) {
            setRotation(180);
        }
        if ("s".equals(key) || "down".equals(key)) {
            setRotation(90);
        }
        if ("d".equals(key) || "right".equals(key)) {
            setRotation(0);
        }

        move(10);
    }
}