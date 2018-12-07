/**
 * <p>
 * Das Level für Verlierer.
 * </p>
 */
public class GameOverLevel extends Level {

    public GameOverLevel() {
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
