package week1.week23;




abstract class Person
{
    protected String name;
    protected int age;

    public Person(String name, int age)
    {
        this.name = name;
        this.age = age;
    }

    public void showBasicInfo()
    {
        System.out.println("Name: " + name + " Age: " + age);
    }

    public abstract String performRole();
}
