import java.awt.Color;
import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

import greenfoot.Actor;
import greenfoot.World;

public class TimerDisplay extends Actor {

    public static TimerDisplay get() {
        return instance;
    }

    static Calendar timeLeft = Calendar.getInstance();
    static TimerDisplay instance = new TimerDisplay();
    static Timer timer;
    static int time = 5;
    boolean running = false;
    

    private TimerDisplay() {
        setImage("transparent.png");
        reset();
    }

    @Override
    public void act() {

        // Blende den Timer nicht ein, wenn der Spieler sich nicht in der Welt befindet
        World world = Player.get().getWorld();
        if (world == null || world != getWorld()) {
            // Wenn der Spieler nicht in der Welt oder in einer anderen Welt
            // als der aktuellen ist
            setImage("transparent.png");
            return;
        }

        // Wenn der Timer das erste Mal "actet" fängt er an zu laufen
        if (timer == null) {
            timer = new Timer("timer");
            running = true;
            timer.scheduleAtFixedRate(new TimerTask() {

                @Override
                public void run() {
                    if (running) {
                        secondPassed();
                    }
                }
            }, 1000, 1000);
        }
    }

    private void refreshImage(int mins, int secs) {

        Color c = new Color(1f, 0f, 0f, 0f);
        String minString = String.format("%02d", mins);
        String secString = String.format("%02d", secs);

        // create the text image
        String text = minString + ":" + secString;
        setImage(GreenfootImageConstructor.getConstructedImg(20, text, c));
    }

    private void secondPassed() {
        // Ziehe jede Sekunde eine Sekunde vom Timer ab
        timeLeft.add(Calendar.SECOND, -1);

        int mins = getMinutesLeft();
        int secs = getSecondsLeft();

        if (mins == 0 && secs == 0) {
            timesUp();
        }

        // Aktualisiere das Bild
        refreshImage(mins, secs);
    }

    private void timesUp() {
        GameOverLevel.runGameOverWorld();
        stop();
        reset();
    }

    public void reset() {
        // Am Anfang steht des Timer auf 5 Minuten
        timeLeft.set(Calendar.MINUTE, time);
        timeLeft.set(Calendar.SECOND, 0);
        refreshImage(time, 0);
    }

    public void start() {
        running = true;
    }

    public void stop() {
        running = false;
    }

    public int getSecondsLeft() {
        return timeLeft.get(Calendar.SECOND);
    }

    public int getMinutesLeft() {
        return timeLeft.get(Calendar.MINUTE);
    }
}
