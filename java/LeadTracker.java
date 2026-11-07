import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

/**
 * Holds the in-memory collection of leads and the validation logic for
 * adding new ones.
 */
public class LeadTracker {

    // Requires: something@something.something (at least one letter-ish
    // block, an @, a domain, a dot, and a 2+ letter extension).
    // Rejects plain text like "123" or "john"; accepts "john@gmail.com".
    private static final Pattern EMAIL_PATTERN =
            Pattern.compile("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$");

    // Requires at least one letter somewhere in the name, so purely
    // numeric input like "12" is rejected, while still allowing names
    // with apostrophes, hyphens, or spaces (e.g. "Anne-Marie O'Brien").
    private static final Pattern NAME_PATTERN =
            Pattern.compile(".*[A-Za-z].*");

    private final List<Lead> leads = new ArrayList<>();

    /**
     * Attempts to add a new lead. Throws IllegalArgumentException with a
     * clear message if the name, email, or score is invalid.
     */
    public void addLead(String name, String email, int score) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be empty.");
        }
        if (!NAME_PATTERN.matcher(name.trim()).matches()) {
            throw new IllegalArgumentException("Name must contain at least one letter.");
        }
        if (email == null || email.trim().isEmpty()) {
            throw new IllegalArgumentException("Email cannot be empty.");
        }
        if (!EMAIL_PATTERN.matcher(email.trim()).matches()) {
            throw new IllegalArgumentException("Email must be a valid address, e.g. john@example.com.");
        }
        if (score < 0 || score > 100) {
            throw new IllegalArgumentException("Score must be between 0 and 100.");
        }
        leads.add(new Lead(name.trim(), email.trim(), score));
    }

    public List<Lead> getLeads() {
        return leads;
    }

    /**
     * Finds a lead by exact email match, ignoring case and surrounding
     * whitespace. Returns an empty Optional if no lead has that email.
     */
    public Optional<Lead> findByEmail(String email) {
        if (email == null) {
            return Optional.empty();
        }
        String target = email.trim().toLowerCase();
        return leads.stream()
                .filter(lead -> lead.email().toLowerCase().equals(target))
                .findFirst();
    }
}