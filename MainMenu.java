import greenfoot.Greenfoot;

public class MainMenu extends Menu {

    Entry start = new StartEntry();
    Entry ranking = new RankingEntry();
    Entry controls = new ControlEntry();

    public MainMenu() {
        super("start_Menu/start_screen.png");

        addObject(start, 640, 300);
        addObject(ranking, 640, 400);
        addObject(controls, 640, 500);

        start.select();
    }

    class StartEntry extends Entry {

        public StartEntry() {
            super(MainMenu.this, "start_Menu/play_big.png", "start_Menu/play_big_inverted.png");
        }

        @Override
        public void onClick() {
            restartGame();
        }

        @Override
        public int getIndex() {
            return 1;
        }
    }

    class RankingEntry extends Entry {

        public RankingEntry() {
            super(MainMenu.this, "start_Menu/highscore_big.png", "start_Menu/highscore_big_inverted.png");
        }

        @Override
        public void onClick() {
            Greenfoot.setWorld(new Menu.Scoring());
        }

        @Override
        public int getIndex() {
            return 2;
        }

    }

    class ControlEntry extends Entry {

        public ControlEntry() {
            super(MainMenu.this, "start_Menu/controls_big.png", "start_Menu/controls_big_inverted.png");
        }

        @Override
        public void onClick() {
            Greenfoot.setWorld(new Menu.Controls());
        }

        @Override
        public int getIndex() {
            return 3;
        }
    }
}
