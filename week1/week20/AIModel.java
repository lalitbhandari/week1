package week1.week20;

/**
 * AIModel - Abstract parent class representing an AI model with pricing and technical specs.
 * @author SubscriptionSystem
 * @version 1.0
 */
public abstract class AIModel {

    /** Name of the AI model */
    private String modelName;
    /** Price in Nepali Rupees per 1 Lakh tokens */
    private double price;
    /** Number of parameters in billions */
    private int parameterCount;
    /** Context window size (e.g., "64K") */
    private String contextWindow;

    /**
     * Constructor for AIModel.
     */
    public AIModel(String modelName, double price, int parameterCount, String contextWindow) {
        this.modelName = modelName;
        this.price = price;
        this.parameterCount = parameterCount;
        this.contextWindow = contextWindow;
    }

    // Getters
    public String getModelName() {
        return modelName;
    }

    public double getPrice() {
        return price;
    }

    public int getParameterCount() {
        return parameterCount;
    }

    public String getContextWindow() {
        return contextWindow;
    }

    /**
     * Abstract method - must be implemented by subclasses (PersonalPlan & ProPlan)
     * This handles entering a prompt differently for each plan type.
     */
    public abstract String enterPrompt(String promptName, int tokens);

    /**
     * Displays model details as a formatted String.
     * Subclasses can override this to add more information.
     */
    public String display() {
        return "Model Name: " + modelName + "\n" +
               "Price (per 1L tokens): NRS " + price + "\n" +
               "Parameter Count: " + parameterCount + " Billion\n" +
               "Context Window: " + contextWindow;
    }
}