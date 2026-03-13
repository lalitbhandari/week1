package week1.week16;
import javax.swing.*;


/**
 * Write a description of class Student here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Student extends JFrame
{
    public Student(){
        setTitle(" reusable window to show student information");
        setSize(300,400);
        JPanel panel = new JPanel();
        JLabel label= new JLabel("reusable window to show student information");
        label.setBounds(20,20,300,30);
        
        panel.add(label);
        panel.setLayout(null);
        
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        add(panel);
        
    }
    public static void main(String [] args){
        SwingUtilities.invokeLater(()->{
            Student wd= new Student();
            wd.setVisible(true);
        });
    }
}