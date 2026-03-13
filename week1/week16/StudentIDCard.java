package week1.week16;
import javax.swing.*;
import java.awt.*;

public class StudentIDCard {

    public static void main(String[] args) {

        // Create frame
        JFrame frame = new JFrame("Student ID Preview");
        frame.setSize(400, 250);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // HTML formatted text
        String htmlText = "<html>"
                + "<div style='text-align:center;'>"
                + "<h2>Student ID Card</h2>"
                + "Name: Lalit Bhandari<br>"
                + "Module: Programming<br>"
                + "College: Islington College"
                + "</div>"
                + "</html>";

        // Create JLabel with HTML
        JLabel label = new JLabel(htmlText);

        // Align label to center
        label.setHorizontalAlignment(SwingConstants.CENTER);

        // Add label to frame
        frame.add(label);

        // Make frame visible
        frame.setVisible(true);
    }
}