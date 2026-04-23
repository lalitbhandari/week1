package week1.week22;


import java.util.*;


public class Employee {

    private String  name, employmentType, department;
    private double salary;
    private ArrayList<String> benefits= new ArrayList<>();
    

    public Employee( String name, double salary, String employmentType, ArrayList <String> benefits, String department) {
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
        return "name"+ name+  "Salary: " + salary+ "Type: " + employmentType+ "Benefits: " + benefits + "Department: " + department;
    }
    }
