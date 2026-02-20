package week1.week16;

public class NepaliPhoneValidator {

    public static boolean isValidPhone(String phone) {
        return phone.matches("^(97|98)[0-9]{8}$");
    }

    public static void main(String[] args) {

        String[] testNumbers = {"9841234567",   "1234567890",   "98412345",     "98-412-345"};

        for (String number : testNumbers) {
            System.out.println(number + " → " + isValidPhone(number));
        }
    }
}
