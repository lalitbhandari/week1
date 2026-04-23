package week1.week20.CourseWorkFinal;


/**
 * Write a description of class l here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */

/**
 * AIModel - Abstract parent class representing an AI model with pricing and technical specs.
 *
 * This is the base class of the subscription system hierarchy.
 * It stores common attributes shared by all plan types:
 *   - modelName     : Name of the AI model (e.g. "GPT-5", "Claude 3")
 *   - price         : Cost per 1 Lakh (100,000) tokens in NRS
 *   - parameterCount: Model size in billions of parameters
 *   - contextWindow : Maximum token context the model can handle (e.g. "128K")
 *
 * Subclasses (PersonalPlan, ProPlan) MUST implement:
 *   - enterPrompt() : Behaviour differs — Personal has limits, Pro is unlimited.
 *
 * @author SubscriptionSystem
 * @version 1.0
 */
public abstract class AIModel {

    // ─── Fields ────────────────────────────────────────────────────────────
    private String modelName;      // Name of the AI model
    private double price;          // Price per 1 Lakh tokens (NRS)
    private int    parameterCount; // Model parameter count in billions
    private String contextWindow;  // Context window size (e.g. "64K", "128K")

    // ─── Constructor ───────────────────────────────────────────────────────

    /**
     * Constructs an AIModel with the given specifications.
     *
     * @param modelName      Name of the AI model
     * @param price          Price per 1 Lakh tokens in NRS
     * @param parameterCount Number of parameters in billions
     * @param contextWindow  Context window size as a string (e.g. "64K")
     */
    public AIModel(String modelName, double price, int parameterCount, String contextWindow) {
        this.modelName      = modelName;
        this.price          = price;
        this.parameterCount = parameterCount;
        this.contextWindow  = contextWindow;
    }

    // ─── Getters ───────────────────────────────────────────────────────────

    /** @return The name of the AI model */
    public String getModelName() {
        return modelName;
    }

    /** @return Price per 1 Lakh tokens in NRS */
    public double getPrice() {
        return price;
    }

    /** @return Parameter count in billions */
    public int getParameterCount() {
        return parameterCount;
    }

    /** @return Context window size (e.g. "64K") */
    public String getContextWindow() {
        return contextWindow;
    }

    // ─── Abstract Methods ──────────────────────────────────────────────────

    /**
     * Abstract method — must be implemented by subclasses.
     *
     * PersonalPlan: decrements remaining prompts; blocks if limit reached.
     * ProPlan     : always succeeds; unlimited prompts for team use.
     *
     * @param promptName Name/label for this prompt
     * @param tokens     Expected output length in tokens
     * @return           Formatted result string for display
     */
    public abstract String enterPrompt(String promptName, int tokens);

    // ─── Concrete Methods ──────────────────────────────────────────────────

    /**
     * Builds a formatted display string for this model's basic specs.
     * Subclasses call super.display() and append their own extra fields.
     *
     * @return Formatted multi-line string of model details
     */
    public String display() {
        return "Model Name        : " + modelName       + "\n" +
               "Price (per 1L tok): NRS " + price       + "\n" +
               "Parameters        : " + parameterCount  + " Billion\n" +
               "Context Window    : " + contextWindow;
    }
}
