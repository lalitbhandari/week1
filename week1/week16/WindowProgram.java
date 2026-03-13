package week1.week16;
import javax.swing.*;



/**
 * Write a description of class WindowProgram here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class WindowProgram extends JFrame
{
    public WindowProgram(){
        setTitle(" Library Notice");
        setSize(400,300);
        
        JPanel panel = new JPanel();
        JLabel label= new JLabel("Library Open at 7:30AM");
        label.setBounds(20,20,300,30);
        
        panel.add(label);
        panel.setLayout(null);
        
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        add(panel);
        
    }
    public static void main(String [] args){
        WindowProgram wd = new WindowProgram();
        wd.setVisible(true);
        }
        
    }
