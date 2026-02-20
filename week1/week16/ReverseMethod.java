package week1.week16;


/**
 * Write a description of class ReverseMethod here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class ReverseMethod
{
    public static void main(String [] args)
    {
        StringBuilder sb = new StringBuilder();
        String givenString = "lalit";
        
        for(int i = givenString.length() - 1; i >= 0; i--) {
            sb.append(givenString.charAt(i));
        }
        
        String reversed= sb.toString();
        System.out.println(reversed);
         if(reversed.equals(givenString)){
            System.out.println("The string is a palindrome");
        }else {
                System.out.println("The string is a palindrome");
            }
        }
    
}