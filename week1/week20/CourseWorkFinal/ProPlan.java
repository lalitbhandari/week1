package week1.week20.CourseWorkFinal;


/**
 * Write a description of class l here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */

import java.util.ArrayList;

/**
 * ProPlan - Concrete subclass of AIModel for team/professional subscriptions.
 *
 * Features:
 *   - Unlimited prompts : No monthly limit — any team member can prompt freely.
 *   - Team management   : Add/remove named team members up to the slot limit.
 *   - Team slots        : Maximum number of members allowed (set at creation).
 *
 * Inherits from AIModel: modelName, price, parameterCount, contextWindow.
 *
 * Key behaviour:
 *   enterPrompt()      → always succeeds; shows remaining team slots.
 *   addTeamMember()    → adds member if slots are available.
 *   removeTeamMember() → removes member and frees the slot.
 */
public class ProPlan extends AIModel {

    // ─── Fields ────────────────────────────────────────────────────────────
    private int               teamSlots;     // Maximum number of team members allowed
    private int               remainingSlots;// Slots still available for new members
    private ArrayList<String> teamMembers;   // Names of current team members

    // ─── Constructor ───────────────────────────────────────────────────────

    /**
     * Creates a ProPlan with given model specs and team size capacity.
     *
     * @param modelName      Name of the AI model
     * @param price          Price per 1 Lakh tokens in NRS
     * @param parameterCount Parameters in billions
     * @param contextWindow  Context window size (e.g. "128K")
     * @param teamSlots      Maximum number of team members allowed
     */
    public ProPlan(String modelName, double price, int parameterCount,
                   String contextWindow, int teamSlots) {
        super(modelName, price, parameterCount, contextWindow); // Call parent constructor
        this.teamSlots      = teamSlots;
        this.remainingSlots = teamSlots; // All slots open at start
        this.teamMembers    = new ArrayList<>();
    }

    // ─── Getters ───────────────────────────────────────────────────────────

    /** @return Total number of team slots */
    public int getTeamSlots() {
        return teamSlots;
    }

    /** @return Number of slots still available */
    public int getRemainingSlots() {
        return remainingSlots;
    }

    /** @return List of current team member names */
    public ArrayList<String> getTeamMembers() {
        return teamMembers;
    }

    // ─── Business Logic ────────────────────────────────────────────────────

    /**
     * Adds a new member to the team if slots are available.
     * Validates that the name is not blank and a slot is free.
     *
     * @param memberName Full name of the team member to add
     * @return           Success or error message
     */
    public String addTeamMember(String memberName) {
        // Validate: name must not be empty
        if (memberName == null || memberName.trim().isEmpty()) {
            return "❌ Member name cannot be empty.";
        }
        // Guard: no slots left
        if (remainingSlots <= 0) {
            return "⚠️  No remaining team slots in " + getModelName() + " Pro Plan.\n" +
                   "All " + teamSlots + " slots are currently occupied.";
        }
        teamMembers.add(memberName.trim()); // Add to team roster
        remainingSlots--;                   // Consume one slot
        return "✅ Team member '" + memberName + "' added successfully.\n" +
               "Remaining slots : " + remainingSlots + " / " + teamSlots;
    }

    /**
     * Removes a member from the team and frees their slot.
     * Returns an error if the given name is not found in the roster.
     *
     * @param memberName Name of the team member to remove
     * @return           Success or error message
     */
    public String removeTeamMember(String memberName) {
        // ArrayList.remove() returns true if element was found and removed
        if (teamMembers.remove(memberName.trim())) {
            remainingSlots++; // Slot is freed on removal
            return "✅ Team member '" + memberName + "' removed successfully.\n" +
                   "Remaining slots : " + remainingSlots + " / " + teamSlots;
        } else {
            return "❌ Member '" + memberName + "' not found in the team roster.";
        }
    }

    /**
     * Executes a prompt — always succeeds for Pro Plans (unlimited).
     * Displays current team slot status alongside the result.
     *
     * @param promptName Label/name for the prompt
     * @param tokens     Expected output size in tokens
     * @return           Result message with plan and team details
     */
    @Override
    public String enterPrompt(String promptName, int tokens) {
        return "✅ Prompt '" + promptName + "' executed successfully (" + tokens + " tokens)\n" +
               "Model           : " + getModelName() + " (Pro Plan)\n" +
               "Prompt Limit    : Unlimited\n" +
               "Remaining Slots : " + remainingSlots + " / " + teamSlots;
    }

    /**
     * Returns full display info for this Pro Plan.
     * Calls super.display() for base fields, then appends Pro-specific fields.
     *
     * @return Formatted multi-line string of all plan details
     */
    @Override
    public String display() {
        StringBuilder sb = new StringBuilder(super.display());
        sb.append("\nPlan Type         : Pro (Team)");
        sb.append("\nTeam Slots        : ").append(teamSlots);
        sb.append("\nRemaining Slots   : ").append(remainingSlots);
        sb.append("\nTeam Members      : ");
        if (teamMembers.isEmpty()) {
            sb.append("None"); // No members yet
        } else {
            sb.append(String.join(", ", teamMembers)); // Comma-separated list
        }
        return sb.toString();
    }
}
