
import java.awt.Color;

import greenfoot.Actor;
import greenfoot.GreenfootImage;

public class GameWon extends Level {

    class ScoringTable extends Actor {
        ScoringTable() {
            setImage(new GreenfootImage(Scoring.generateTable(), 20, Color.WHITE, Color.BLACK));
        }
    }

    public GameWon() {
        super("WinScreen.png");
        addObject(new Button("Restart") {

            @Override
            public void onClick() {
                Level.restartGame();
            }
        }, 640, 600);
        Scoring.saveScore();
        addObject(new ScoringTable(), 640, 450);
    }

    @Override
    public void finish() {
        // Tue nichts
    }

}
