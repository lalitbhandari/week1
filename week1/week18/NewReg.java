package week1.week18;
import javax.swing.*;
import java.awt.*;


/**
 * Write a description of class NewReg here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class NewReg extends JFrame
{
    public static void main(String [] args){
        JFrame f = new JFrame();
        
        f.setLayout(new GridLayout(0,2,10,10));
        f.add(new Button("button 1"));
        f.add(new Button("Button 2"));
        f.add(new Button("Button 3"));
        f.add(new Button("Button 4"));
        
        f.setSize(300,350);
        f.setVisible(true);
        
        
    }
}