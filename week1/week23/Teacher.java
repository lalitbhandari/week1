package week1.week23;


class Teacher extends Person
{
    private String subject;

    public Teacher(String name, int age, String subject)
    {
        super(name, age);
        this.subject = subject;
    }

    @Override
    public String performRole()
    {
        return name + " teaches " + subject;
    }

    public String takeLecture()
    {
        return name + " is taking lecture.";
    }
}
