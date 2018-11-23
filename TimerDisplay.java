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

    private TimerDisplay() {
        // Am Anfang steht des Timer auf 2 Minuten
        timeLeft.set(Calendar.MINUTE, 2);
        timeLeft.set(Calendar.SECOND, 0);
        refreshImage();
    }

    @Override
    public void act() {
        // Wenn der Timer das erste Mal "actet" fange an zu zählen
        if (timer == null) {
            timer = new Timer("timer");
            timer.scheduleAtFixedRate(new TimerTask() {

                @Override
                public void run() {
                    // Ziehe jede Sekunde eine Sekunde vom Timer ab
                    timeLeft.add(Calendar.SECOND, -1);
                    refreshImage();
                }
            }, 1000, 1000);
        }
    }

    private void refreshImage() {
        Color c = new Color(1f, 0f, 0f, 0f);
        int mins = timeLeft.get(Calendar.MINUTE);
        int secs = timeLeft.get(Calendar.SECOND);
        String strMinutes = String.valueOf(String.format("%02d", mins));
        String strSeconds = String.valueOf(String.format("%02d", secs));
        setImage(new GreenfootImage(strMinutes + ":" + strSeconds, 30, Color.BLACK, c));
    }
}
