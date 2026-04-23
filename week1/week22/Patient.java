package week1.week22;

 public class Patient {
    private String name;
    private int age;
    private String gender;
    private String disease;

    public Patient(String name, int age, String gender, String disease) 
    {
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.disease = disease;
    }

    @Override
    public String toString()
    {
            return "Patient [Name=" + name + ", Age=" + age + ", Gender=" + gender + ", Disease=" + disease + "]";
    }
    
    }
