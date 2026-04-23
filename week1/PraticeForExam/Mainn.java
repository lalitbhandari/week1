package week1.PraticeForExam;


/**
 * Write a description of class Mainn here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Mainn
{
    public static void main (String [] args)
    {
        Vechilee s1 = new Vechilee();
        s1.car();

        Bike s2 = new Bike();
        s2.car();   // inherited
        //s2.bike();  // own method
    }
}