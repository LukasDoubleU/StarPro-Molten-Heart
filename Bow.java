import java.util.ArrayList;
import java.util.List;

import greenfoot.GreenfootImage;

public class Bow extends Weapon {

    public Bow() {
        super("BowAndArrow/Bow.png", 2, 50);
    }

    @Override
    public Attack getAttack() {
        return new Arrow(this);
    }

    public static class Arrow extends Attack {

        boolean markedForRemoval = false;
        Direction direction;

        public Arrow(Weapon weapon) {
            super(weapon);
            // Gleiche die Blickrichtung dem Spieler an
            direction = Player.get().getDirection();
            int rotation = direction.getRotation();
            setRotation(rotation);
        }

        @Override
        public void act() {
            super.act();

            // Bewege den Pfeil vorwärts
            move();

            removeIfNecessary();

            if (markedForRemoval) {
                remove();
            }
        }

        private void move() {
            if (Direction.Up.equals(direction)) {
                setLocation(getX(), getY() - getMoveSpeed());
            } else if (Direction.Left.equals(direction)) {
                setLocation(getX() - getMoveSpeed(), getY());
            } else if (Direction.Down.equals(direction)) {
                setLocation(getX(), getY() + getMoveSpeed());
                getWorld().setPaintOrder(Player.class);
            } else if (Direction.Right.equals(direction)) {
                setLocation(getX() + getMoveSpeed(), getY());
            }
        }

        private int getMoveSpeed() {
            // Immer a bißl schneller als der Spieler-Dude
            return Player.get().getMoveSpeed() + 3;
        }

        private void removeIfNecessary() {

            // Am Weltrand
            if (isAtEdge()) {
                markedForRemoval = true;
            }
            // Überlappung mit einem Objekt
            @SuppressWarnings("unchecked")
            List<Obstacle> obj = getIntersectingObjects(Obstacle.class);
            List<Obstacle> nonEnemies = new ArrayList<Obstacle>();
            for (Obstacle obstacle : obj) {
                if (!(obstacle instanceof Enemy)) {
                    nonEnemies.add(obstacle);
                }
            }
            if (!nonEnemies.isEmpty()) {
                markedForRemoval = true;
            }
        }

        @Override
        protected void processHit(Enemy enemy) {
            super.processHit(enemy);
            // Entferne den Pfeil nach einem Treffer
            markedForRemoval = true;
        }

        @Override
        public GreenfootImage getUsageImage() {
            return new GreenfootImage("BowAndArrow/Arrow.png");
        }

        @Override
        public GreenfootImage getHitImage() {
            return new GreenfootImage("red_yellow_splash_small.png");
        }

        @Override
        protected void processDuration() {
            // Pfeile sollen nicht nach Zeit verschwinden
        }

    }

}
