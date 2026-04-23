package week1.week24;
import java.io.*;


/**
 * Write a description of class m here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */

public class Car extends Vehicle implements Serializable 
{
    private int seats;
    private double fuelEfficiency; 
    private double tankCapacity;

    public Car(String name, int speed, int seats, double fuelEfficiency, double tankCapacity) 
    {
        super(name, speed);
        this.seats = seats;
        this.fuelEfficiency = fuelEfficiency;
        this.tankCapacity = tankCapacity;
    }

    public double calculateRange() 
    {
        return fuelEfficiency * tankCapacity;
    }

    public int getSeats() {
        return seats;
    }

    public double getFuel() {
        return fuelEfficiency;
    }

    public double getTank() {
        return tankCapacity;
    }
    
    @Override
    public String getInfo()
    {
        return super.getInfo() + ", Seats: " + seats + ", Fuel Efficiency: " + fuelEfficiency + " km/l" + ", tankCapacity: " + tankCapacity + " litres";
    }
}
