package week1;
import java.util.Scanner;


/**
 * Write a description of class studentProgram here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class studentProgram
{
    public static void main(String [] args)
    {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter your marks");
        double marks= sc.nextInt();
        if(marks>=40){
            System.out.println("You are passed in the examanation ..!");
        }
        else{
            System.out.println("You are failed in this exam..!");
        }
    }
}