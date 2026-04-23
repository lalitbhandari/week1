public class Mainn {
    public static void main(String[] args) {

        Vehicle s1 = new Vehicle();
        s1.car();

        Bike s2 = new Bike();
        s2.car();   // inherited
        s2.bike();  // own method
    }
}