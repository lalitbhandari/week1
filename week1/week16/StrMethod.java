package week1.week16;


/**
 * Write a description of class StrMethod here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class StrMethod
{
    public static void main (String [] args){
        String text=" Hello   world ";
        String trimmedString = text.trim();
        
        String firstTen = trimmedString.substring(0,10);
        System.out.println("Firstn ten Characters:" + firstTen);
        
        String[] words= trimmedString.split("\\s+");
        
        for (String word: words){
            System.out.println(word);
        }
    }
}