import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.prefs.BackingStoreException;
import java.util.prefs.Preferences;

import javax.swing.JOptionPane;

import greenfoot.Actor;
import greenfoot.Greenfoot;
import greenfoot.GreenfootImage;

/**
 * <p>
 * Diese Klasse handhabt das Scoring.
 * </p>
 */
public class ScoringHandler {

    public static class TopTenTable extends Actor {

        public TopTenTable() {
            setImage(new GreenfootImage(ScoringHandler.generateTopTenTableString(), 36, Color.WHITE,
                    new Color(0, 0, 0, 1), Color.BLACK));
        }
    }

    public static final int PLAYER_NAME_MAX_LENGTH = 12;
    public static final Preferences PREFS = Preferences.userRoot().node("MoltenHeartScoring");

    /**
     * Speichert die Datei inklusive des Scores fürs aktuelle Spiel
     *
     * @return Den Score aus diesem Spiel (oder null)
     */
    public static Score saveScore() {

        // Der Name des aktuellen Spielers
        String name = askForPlayerName();
        if (name == null) {
            // null = Abbruch
            return null;
        }

        // Der Score fürs aktuelle Spiel
        int value = calcScoreForCurrentGame();
        Score score = new Score(name, value);

        // Schreibe den Score
        PREFS.putInt(score.getName(), score.getValue());

        return score;
    }

    private static String askForPlayerName() {
        String ask = Greenfoot.ask("Gib deinen Namen ein, du Held");
        // Wenn nichts eingegeben wird, speichern wir den Score halt nicht
        if (ask == null || ask.isEmpty()) {
            return null;
        }
        if (!ask.matches("[a-zA-Z0-9]+")) {
            JOptionPane.showMessageDialog(null, "Die Eingabe enthält ungültige Einzeichen!");
            return askForPlayerName();
        }
        if (ask.length() > PLAYER_NAME_MAX_LENGTH) {
            JOptionPane.showMessageDialog(null, "Die Eingabe ist zu lang! (Maximal 12 Zeichen)");
            return askForPlayerName();
        }
        try {
            if (Arrays.asList(PREFS.keys()).contains(ask)) {
                JOptionPane.showMessageDialog(null, "Der Name ist bereits vergeben!");
                return askForPlayerName();
            }
        } catch (BackingStoreException e) {
            e.printStackTrace();
        }
        return ask;
    }

    /**
     * @return Score für das aktuelle Spiel: Die verbleibende Zeit in Millisekunden
     */
    private static int calcScoreForCurrentGame() {
        TimerDisplay timer = TimerDisplay.get();
        int secs = timer.getSecondsLeft();
        int mins = timer.getMinutesLeft();
        int millis = timer.getMillisecondsLeft();
        return secs * 1000 + mins * 60 * 1000 + millis;
    }

    public static List<Score> read() {
        List<Score> scores = new ArrayList<Score>();

        try {
            String[] keys = PREFS.keys();
            for (String key : keys) {
                scores.add(new Score(key, PREFS.getInt(key, 0)));
            }

        } catch (BackingStoreException e) {
            e.printStackTrace();
        }

        // Sortiere, sodass die besten Scores ganz oben stehen
        Collections.sort(scores);

        return scores;
    }

    public static String generateTopTenTableString() {
        List<Score> read = read();
        Collections.sort(read);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 10; i++) {
            // gibt es für diesen index einen score?
            if (read.size() > i) {
                // Schreibe den Score
                Score score = read.get(i);
                sb.append(score.getNameFilledToMax() + " " + score.getValueFilledToMax() + "\n");
            }
            // Ansonsten füge eine Leerzeile ein
            else {
                sb.append(" - \n");
            }
        }
        return sb.toString();
    }

}
