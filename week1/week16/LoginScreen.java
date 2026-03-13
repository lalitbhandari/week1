package week1.week16;
import javax.swing.*;
import java.awt.*;

public class LoginScreen {

    public static void main(String[] args) {

        // Create JFrame
        JFrame frame = new JFrame("Login Screen");
        frame.setSize(400, 300);
        frame.setLayout(null);   // Important for setBounds()
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create Username TextField
        JTextField usernameField = new JTextField();
        usernameField.setBounds(120, 100, 150, 30);  
        frame.add(usernameField);

        // Create Login Button
        JButton loginButton = new JButton("Login");
        loginButton.setBounds(150, 150, 100, 40);   
        loginButton.setBackground(Color.GREEN);     // Green color
        loginButton.setForeground(Color.WHITE);     // Text color (optional)
        frame.add(loginButton);

        // Make Frame Visible
        frame.setVisible(true);
    }
}