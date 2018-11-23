import java.awt.Color;
import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

import greenfoot.Actor;
import greenfoot.GreenfootImage;

public class TimerDisplay extends Actor {

    public static TimerDisplay get() {
        return instance;
    }

    static Calendar timeLeft = Calendar.getInstance();
    static TimerDisplay instance = new TimerDisplay();
    static Timer timer;

    boolean running = false;

    private TimerDisplay() {
        reset();
    }

    @Override
    public void act() {
        // Wenn der Timer das erste Mal "actet" fange an zu zählen
        if (timer == null) {
            timer = new Timer("timer");
            timer.scheduleAtFixedRate(new TimerTask() {

                @Override
                public void run() {
                    secondPassed();
                }
            }, 1000, 1000);
        }
    }

    private void refreshImage(int mins, int secs) {
        Color c = new Color(1f, 0f, 0f, 0f);
        String minString = String.format("%02d", mins);
        String secString = String.format("%02d", secs);
        setImage(new GreenfootImage(minString + ":" + secString, 30, Color.WHITE, Color.BLACK));
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
        GameOver.runGameOverWorld();
        stop();
    }

    public void reset() {
        // Am Anfang steht des Timer auf 2 Minuten
        timeLeft.set(Calendar.MINUTE, 2);
        timeLeft.set(Calendar.SECOND, 0);
        refreshImage(2, 0);
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
