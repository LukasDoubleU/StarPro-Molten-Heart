import java.util.List;

import greenfoot.Actor;
import greenfoot.Greenfoot;
import greenfoot.MouseInfo;

public class Menu extends Level {

    protected Entry selectedEntry;

    public Menu(String image) {
        super(image);
    }

    @Override
    public void started() {
        // Wird beim Start des Programms einmalig ausgeführt
        // Spiele die Hintergrundmusik in Dauerschleife
//        GreenfootSound backgroundMusic = new GreenfootSound("sounds/background.wav");
//        backgroundMusic.playLoop();
    }

    protected void resetTimer() {
        TimerDisplay timer = TimerDisplay.get();
        timer.stop();
        timer.reset();
    }

    protected void addBackToMainMenuButton() {
        addObject(new Actor() {

            {
                setImage("return_button.png");
            }

            @Override
            public void act() {
                MouseInfo mouseInfo = Greenfoot.getMouseInfo();
                if (mouseInfo != null) {
                    int mouseX = mouseInfo.getX();
                    int mouseY = mouseInfo.getY();
                    List<?> objectsAt = getWorld().getObjectsAt(mouseX, mouseY, this.getClass());
                    if (objectsAt.contains(this)) {
                        setImage("return_button_inverted.png");
                    } else {
                        setImage("return_button.png");
                    }
                }
                if (Greenfoot.mouseClicked(this)) {
                    Greenfoot.setWorld(new MainMenu());
                }
            }
        }, 310, 670);
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
            for (Entry e : getEntries()) {
                if (key.equals("" + e.getIndex())) {
                    e.onClick();
                }
            }
        }
    }

    boolean anyEquals(Object obj, Object... objects) {
        for (Object other : objects) {
            if (obj.equals(other)) {
                return true;
            }
        }
        return false;
    }

    @SuppressWarnings("unchecked")
    public List<Entry> getEntries() {
        return getObjects(Entry.class);
    }

    public Entry getEntryWithIndex(int i) {
        Entry e = null;
        for (Entry entry : getEntries()) {
            if (entry.getIndex() == i) {
                e = entry;
                break;
            }
        }
        return e;
    }

    @Override
    public final void finish() {
        // Gibt's hier nicht
    }

    public static class Controls extends Menu {

        public Controls() {
            super("controls_background.png");
            addBackToMainMenuButton();
        }
    }

    public static class Scoring extends Menu {

        public Scoring() {
            super("highscore_background.png");
            addBackToMainMenuButton();
            addObject(new ScoringHandler.TopTenTable(), 640, 400);
        }
    }

}
