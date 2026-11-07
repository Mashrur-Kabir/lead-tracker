import java.util.Optional;
import java.util.Scanner;

/**
 * Console entry point for the Simple Lead Tracker.
 */
public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        LeadTracker tracker = new LeadTracker();
        boolean running = true;

        while (running) {
            printMenu();
            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1":
                    addLead(scanner, tracker);
                    break;
                case "2":
                    viewLeads(tracker);
                    break;
                case "3":
                    findLeadByEmail(scanner, tracker);
                    break;
                case "4":
                    running = false;
                    System.out.println("Goodbye!");
                    break;
                default:
                    System.out.println("Invalid option. Please choose 1, 2, 3, or 4.");
            }
        }

        scanner.close();
    }

    private static void printMenu() {
        System.out.println("\n=== Simple Lead Tracker ===");
        System.out.println("1. Add a lead");
        System.out.println("2. View all leads");
        System.out.println("3. Find a lead by email");
        System.out.println("4. Exit");
        System.out.print("Choose an option: ");
    }

    private static void addLead(Scanner scanner, LeadTracker tracker) {
        System.out.print("Name: ");
        String name = scanner.nextLine();

        System.out.print("Email: ");
        String email = scanner.nextLine();

        System.out.print("Score (0-100): ");
        String scoreInput = scanner.nextLine();

        int score;
        try {
            score = Integer.parseInt(scoreInput.trim());
        } catch (NumberFormatException e) {
            System.out.println("Invalid input: score must be a whole number.");
            return;
        }

        try {
            tracker.addLead(name, email, score);
            System.out.println("Lead added successfully.");
        } catch (IllegalArgumentException e) {
            System.out.println("Could not add lead: " + e.getMessage());
        }
    }

    private static void findLeadByEmail(Scanner scanner, LeadTracker tracker) {
        System.out.print("Enter email to search for: ");
        String email = scanner.nextLine();

        Optional<Lead> result = tracker.findByEmail(email);
        if (result.isPresent()) {
            Lead lead = result.get();
            String priority = PriorityCalculator.calculate(lead.score());
            System.out.printf("Found: Name: %s | Email: %s | Score: %d | Priority: %s%n",
                    lead.name(), lead.email(), lead.score(), priority);
        } else {
            System.out.println("No lead found with that email. Did you add '@gmail.com' at the end? ");
        }
    }

    private static void viewLeads(LeadTracker tracker) {
        if (tracker.getLeads().isEmpty()) {
            System.out.println("No leads have been added yet.");
            return;
        }

        System.out.println("\n--- All Leads ---");
        for (Lead lead : tracker.getLeads()) {
            String priority = PriorityCalculator.calculate(lead.score());
            System.out.printf("Name: %s | Email: %s | Score: %d | Priority: %s%n",
                    lead.name(), lead.email(), lead.score(), priority);
        }
    }
}