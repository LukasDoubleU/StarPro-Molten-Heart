import java.util.Arrays;

import greenfoot.Actor;
import greenfoot.Greenfoot;
import greenfoot.GreenfootImage;
import greenfoot.MouseInfo;

public class MainMenuLevel extends Level {

    Entry selectedEntry;
    Entry start = new StartEntry();
    Entry ranking = new RankingEntry();
    Entry controls = new ControlEntry();

    public MainMenuLevel() {
        super("start_Menu/start_screen.png");

        addObject(start, 640, 300);
        addObject(ranking, 640, 400);
        addObject(controls, 640, 500);

        start.select();
    }

    @Override
    public void act() {

        MouseInfo mouseInfo = Greenfoot.getMouseInfo();
        if (mouseInfo != null) {
            Actor actor = mouseInfo.getActor();
            if (actor instanceof Entry) {
                Entry entry = (Entry) actor;
                entry.select();
            }
        }

        String key = Greenfoot.getKey();
        if (key != null) {
            if (selectedEntry != null) {
                // Mit Pfeiltasten bzw. W und S kann navigiert werden
                if (anyEquals(key, "up", "w")) {
                    selectedEntry.selectPrevious();
                } else if (anyEquals(key, "down", "s")) {
                    selectedEntry.selectNext();
                } // Mit Space / Enter klicken
                else if (anyEquals(key, "space", "enter")) {
                    selectedEntry.onClick();
                }
            }
            // Mit 1-3 können die Items direkt geklickt werden
            if (key.equals("1")) {
                start.onClick();
            } else if (key.equals("2")) {
                ranking.onClick();
            } else if (key.equals("3")) {
                controls.onClick();
            }
        }

    }

    boolean anyEquals(Object obj, Object... objects) {
        return Arrays.asList(objects).contains(obj);
    }

    @Override
    public void finish() {
    }

    class StartEntry extends Entry {

        public StartEntry() {
            super("start_Menu/play_big.png", "start_Menu/play_big_inverted.png");
        }

        @Override
        void onClick() {
            restartGame();
        }

        @Override
        Entry getNextEntry() {
            return ranking;
        }

        @Override
        Entry getPreviousEntry() {
            return controls;
        }
    }

    class RankingEntry extends Entry {

        public RankingEntry() {
            super("start_Menu/highscore_big.png", "start_Menu/highscore_big_inverted.png");
        }

        @Override
        void onClick() {
            Greenfoot.setWorld(new ScoringLevel(MainMenuLevel.this));
        }

        @Override
        Entry getNextEntry() {
            return controls;
        }

        @Override
        Entry getPreviousEntry() {
            return start;
        }
    }

    class ControlEntry extends Entry {

        public ControlEntry() {
            super("start_Menu/controls_big.png", "start_Menu/controls_big_inverted.png");
        }

        @Override
        void onClick() {
            // TODO
        }

        @Override
        Entry getNextEntry() {
            return start;
        }

        @Override
        Entry getPreviousEntry() {
            return ranking;
        }
    }

    abstract class Entry extends Actor {

        GreenfootImage normal, inverted;
        boolean normalFlag = true;

        public Entry(String normalImage, String invertedImage) {
            normal = new GreenfootImage(normalImage);
            inverted = new GreenfootImage(invertedImage);
            setImage(normal);
        }

        @Override
        public void act() {
            if (Greenfoot.mouseClicked(this)) {
                onClick();
            }
        }

        public boolean isSelected() {
            return selectedEntry == this;
        }

        public void select() {
            if (!isSelected()) {
                if (selectedEntry != null) {
                    selectedEntry.deselect();
                }
                selectedEntry = this;
                setImage(inverted);
            }
        }

        private void deselect() {
            setImage(normal);
        }

        abstract void onClick();

        void selectNext() {
            getNextEntry().select();
        }

        void selectPrevious() {
            getPreviousEntry().select();
        }

        abstract Entry getNextEntry();

        abstract Entry getPreviousEntry();
    }
}
