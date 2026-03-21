package week1.week20;

import javax.swing.*;
import java.awt.*;

public class StudentDashboardUI extends JFrame {
    //private String btnList ={"addBtn",

    public StudentDashboardUI() {

        // Frame setup
        setTitle("Admin Dashboard");
        setSize(900,600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // HEADER (NORTH) 
        JPanel headerPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        headerPanel.setBackground(Color.LIGHT_GRAY);

        
        //JLabel titleLabel = new JLabel(new FlowLayout(FlowLayout.CENTER));
        JLabel titleLabel = new JLabel("<html><h1>Student Admin Panel</h1></html>",JLabel.CENTER);

        
        headerPanel.add(titleLabel);

        add(headerPanel, BorderLayout.NORTH);

        //  FOOTER (SOUTH) 
        JPanel footerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        footerPanel.setBackground(Color.LIGHT_GRAY);

        JLabel footerLabel = new JLabel("© 2025 Student Management System");
        footerPanel.add(footerLabel);

        add(footerPanel, BorderLayout.SOUTH);

        // SIDEBAR (WEST)
        JPanel sidePanel = new JPanel(new FlowLayout(FlowLayout.LEFT,10,20));
        sidePanel.setPreferredSize(new Dimension(180,0));
        sidePanel.setBackground(new Color(220,220,220));

        JButton addBtn = new JButton("Add Student");
        JButton viewBtn = new JButton("View Student");
        JButton updateBtn = new JButton("Update Student");
        JButton deleteBtn = new JButton("Delete Student");

        Dimension btnSize = new Dimension(150,30);

        addBtn.setPreferredSize(btnSize);
        viewBtn.setPreferredSize(btnSize);
        updateBtn.setPreferredSize(btnSize);
        deleteBtn.setPreferredSize(btnSize);

        sidePanel.add(addBtn);
        sidePanel.add(viewBtn);
        sidePanel.add(updateBtn);
        sidePanel.add(deleteBtn);

        add(sidePanel, BorderLayout.WEST);

        //FORM PANEL (CENTER)
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS));

        // Name
        formPanel.add(new JLabel("Name:"));
        JTextField nameField = new JTextField(20);
        formPanel.add(nameField);

        // Age
        formPanel.add(new JLabel("Age:"));
        JTextField ageField = new JTextField(20);
        formPanel.add(ageField);

        // Gender
        formPanel.add(new JLabel("Gender:"));

        JRadioButton male = new JRadioButton("Male");
        JRadioButton female = new JRadioButton("Female");

        ButtonGroup genderGroup = new ButtonGroup();
        genderGroup.add(male);
        genderGroup.add(female);

        formPanel.add(male);
        formPanel.add(female);

        // Skills
        formPanel.add(new JLabel("Skills"));

        JCheckBox java = new JCheckBox("Java");
        JCheckBox python = new JCheckBox("Python");
        JCheckBox cpp = new JCheckBox("C++");

        formPanel.add(java);
        formPanel.add(python);
        formPanel.add(cpp);

        // Course
        formPanel.add(new JLabel("Course"));

        String courses[] = {"B.Tech","M.Tech","B.Sc","M.Sc"};
        JComboBox<String> courseBox = new JComboBox<>(courses);

        formPanel.add(courseBox);

        // Register Button
        JButton registerBtn = new JButton("Register");
        formPanel.add(registerBtn);

        add(formPanel, BorderLayout.CENTER);

        setVisible(true);
    }

    public static void main(String[] args) {
        new StudentDashboardUI();
    }
}
