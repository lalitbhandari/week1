package week1.week24;
import java.io.*;


/**
 * Write a description of class n here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */

public class Bike extends Vehicle implements Serializable
{
    private boolean hasCarrier;
    private int gearCount;
    private double distance;

    public Bike(String name, int speed, boolean hasCarrier, int gearCount, double distance) 
    {
        super(name, speed);
        this.hasCarrier = hasCarrier;
        this.gearCount = gearCount;
        this.distance = distance;
    }

    public boolean hasCarrier() {
        return hasCarrier;
    }

    public int getGear() {
        return gearCount;
    }

    public double getDistance() {
        return distance;
    }

    // calculate travel time
    public double calculateTravelTime() 
    {
        int speed = super.getSpeed();
        return distance / speed;
    }

    @Override
    public String getInfo() {
        return super.getInfo() + ", Has Carrier: " + hasCarrier + ", Gear Count: " + gearCount + ", Distance: " + distance;
    }
}
