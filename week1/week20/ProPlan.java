package week1.week20;

import java.util.ArrayList;

/**
 * ProPlan - Concrete class for team subscription with unlimited prompts and team management.
 */
public class ProPlan extends AIModel {

    private int teamSlots;
    private int remainingSlots;
    private ArrayList<String> teamMembers;

    public ProPlan(String modelName, double price, int parameterCount, 
                  String contextWindow, int teamSlots) {
        super(modelName, price, parameterCount, contextWindow);
        this.teamSlots = teamSlots;
        this.remainingSlots = teamSlots;
        this.teamMembers = new ArrayList<>();
    }

    public int getTeamSlots() {
        return teamSlots;
    }

    public int getRemainingSlots() {
        return remainingSlots;
    }

    public ArrayList<String> getTeamMembers() {
        return teamMembers;
    }

    /**
     * Add a team member
     */
    public String addTeamMember(String memberName) {
        if (memberName == null || memberName.trim().isEmpty()) {
            return "❌ Member name cannot be empty.";
        }
        if (remainingSlots <= 0) {
            return "❌ No remaining team slots available in " + getModelName() + " Pro Plan.";
        }
        teamMembers.add(memberName.trim());
        remainingSlots--;
        return "✅ Team member '" + memberName + "' added successfully.\n" +
               "Remaining slots: " + remainingSlots + " / " + teamSlots;
    }

    /**
     * Remove a team member
     */
    public String removeTeamMember(String memberName) {
        if (teamMembers.remove(memberName.trim())) {
            remainingSlots++;
            return "✅ Team member '" + memberName + "' removed successfully.\n" +
                   "Remaining slots: " + remainingSlots + " / " + teamSlots;
        } else {
            return "❌ Member '" + memberName + "' not found in the team.";
        }
    }

    /**
     * Enter Prompt - Unlimited for Pro Plan
     */
    @Override
    public String enterPrompt(String promptName, int tokens) {
        return "✅ Prompt '" + promptName + "' executed successfully (" + tokens + " tokens)\n" +
               "Model: " + getModelName() + " (Pro Plan)\n" +
               "Status: Unlimited prompts available\n" +
               "Remaining Team Slots: " + remainingSlots + " / " + teamSlots;
    }

    @Override
    public String display() {
        StringBuilder sb = new StringBuilder(super.display());
        sb.append("\nTeam Slots: ").append(teamSlots);
        sb.append("\nRemaining Slots: ").append(remainingSlots);
        sb.append("\nTeam Members: ");
        if (teamMembers.isEmpty()) {
            sb.append("None");
        } else {
            sb.append(String.join(", ", teamMembers));
        }
        return sb.toString();
    }
}