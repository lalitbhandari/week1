package week1.week18;

import java.util.ArrayList;

public class Student {

    // ── Attributes ────────────────────────────────────────────────
    private int    id;
    private String name;
    private String gender;
    private String skill;
    private String course;

    // ── Static ArrayList shared across all instances ──────────────
    private static ArrayList<Student> studentList = new ArrayList<>();

    
    public Student(int id, String name, String gender,
                   String skill, String course) {
        this.id     = id;
        this.name   = name;
        this.gender = gender;
        this.skill  = skill;
        this.course = course;
    }

    public int    getId()     { return id;     }
    public String getName()   { return name;   }
    public String getGender() { return gender; }
    public String getSkill()  { return skill;  }
    public String getCourse() { return course; }


    public static boolean addStudent(Student student) {
        // Check for duplicate ID
        for (Student s : studentList) {
            if (s.getId() == student.getId()) {
                return false;   // ❌ duplicate ID found
            }
        }
        studentList.add(student);
        return true;            // ✅ added successfully
    }

    public static void viewStudents() {
        if (studentList.isEmpty()) {
            System.out.println("No student records found.");
            return;
        }
        System.out.println("\n===== Student Records =====");
        System.out.printf("%-5s %-15s %-8s %-15s %-10s%n",
                          "ID", "Name", "Gender", "Skill", "Course");
        System.out.println("-".repeat(55));
        for (Student s : studentList) {
            System.out.printf("%-5d %-15s %-8s %-15s %-10s%n",
                s.getId(), s.getName(), s.getGender(),
                s.getSkill(), s.getCourse());
        }
    }

    public static boolean deleteStudent(int id) {
        for (Student s : studentList) {
            if (s.getId() == id) {
                studentList.remove(s);
                return true;    // ✅ deleted
            }
        }
        return false;           // ❌ ID not found
    }

    // ── Getter for the list (used by GUI to populate JTable) ──────
    public static ArrayList<Student> getStudentList() {
        return studentList;
    }
}