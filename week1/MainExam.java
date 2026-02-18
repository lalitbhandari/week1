package week1;


/**
 * Write a description of class MainExam here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class MainExam
{
    public static void main(String [] args){
        Examm.setCollegeName ("Islington college");
         Examm s1 = new Examm(" lalit bhandari",101,"Sandesh Paudel","Java");
          System.out.println(Examm.getCollegeName());
          System.out.println(s1.getStudentName());
         s1.display();
    }
}