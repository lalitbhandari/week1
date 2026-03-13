package week1.week19;

import javax.swing.*;
import java.awt.*;


public class StudentForm extends JFrame
{
    private String[] btnList= {"Add", "Edit", "Delete", "View"};
    
    public StudentForm()
    {
        setTitle("Layout Demo");
        setSize(500,500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        
        JPanel titlePanel= new JPanel();
        JLabel label= new JLabel("Student Registration Details");
        label.setFont(new Font("Arial", Font.BOLD, 18));
        label.setForeground(Color.RED);
        titlePanel.add(label);
        
        add(titlePanel, BorderLayout.NORTH);
        
        JPanel sidebarPanel= new JPanel();
        sidebarPanel.setLayout(new BoxLayout(sidebarPanel, BoxLayout.Y_AXIS));
        sidebarPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        sidebarPanel.add(Box.createVerticalGlue());
        
        for(String btn: btnList)
        {
            JButton button= new JButton(btn);
            button.setMaximumSize(new Dimension(100,40));
            sidebarPanel.add(button);
            sidebarPanel.add(Box.createVerticalStrut(10));
        }
        
        sidebarPanel.add(Box.createVerticalGlue());
        JPanel formPanel = new JPanel();
        FormPanel.setLayout(new BoxLayout(FormPanel, BoxLayout.Y_AXIS));
        
        JPanel row1 = new JPanel();
        row1.setLayout(new BoxLayout(row1, BoxLayout.X_AXIS));
        
        JLabel nameLabel = new JLabel("Name:");
        JTextField txtName = new JTextField();
        
        row1.add(nameLabel);
        row1.add(txtName);
        add(sidebarPanel, BorderLayout.WEST);
        
        JPanel formPanel= new JPanel();
        formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS));
        formPanel.setBorder(BorderFactory.createEmptyBorder(15,15,15,15));
        
        JPanel row1= new JPanel();
        row1.setLayout(new BoxLayout(row1, BoxLayout.X_AXIS));
        row1.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        JLabel nameLbl= new JLabel("Name: ");
        JTextField txtField= new JTextField(15);
        row1.add(nameLbl);
        //.add(Box.createHorizontalStrut(10));