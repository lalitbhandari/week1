package week1;


/**
 * Write a description of class waterTank here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class waterTank
{
    public static void main(String [] args)
    {
        System.out.println("WaterLevel Checked");
        double waterLevel = 12220;
        if(waterLevel<950)
        {
            System.out.println("Appropriate waterLevel");
        }
        else if(waterLevel<=950 && waterLevel >=1200)
        {
            System.out.println("Normal Level");
            
        }
        else{
            System.out.println("Warninng level reached");
        }
        
    }
}