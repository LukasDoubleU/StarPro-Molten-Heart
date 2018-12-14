import java.awt.Color;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

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

    public static List<Score> mostRecentLoadedScores = null;
    public static final int PLAYER_NAME_MAX_LENGTH = 12;
    public static final char SCORE_SEP_CHAR = ';';
    static final String FILE_PATH = System.getProperty("user.home") + File.separator + "MoltenHeartScoring.txt";
    static File file;

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

        // Die bisherigen Scores
        final List<Score> previousScores = new CopyOnWriteArrayList<Score>(read());

        // Der Score fürs aktuelle Spiel
        int points = calcScoreForCurrentGame();
        Score score = new Score(name, points);
        mostRecentLoadedScores.add(score);
        Collections.sort(mostRecentLoadedScores);
        previousScores.add(score);

        // Schreibe alle Scores in die Datei
        StringBuilder sb = new StringBuilder();
        for (Score s : previousScores) {
            sb.append(s.toString() + "\n");
        }
        try {
            Files.write(getPath(), sb.toString().getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }

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

    public static File getFile() {
        if (file == null) {
            file = new File(FILE_PATH);
            if (!file.exists()) {
                try {
                    file.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return file;
    }

    public static Path getPath() {
        return getFile().toPath();
    }

    public static List<Score> read() {
        List<Score> scores = new ArrayList<Score>();

        try {
            List<String> lines = Files.readAllLines(getPath());
            for (String line : lines) {
                String[] split = line.split(";");
                scores.add(new Score(split[0], Integer.valueOf(split[1])));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Sortiere, sodass die besten Scores ganz oben stehen
        Collections.sort(scores);

        mostRecentLoadedScores = scores;

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
