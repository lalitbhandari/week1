package week1.GUI_Concept;
import javax.swing.*;
import java.awt.Color;
/**
 * Write a description of class JustText here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class JustText
{
    public static void main(String[] args){
        JFrame frame =new JFrame("JPanel With titled Border");
        frame.setBounds(300, 200, 500, 450);
        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBounds(50,50,200,150);
        panel.setBackground(Color.GREEN);
        
        panel.setBorder(BorderFactory.createTitledBorder("User Information:"));
        
        JLabel label = new JLabel("Enter your Name:");
        label.setBounds(20, 20,120,25);
        
        JTextField textField = new JTextField();
        textField.setBounds(140, 30, 100, 25);
        
        panel.add(label);
        panel.add(textField);
        
        frame.add(panel);
        
        
        
        
        frame.setVisible(true);        
    }
}