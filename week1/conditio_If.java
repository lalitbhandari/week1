package week1;
import java.util.Scanner;


/**
 * Write a description of class conditio_If here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class conditio_If
{
    public static void main(String [] args){
        Scanner scan = new Scanner (System.in);
        System.out.println("Enter your age :");
        int age = scan.nextInt();
         if(age>=18){
             System.out.println("You are elligable for vote");
         }
         else if(age<18){
             System.out.println("Your age is bellow 18");
         }
        
        
    }
}