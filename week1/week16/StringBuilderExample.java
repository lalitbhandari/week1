package week1.week16;

public class StringBuilderExample {

    public static void main(String[] args) {

        // Create StringBuilder object
        StringBuilder sb = new StringBuilder();

        // Original strings
        String firstString = "Lalit";
        String secondString = "Bhandari";

        
        sb.append(firstString);
        sb.append(" "); // adding space
        sb.append(secondString);

        // Append integer
        sb.append(19);

        // Append special character
        sb.append("!");

        // Convert to String
        String finalString = sb.toString();

        // Print final result
        System.out.println("Final Modified String: " + finalString);
    }
}