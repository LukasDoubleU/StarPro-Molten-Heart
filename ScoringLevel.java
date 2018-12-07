import greenfoot.Greenfoot;

public class ScoringLevel extends Level {

    public ScoringLevel(final Level previousLevel) {
        super("scoring.png"); // TODO Hintergrundbilder

        addObject(new Scoring.Table(), 640, 200);

        addObject(new Button("Zurück") {

            @Override
            public void onClick() {
                Greenfoot.setWorld(previousLevel);
            }

        }, 640, 550);
    }

    @Override
    public void finish() {
    }

}
