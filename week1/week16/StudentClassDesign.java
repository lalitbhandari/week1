package week1.week16;


/**
 * Write a description of class StudentClassDesign here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class StudentClassDesign
{
    // variable Declare
    private String name;
    private int age;
    private String schoolName;
    private int grade;
    private String citizenship;
    private String phone;
    
    public StudentClassDesign( String name, int age, String schoolName,int grade,String citizenship, String phone){
        this.name = name;
        this.age= age;
        this.schoolName = schoolName;
        this.grade = grade;
        this.citizenship= citizenship;
        this.phone = phone;
    }
    // getter Setter method 
        public String getName(){
            return name;
        }
        public void setName(String name){
            this.name = name;
        }
        public int getAge(){
            return age;
        }
        public void setAge(int age){
            this.age= age;
        }
        public String getSchoolName(){
            return schoolName;
        }
        public void setSchoolName( String schoolName){
            this.schoolName= schoolName;
        }
        public int getGrade(){
            return grade;
        }
        public void setGrade( int grade){
            this.grade= grade;
        }
        public String getCitizenship(){
            return citizenship;
        }
        public void setCitizenship(String citizenship){
            this.citizenship= citizenship;
        }
        public String getPhone(){
            return phone;
        }
        public void setPhone(String phone){
            this.phone= phone;
        }
        // method to get initials (LBB)
        public String getInitials(){
        String[] words = name.split(" ");
        StringBuilder sb = new StringBuilder();
        
       for(String word : words){
           if(word.length()>0){
               sb.append(Character.toUpperCase(word.charAt(0)));
               
           }
       }
       return sb.toString();
    }
        // toString Method
        @Override
        public String toString() {
            return "==== Nepal School Census Report ====\n"+
            "Total Student: Valid records: Invalid:\n"+ 
            "--Student List--\n"+
           "Name: " + name +
           " Age: |" + age +
           "| School : " + schoolName +
           " Grade: |" + grade +
           "| Citizenship : " + citizenship +
           " Phone: " + phone;
        }
        
        
}

        
        
        
    
            
            
        
            
        
        

    