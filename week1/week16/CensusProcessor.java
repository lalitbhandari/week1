package week1.week16;

import java.util.List;
import java.util.ArrayList;

public class CensusProcessor {

    public static StudentClassDesign[] parseCSV(String[] csv) {
        StudentClassDesign[] students = new StudentClassDesign[csv.length - 1];
        for (int i = 1; i < csv.length; i++) {
            String[] parts = csv[i].split(",");
            String name        = parts[0].trim();
            int age            = Integer.parseInt(parts[1].trim());
            String schoolName  = parts[2].trim();
            int grade          = Integer.parseInt(parts[3].trim());
            String citizenship = parts[4].trim();
            String phone       = parts[5].trim();
            students[i - 1] = new StudentClassDesign(name, age, schoolName, grade, citizenship, phone);
        }
        return students;
    }

    public static boolean isValidCitizenship(String cit) {
        if (cit == null || cit.length() != 10) return false;
        if (cit.charAt(2) != '-') return false;
        for (int i = 0; i < 2; i++) {
            if (!Character.isDigit(cit.charAt(i))) return false;
        }
        for (int i = 3; i < 10; i++) {
            if (!Character.isDigit(cit.charAt(i))) return false;
        }
        return true;
    }

    public static StudentClassDesign[] findBySchool(StudentClassDesign[] students, String schoolName) {
        List<StudentClassDesign> result = new ArrayList<StudentClassDesign>();
        for (StudentClassDesign s : students) {
            if (s.getSchoolName().equalsIgnoreCase(schoolName)) {
                result.add(s);
            }
        }
        return result.toArray(new StudentClassDesign[0]);
    }

    public static int countByGrade(StudentClassDesign[] students, int grade) {
        int count = 0;
        for (StudentClassDesign s : students) {
            if (s.getGrade() == grade) count++;
        }
        return count;
    }

    public static double averageAge(StudentClassDesign[] students) {
        int total = 0;
        for (StudentClassDesign s : students) {
            total += s.getAge();
        }
        return (double) total / students.length;
    }

    public static StudentClassDesign findLongestName(StudentClassDesign[] students) {
        StudentClassDesign longest = students[0];
        for (StudentClassDesign s : students) {
            if (s.getName().length() > longest.getName().length()) {
                longest = s;
            }
        }
        return longest;
    }

    public static String generateCensusReport(StudentClassDesign[] students) {
        StringBuilder sb = new StringBuilder();
        int valid = 0, invalid = 0;

        sb.append("========= NEPAL STUDENT CENSUS REPORT =========\n");
        for (StudentClassDesign s : students) {
            if (isValidCitizenship(s.getCitizenship())) valid++;
            else invalid++;
        }
        sb.append(String.format("Total: %d | Valid: %d | Invalid: %d\n\n", students.length, valid, invalid));
        sb.append(String.format("%-8s %-25s %-5s %-8s %-10s\n", "Init", "Name", "Age", "Grade", "Status"));
        sb.append("-------------------------------------------------------------\n");

        for (StudentClassDesign s : students) {
            String status = isValidCitizenship(s.getCitizenship()) ? "VALID" : "INVALID";
            sb.append(String.format("%-8s %-25s %-5d %-8d %-10s\n",
                s.getInitials(), s.getName(), s.getAge(), s.getGrade(), status));
        }

        sb.append("\n============= STATISTICS =============\n");
        sb.append(String.format("Average Age: %.2f\n", averageAge(students)));
        sb.append("Longest Name: " + findLongestName(students).getName() + "\n");
        return sb.toString();
    }
}
