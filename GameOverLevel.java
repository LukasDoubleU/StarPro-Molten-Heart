import java.util.Timer;
import java.util.TimerTask;

import greenfoot.Greenfoot;

public class GameOverLevel extends Menu {

    static Timer timer;
    boolean running = false;

    public GameOverLevel() {
	super("/game_over/game_over_part_1.png");

    }

    @Override
    public void act() {
	timerAction();
	String key = Greenfoot.getKey();
	if (key != null && key.equals("enter")) {
	    Greenfoot.setWorld(new MainMenu());
	    timer = null;
	}
    }

    public void timerAction() {
	if (timer == null) {
	    timer = new Timer("timer");
	    running = true;
	    timer.scheduleAtFixedRate(new TimerTask() {

		@Override
		public void run() {
		    if (running) {
			String[] str = getBackground().toString().split(" ");
			if (str[3].equals("/game_over/game_over_part_2.png")) {
			    setBackground("/game_over/game_over_part_1.png");
			} else {
			    setBackground("/game_over/game_over_part_2.png");
			}
		    }
		}
	    }, 500, 500);
	}
    }

    public void start() {
	running = true;
    }

    public void stop() {
	running = false;
    }
}
