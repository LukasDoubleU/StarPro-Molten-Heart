import static java.lang.String.format;

import java.awt.Color;
import java.util.List;

import greenfoot.Actor;
import greenfoot.GreenfootImage;

/**
 * <p>
 * Das Level für Gewinner
 * </p>
 *
 * @author Lukas Wulff
 */
public class WinningScreenLevel extends Menu {

    public WinningScreenLevel() {
        super("WinScreen/win_screen.png");

        addBackToMainMenuButton();

        Score score = ScoringHandler.saveScore();
        List<Score> scores = ScoringHandler.mostRecentLoadedScores;
        if (score != null && scores != null) {
            addObject(new Text(format("Score: %s", score.getValue())), 640, 400);
            int rank = scores.indexOf(score) + 1;
            String s;
            if (rank == 1) {
                SoundUtil.playSound("high_score.wav");
                s = "Rang: %s\nNeuer Highscore!";
            } else {
                s = "Rang: %s";
            }
            addObject(new Text(format(s, rank)), 640, 500);
        }

        resetTimer();
    }

    private class Text extends Actor {

        private Text(String s) {
            setImage(new GreenfootImage(s, 36, Color.WHITE, new Color(0, 0, 0, 1), Color.BLACK));
        }
    }
}
