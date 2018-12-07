import greenfoot.Greenfoot;

public class MainMenuLevel extends Level {

    public MainMenuLevel() {
        super("Start_Game.png");
        addObject(new Button("Start Game") {

            @Override
            public void onClick() {
                restartGame();
            }
        }, 640, 600);
        addObject(new Button("Rangliste") {

            @Override
            public void onClick() {
                Greenfoot.setWorld(new ScoringLevel());
            }
        }, 640, 570);
    }

    @Override
    public void finish() {
    }
}
