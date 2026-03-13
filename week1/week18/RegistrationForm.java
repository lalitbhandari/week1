package week1.week18;
import javax.swing.*;
import java.awt.GridLayout;
import java.awt.FlowLayout;
import java.awt.Dimension;


/**
 * Write a description of class RegistrationForm here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */ 
public class RegistrationForm extends JFrame
{
    public RegistrationForm(){
        setTitle("Student Registration   Form ");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500,550);
        setLayout(new GridLayout(0,2,10,10));
        
        // Student name  input
        //add(new JLabel("Student Name:"));
        //JTextField nameFiled = new JTextField();
       // add(nameFiled);
       JPanel sidePanel= new JPanel();
sidePanel.setLayout(new BoxLayout(sidePanel, BoxLayout.Y_AXIS));
sidePanel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
       String[] buttons= {"Add", "Edit", "Delete", "View"};

sidePanel.add(Box.createVerticalGlue());

for(String btn: buttons)
{
JButton button= new JButton(btn);
button.setMaximumSize(new Dimension(100,30));
sidePanel.add(button);
sidePanel.add(Box.createVerticalStrut(10));
}   
        
        // student age input
        //add(new JLabel(" age:"));
        //JTextField ageFiled = new JTextField();
        //add(ageFiled);
        
        // student number input
        //add(new JLabel("Phone Number:"));
        //JTextField phoneField = new JTextField();
        //add(phoneField);
        
        //add(new JLabel("Department:"));
        //String[] departments = {
        //    "Computer Science", "Engineering", "Business", "Arts",
        //    "Medicine", "Law", "Education", "Architecture",
         //   "Psychology", "Economics", "Biology", "Chemistry"
       // };
       // JComboBox<String> departmentBox = new JComboBox<>(departments);
       // add(departmentBox);

        // 5. Gender → JRadioButton 
        add(new JLabel("Gender:"));
        JPanel genderPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JRadioButton male   = new JRadioButton("Male");
        JRadioButton female = new JRadioButton("Female");
        JRadioButton other  = new JRadioButton("Other");

        ButtonGroup genderGroup = new ButtonGroup(); 
        genderGroup.add(male);
        genderGroup.add(female);
        genderGroup.add(other);

        genderPanel.add(male);
        genderPanel.add(female);
        genderPanel.add(other);
        add(genderPanel);

        // 6. Hobbies → JCheckBox 
        add(new JLabel("Hobbies:"));
        JPanel hobbiesPanel = new JPanel(new GridLayout(0, 1));
        String[] hobbies = {"Reading", "Gaming", "Cooking", "Traveling", "Sports"};
        for (String hobby : hobbies) {
            hobbiesPanel.add(new JCheckBox(hobby));
        }
        add(hobbiesPanel);

        // Submit Button
        add(new JLabel(""));
        JButton submitBtn = new JButton("Submit");
        add(submitBtn);

        setVisible(true);
    }

    public static void main(String[] args) {
        new RegistrationForm();
    }
}
        
        
        
        
        
        
    
    
    
