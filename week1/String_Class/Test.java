package week1.String_Class;


/**
 * Write a description of class Test here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Test
{
    public static void main(String [] args)
    {
        // reference variable, and the Student is object and student is variable, new Student is created
        //in heap memory and store in student location;
        // 
        Student student = new Student();
        student.name="Lalit Bhandari";
        student.age =19;
        student.rollNo= 43;
        System.out.println(student.name);
        
    }
}