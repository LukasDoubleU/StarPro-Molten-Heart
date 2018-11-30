public class StartingGame extends Level {

    public StartingGame() {
        super("Start_Game.png");
        addObject(new Button("Start Game") {

            @Override
            public void onClick() {
                restartGame();
            }
        }, 640, 600);
    }

    @Override
    public void finish() {
    }
}
