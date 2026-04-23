package week1.week23;




public class Bike extends Vehicle 
{
    private boolean hasCarrier;
    private int gearCount;
    private double distance;

    public Bike(String name, int speed, boolean hasCarrier, int gearCount, double distance) {
        super(name, speed);
        this.hasCarrier = hasCarrier;
        this.gearCount = gearCount;
        this.distance= distance;
    }

    // calculate travel time for a distance
    public double calculateTravelTime() 
    {
        int speed= super.getSpeed();
        return distance / speed ; // time in hours
    }

    @Override
    public String getInfo() {
        return super.getInfo() + ", Has Carrier: " + hasCarrier + ", Gear Count: " + gearCount + ", Distance: "+ distance;
    }
}
