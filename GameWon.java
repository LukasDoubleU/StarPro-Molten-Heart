
import java.awt.Color;

import greenfoot.Actor;
import greenfoot.GreenfootImage;

public class GameWon extends Level {

    class ScoringTable extends Actor {
        ScoringTable() {
            GreenfootImage img = new GreenfootImage(Scoring.generateTable(), 12, Color.WHITE, Color.BLACK);
            setImage(img);
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
        addObject(new ScoringTable(), 640, 650);
    }

    @Override
    public void finish() {
        // Tue nichts
    }

}
