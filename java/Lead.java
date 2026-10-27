/**
 * Represents a single sales lead.
 *
 * Implemented as a record because a lead's name, email, and score are
 * fixed once created in this simple tracker. Priority is deliberately
 * NOT a field here — it is always derived from the score via
 * PriorityCalculator, so it can never be stored, edited, or go stale
 * independently of the score.
 */
public record Lead(String name, String email, int score) {
}
