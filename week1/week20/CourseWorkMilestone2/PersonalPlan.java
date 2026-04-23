package week1.week20.CourseWorkMilestone2;


/**
 * PersonalPlan - Concrete class for individual user subscription with monthly prompt limit.
 */
public class PersonalPlan extends AIModel {

    private int monthlyPrompts;
    private int remainingPrompts;

    public PersonalPlan(String modelName, double price, int parameterCount, 
                       String contextWindow, int monthlyPrompts) {
        super(modelName, price, parameterCount, contextWindow);
        this.monthlyPrompts = monthlyPrompts;
        this.remainingPrompts = monthlyPrompts;
    }

    public int getMonthlyPrompts() {
        return monthlyPrompts;
    }

    public int getRemainingPrompts() {
        return remainingPrompts;
    }

    
    //Purchase additional prompts
    
    public String purchasePrompts(int additional) {
        if (additional <= 0) {
            return "❌ Invalid number of prompts to purchase.";
        }
        this.remainingPrompts += additional;
        return "✅ Successfully purchased " + additional + " prompts.\n" +
               "New remaining prompts: " + remainingPrompts;
    }

    
     //Enter a prompt (with limit check)
    @Override
    public String enterPrompt(String promptName, int tokens) {
        if (remainingPrompts <= 0) {
            return "!! No remaining prompts in " + getModelName() + " Personal Plan.\n" +
                   "Please purchase more prompts.";
        }
        remainingPrompts--;
        return " Prompt '" + promptName + "' executed successfully (" + tokens + " tokens)\n" +
               "Model: " + getModelName() + " (Personal Plan)\n" +
               "Remaining Prompts: " + remainingPrompts + " / " + monthlyPrompts;
    }

    @Override
    public String display() {
        return super.display() + "\n" +
               "Monthly Prompts: " + monthlyPrompts + "\n" +
               "Remaining Prompts: " + remainingPrompts;
    }
}
