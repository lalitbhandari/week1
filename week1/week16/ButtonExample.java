package week1.week16;
import javax.swing.JFrame;
import javax.swing.JButton;


/**
 * Write a description of class ButtonExample here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class ButtonExample
{
    public static void main(String [] args){
        JFrame frame = new JFrame (" Button Example");
        JButton button = new JButton ("Click me");
        
        frame.add(button);
        frame.setSize(400, 300);
        frame.setVisible(true);
        
        
        
    }
}