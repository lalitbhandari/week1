package week1.week16;

/**
 * Write a description of class d here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class StudentClassDesign {
    // Variable declare instance variable

    private String name;
    private int age;
    private String schoolName;
    private int grade;
    private String citizenship;
    private String phone;

    public StudentClassDesign(String name, int age, String schoolName, int grade, String citizenship, String phone) {
        this.name = name;
        this.age = age;
        this.schoolName = schoolName;
        this.grade = grade;
        this.citizenship = citizenship;
        this.phone = phone;
    }
    // Getter setter method 

    public String getName(){
        return name; }
    public void setName(String name){ 
        this.name = name; }
    public int getAge(){
        return age; }
    public void setAge(int age){ 
        this.age = age; }
    public String getSchoolName()
    { return schoolName; }
    public void setSchoolName(String s){
        this.schoolName = s; }
    public int getGrade(){
        return grade; }
    public void setGrade(int grade){
        this.grade = grade; }
    public String getCitizenship(){
        return citizenship; }
    public void setCitizenship(String c){
        this.citizenship = c; }
    public String getPhone(){
        return phone; }
    public void setPhone(String phone){
        this.phone = phone; 
    }
    // Method for First Letter of Name Capital Example: lalit Bhandari(L.B);

    public String getInitials() {
        String[] words = name.split(" ");
        StringBuilder sb = new StringBuilder();
        for (String word : words) {
            sb.append(Character.toUpperCase(word.charAt(0))).append(".");
        }
        return sb.toString();
    }
    // toString Method

    public String toString() {
        return String.format("%-25s | %2d | Grade %2d | %-20s", name, age, grade, schoolName);
    }
}