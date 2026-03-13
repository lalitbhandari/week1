package week1.week16;
import javax.swing.*;
import javax.swing.JFrame;
import java.awt.Label;
import javax.swing.JLabel;


/**
 * Write a description of class lableExample here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class lableExample
{
    public static void main (String [] args){
        JFrame frame = new JFrame(" hello lalit");
        JLabel label = new JLabel(" window");
        frame.add(label);
        frame.setSize(400, 300);
        frame.setVisible(true);
        
        
    }
}