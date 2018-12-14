/**
 * <p>
 * Repräsentiert einen einzelnen Score
 * </p>
 */
public class Score implements Comparable<Score> {

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

    public String getValueFilledToMax() {
        return fill(getValue() + "", 6, true);
    }

    @Override
    public int compareTo(Score o) {
        return Integer.valueOf(o.getValue()).compareTo(Integer.valueOf(getValue()));
    }

    @Override
    public String toString() {
        return getName() + ScoringHandler.SCORE_SEP_CHAR + getValue();
    }

    public String getNameFilledToMax() {
        return fill(getName(), ScoringHandler.PLAYER_NAME_MAX_LENGTH, false);
    }

    private static String fill(String s, int length, boolean prepend) {
        StringBuilder sb = new StringBuilder(s);
        // Fülle den Namen auf die maximale Länge auf, mit Leerzeichen
        while (sb.length() < length) {
            if (prepend) {
                sb.insert(0, " ");
            } else {
                sb.append(" ");
            }
        }
        return sb.toString();
    }
}