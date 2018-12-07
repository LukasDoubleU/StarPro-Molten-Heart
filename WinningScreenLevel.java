public class WinningScreenLevel extends Level {

    public WinningScreenLevel() {
        super("WinScreen.png");
        Scoring.saveScore();

        addObject(new Scoring.Table(), 640, 400);

        addObject(new Button("Restart") {

            @Override
            public void onClick() {
                Level.restartGame();
            }
        }, 640, 600);
    }

    @Override
    public void finish() {
        // Tue nichts
    }

}
