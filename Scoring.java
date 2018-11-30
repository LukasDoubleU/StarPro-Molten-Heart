import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.swing.JOptionPane;

import greenfoot.Greenfoot;

/**
 * <p>
 * Diese Klasse handhabt das Scoring.
 * </p>
 */
public class Scoring {

    /**
     * <p>
     * Repräsentiert einen einzelnen Score
     * </p>
     */
    public static class Score implements Comparable<Score> {

        String name;
        int value;

        public Score(String name, int value) {
            super();
            this.name = name;
            this.value = value;
        }

        public String getName() {
            return name;
        }

        public int getValue() {
            return value;
        }

        @Override
        public int compareTo(Score o) {
            return Integer.valueOf(o.getValue()).compareTo(Integer.valueOf(o.getValue()));
        }

        /**
         * Schreibt den Score in die Datei
         */
        public void write() {
            try {
                Files.write(getPath(), this.toString().getBytes(), StandardOpenOption.APPEND);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @Override
        public String toString() {
            return name + SCORE_SEP_CHAR + value;
        }

    }

    static final String FILE_PATH = System.getProperty("user.home") + File.separator + "MoltenHeartScoring.txt";
    static final char SCORE_SEP_CHAR = ';';

    static File file;

    /**
     * Speichert die Datei
     */
    public static void saveScore() {
        String name = askForPlayerName();
        if (name == null) {
            // null = Abbruch
            return;
        }
        int score = calcScoreForCurrentGame();
        new Score(name, score).write();
    }

    private static String askForPlayerName() {
        String ask = Greenfoot.ask("What's your name boi?");
        if (ask == null || ask.isEmpty()) {
            return null;
        }
        if (ask.matches("[a-zA-Z0-9]")) {
            JOptionPane.showMessageDialog(null, "Die Eingabe enthält ungültige Einzeichen!");
            return askForPlayerName();
        }
        if (ask.length() > 12) {
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
        int millis = secs * 1000 + mins * 60 * 1000;
        return millis;
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

    public static Collection<Score> read() {
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

        return scores;
    }

    public static String generateTable() {
        Collection<Score> read = read();
        StringBuilder sb = new StringBuilder();
        for (Score score : read) {
            sb.append(score.getName() + "\t" + score.getValue() + "\n");
        }
        return sb.toString();
    }

}
