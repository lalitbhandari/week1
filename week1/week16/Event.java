package week1.week16;
import java.awt.*;
import javax.swing.*;

/**
 * Write a description of class Event here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Event extends JFrame
{
     public Event(){
        setTitle(" Welcome Orientation Day");
        setSize(400,300);
        
        JPanel panel = new JPanel();
        JLabel label= new JLabel("Welocome Orientation Day ");
        
        panel.setBackground(new Color(173, 216, 230));
        setLocationRelativeTo(null);
        setResizable(false);
        
        panel.add(label);
        panel.setLayout(null);
        
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        add(panel);
    }
        public static void main(String [] args){
        Event wd = new Event();
        wd.setVisible(true);
        }
       
}