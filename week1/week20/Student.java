package week1.week20;
import java.util.ArrayList;


/**
 * Write a description of class Student here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Student
{
    private String name, gender, course;
    private ArrayList<String> hobbies;
    public Student(String name, String gender, String course, ArrayList<String> hobbies){
        this.name = name;
        this.gender= gender;
        this.hobbies = hobbies;
        this.course = course;
    }
   @Override
public String toString(){
    return "Name: " + name + 
           " Gender: " + gender + 
           " Course: " + course + 
           " Hobbies: " + hobbies;
}
    
}