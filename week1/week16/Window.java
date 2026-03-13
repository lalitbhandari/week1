package week1.week16;
import javax.swing.*;
import java.awt.*;



/**
 * Write a description of class Window here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Window extends JFrame
{
    public Window(){
        setTitle(" Programming World!!");
        setSize(500,500);
        
        JPanel panel = new JPanel();
        panel.setBounds(50,20,300,400);
        panel.setBackground(Color.CYAN);
        panel.setBorder(BorderFactory.createTitledBorder("User Info"));
        
        JLabel label = new JLabel("User name: ");
        label.setBounds(20,20,100,30);
        
        JTextField txtField = new JTextField();
        txtField.setBounds(130,20,100,30);
        panel.add(txtField);
        
        panel.add(label);
        panel.setLayout(null);
        add(panel);
        
        JButton btn= new JButton("Sumbit");
        btn.setBounds(20,50,100,30);
        btn.setForeground(Color.RED);
        btn.setBackground(new Color(255,200,0));
        
        panel.add(btn);
        
        
        
        
        
        
        
        setLayout(null);
        add(panel);
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
    }
    public static void main(String [] args){
        Window wdd = new Window();
        wdd.setVisible(true);
        }
    }
    