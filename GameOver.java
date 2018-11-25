public class GameOver extends Level {

    public GameOver() {
        super("gameover.png");
        addObject(new Button("Restart Game") {

            @Override
            public void onClick() {
                Level.restartGame();
            }
        }, 640, 600);
    }

    @Override
    public void finish() {
        // Der Finish macht bei Game Over nichts.
    }
}
