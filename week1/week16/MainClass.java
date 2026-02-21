package week1.week16;


/**
 * Write a description of class MainClass here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class MainClass
{
    public static void main(String[]args){
        StudentClassDesign s1= new StudentClassDesign("Lalit Bhandari",19,"Islington College",90,"NP06LB","9765443802");
        System.out.println(s1.getInitials());
        System.out.println(s1.toString());
        
    }
}