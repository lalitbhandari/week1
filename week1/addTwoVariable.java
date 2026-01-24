package week1;
import java.util.Scanner;


/**
 * Write a description of class addTwoVariable here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class addTwoVariable
{
    public static void main(String [] args){
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter your first Number:");
        int num1 = scan.nextInt();
        
        System.out.println("Enter your Second Number:");
        int num2 =scan.nextInt();
        
        int sum = num1 +num2;
        System.out.println("Num1 and num2 sum is :"+sum);
    }
}