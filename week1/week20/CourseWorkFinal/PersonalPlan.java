package week1.week20.CourseWorkFinal;


/**
 * Write a description of class k here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */

/**
 * PersonalPlan - Concrete subclass of AIModel for individual user subscriptions.
 *
 * Features:
 *   - Monthly prompt limit  : Users get a fixed number of prompts per month.
 *   - Remaining prompts     : Tracked and decremented each time enterPrompt() is called.
 *   - Purchase extra prompts: Users can buy additional prompts via purchasePrompts().
 *
 * Inherits from AIModel: modelName, price, parameterCount, contextWindow.
 *
 * Key behaviour:
 *   enterPrompt() → checks remaining prompts → blocks if 0 → decrements on success.
 *   purchasePrompts() → adds to remainingPrompts directly.
 */
public class PersonalPlan extends AIModel {

    // ─── Fields ────────────────────────────────────────────────────────────
    private int monthlyPrompts;   // Total prompts allocated per month
    private int remainingPrompts; // Prompts still available to use

    // ─── Constructor ───────────────────────────────────────────────────────

    /**
     * Creates a PersonalPlan with given model specs and monthly prompt allocation.
     *
     * @param modelName      Name of the AI model
     * @param price          Price per 1 Lakh tokens in NRS
     * @param parameterCount Parameters in billions
     * @param contextWindow  Context window size (e.g. "64K")
     * @param monthlyPrompts Number of prompts given each month
     */
    public PersonalPlan(String modelName, double price, int parameterCount,
                        String contextWindow, int monthlyPrompts) {
        super(modelName, price, parameterCount, contextWindow); // Call parent constructor
        this.monthlyPrompts   = monthlyPrompts;
        this.remainingPrompts = monthlyPrompts; // Initially full quota available
    }

    // ─── Getters ───────────────────────────────────────────────────────────

    /** @return Total monthly prompt allocation */
    public int getMonthlyPrompts() {
        return monthlyPrompts;
    }

    /** @return Prompts still available this month */
    public int getRemainingPrompts() {
        return remainingPrompts;
    }

    // ─── Business Logic ────────────────────────────────────────────────────

    /**
     * Purchases additional prompts and adds them to the remaining balance.
     * Does NOT increase the monthlyPrompts limit — only boosts remainingPrompts.
     *
     * @param additional Number of extra prompts to buy (must be > 0)
     * @return           Confirmation message or error if input is invalid
     */
    public String purchasePrompts(int additional) {
        if (additional <= 0) {
            return "❌ Invalid number of prompts to purchase. Must be greater than 0.";
        }
        this.remainingPrompts += additional; // Add to current balance
        return "✅ Successfully purchased " + additional + " extra prompts.\n" +
               "New remaining prompts: " + remainingPrompts;
    }

    /**
     * Executes a prompt if remaining quota allows.
     * Decrements remainingPrompts by 1 on each successful call.
     * Blocks execution and returns an error if no prompts remain.
     *
     * @param promptName Label/name for the prompt being entered
     * @param tokens     Expected output size in tokens
     * @return           Result message showing status and updated counts
     */
    @Override
    public String enterPrompt(String promptName, int tokens) {
        // Guard: block if quota is exhausted
        if (remainingPrompts <= 0) {
            return "⚠️  No remaining prompts in " + getModelName() + " Personal Plan.\n" +
                   "Please purchase more prompts to continue.";
        }
        remainingPrompts--; // Deduct one prompt from quota
        return "✅ Prompt '" + promptName + "' executed successfully (" + tokens + " tokens)\n" +
               "Model           : " + getModelName() + " (Personal Plan)\n" +
               "Remaining Prompts: " + remainingPrompts + " / " + monthlyPrompts;
    }

    /**
     * Returns full display info for this Personal Plan.
     * Calls super.display() for base fields, then appends Personal-specific fields.
     *
     * @return Formatted multi-line string of all plan details
     */
    @Override
    public String display() {
        return super.display()                              + "\n" +
               "Plan Type         : Personal"              + "\n" +
               "Monthly Prompts   : " + monthlyPrompts     + "\n" +
               "Remaining Prompts : " + remainingPrompts;
    }
}
