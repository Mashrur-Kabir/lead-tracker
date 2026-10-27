/**
 * Calculates a lead's priority from its score.
 *
 * Kept as a separate class (rather than a field on Lead) so priority
 * is always computed fresh from the current score, never stored as an
 * independently editable value.
 */
public class PriorityCalculator {

    public static String calculate(int score) {
        if (score >= 80) {
            return "HOT";
        } else if (score >= 50) {
            return "WARM";
        } else {
            return "COLD";
        }
    }
}
