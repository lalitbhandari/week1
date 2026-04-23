package week1.week23;
import java.util.*;


/**
 * Write a description of class Main here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Main
{
    public static void main(String [] args)
    {
        ArrayList<Person> Persons = new ArrayList<Person>();
        //upcasting
        Person p1= new Student("lalit",19,"Computing");
        Person p2= new Teacher("Bro Code",23,"programming");
        
        p1.showBasicInfo();
        p2.showBasicInfo();
        
        Student s1 =(Student)p1;
        s1.attendClass();
        
        
        if(p1 instanceof Student)
        {
            Student s2=(Student)p1;
            s2.attendClass(); 
            Persons.add(s2);
        }
        if(p2 instanceof Teacher)
        {
            Teacher t1=(Teacher)p2;
            t1.takeLecture();
            Persons.add(t1);
        }
        for(Person p: Persons)
        {
            if(p instanceof Teacher t)
            {
                t.showBasicInfo();
            }
            
        }
        
        Teacher t1 =(Teacher) p2;
        t1.takeLecture();
        //p1.attendClass();
        
        
        
    }
}