/**
 * <p>
 * Das Level für Gewinner
 * </p>
 */
public class WinningScreenLevel extends Level {

    public WinningScreenLevel() {
        super("WinScreen.png");

        Scoring.saveScore();

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
