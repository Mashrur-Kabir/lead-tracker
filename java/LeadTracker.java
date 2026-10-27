import java.util.ArrayList;
import java.util.List;

/**
 * Holds the in-memory collection of leads and the validation logic for
 * adding new ones.
 */
public class LeadTracker {

    private final List<Lead> leads = new ArrayList<>();

    /**
     * Attempts to add a new lead. Throws IllegalArgumentException with a
     * clear message if the name, email, or score is invalid.
     */
    public void addLead(String name, String email, int score) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be empty.");
        }
        if (email == null || email.trim().isEmpty()) {
            throw new IllegalArgumentException("Email cannot be empty.");
        }
        if (score < 0 || score > 100) {
            throw new IllegalArgumentException("Score must be between 0 and 100.");
        }
        leads.add(new Lead(name.trim(), email.trim(), score));
    }

    public List<Lead> getLeads() {
        return leads;
    }
}
