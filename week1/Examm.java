package week1;


/**
 * Write a description of class examm here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Examm
{
    private static String collegeName;
    private String studentName;
    private int rollNo;
    private String favSubject;
    private String favTeacher;
    public Examm(String studentName, int rollNo, String favTeacher, String favSubject){
        this.studentName= studentName;
        this.rollNo = rollNo;
        this.favSubject = favSubject;
        this.favTeacher= favTeacher;
    }
    public static String getCollegeName(){
        return collegeName;
    }
    public  static void setCollegeName( String collegeName){
        Examm.collegeName= collegeName;
    }
    public String getStudentName(){
        return studentName; 
    }
    public void setStudentName( String studentName){
        this.studentName= studentName;
    }
    public int getRollNo(){
        return rollNo;
    }
    public void setRollNo( int rollNo){
        this.rollNo = rollNo;
    }
    public String getFavTeacher(){
        return favTeacher;
    }
    public void setFavTeacher(String favTeacher){
        this.favTeacher = favTeacher;
    }
    public String getFavSubject(){
        return favSubject;
    }
    public void setFavSubject( String favSubject){
        this.favSubject = favSubject;
    }
    
    public void display(){  
        System.out.println("Student College Name:"+collegeName+"Student Name: "+studentName+"RollNo:"+rollNo+"Student fav Teacher"+favTeacher+"Student FavSubject"+favSubject);
    }
}