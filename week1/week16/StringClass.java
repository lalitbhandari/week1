package week1.week16;
import java.util.Scanner;


/**
 * Write a description of class StringClass here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class StringClass
{
    public static void main (String []  args){
        Scanner scan = new Scanner(System.in);
        System.out.println("Print your first String");
        
        String firstString= scan.nextLine();
        System.out.println("Print your Second String");
        
        String secondString= scan.nextLine();
        
        String fullString= (firstString.concat(secondString));
        
        String combineString = scan.nextLine();
        System.out.println(combineString);
        
        System.out.println("Compared String");
        System.out.println(fullString.equals(combineString));
    }
}