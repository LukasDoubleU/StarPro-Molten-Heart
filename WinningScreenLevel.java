/**
 * <p>
 * Das Level für Gewinner
 * </p>
 *
 * TODO: Hier brauchen wir noch einen netten Hintergrund
 */
public class WinningScreenLevel extends Menu {

    public WinningScreenLevel() {
	super("WinScreen.png");

	ScoringHandler.saveScore();

	addObject(new Button("Restart") {

	    @Override
	    public void onClick() {
		Level.restartGame();
	    }
	}, 640, 600);
    }
}
