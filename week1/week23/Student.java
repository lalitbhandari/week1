package week1.week23;




class Student extends Person
{
    private String course;

    public Student(String name, int age, String course)
    {
        super(name, age);
        this.course = course;
    }

    @Override
    public String performRole()
    {
        return name + " studies " + course;
    }

    public String attendClass()
    {
        return name + " is attending class.";
    }
}
