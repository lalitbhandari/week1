package week1.week21;


import java.util.*;

public class Employee {

    private String empId, name, employmentType, department;
    private double salary;
    private ArrayList<String> benefits= new ArrayList<>();

    public Employee(String empId, String name, double salary, String employmentType, ArrayList<String> benefits, String department) {
        //this.empId = empId;
        this.name = name;
        this.salary = salary;
        this.employmentType = employmentType;
        this.benefits = benefits;
        this.department = department;
    }

    @Override
    public String toString() 
    {
        return "ID: " + empId+ "Name: " + name+  "Salary: " + salary+ "Type: " + employmentType+ "Benefits: " + benefits + "Department: " + department;
    }
    }
