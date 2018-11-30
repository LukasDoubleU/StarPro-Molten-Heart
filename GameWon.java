public class GameWon extends Level {

    public GameWon() {
        super("WinScreen.png");
        addObject(new Button("Restart") {

            @Override
            public void onClick() {
                Level.restartGame();
            }
        }, 640, 600);
        Scoring.saveScore();
        addObject(new Scoring.Table(), 640, 450);
    }

    @Override
    public void finish() {
        // Tue nichts
    }

}
