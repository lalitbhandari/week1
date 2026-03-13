package week1.week19;

import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.Dimension;

public class LayoutDemo extends JFrame
{
    public LayoutDemo(){
        setTitle("Layout Demo");
        setSize(500,550);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        
        // Title Panel
        JPanel titlePanel = new JPanel();
        JLabel titleLabel = new JLabel("Welcome to Islington");
        titlePanel.add(titleLabel);
        add(titlePanel, BorderLayout.NORTH);
        
        // Side Panel
        JPanel sidePanel = new JPanel();
        sidePanel.setLayout(new BoxLayout(sidePanel, BoxLayout.Y_AXIS));
        add(sidePanel, BorderLayout.WEST);
        
        
        
        // Buttons
        String[] buttons = {"Add", "Edit", "Delete", "View"};
        
        for(String btn : buttons){
            JButton button = new JButton(btn);
            button.setMaximumSize(new Dimension(100,30));
            sidePanel.add(button);
            sidePanel.add(Box.createVerticalStrut(10));
            
        }
    }

    public static void main(String [] args){
        SwingUtilities.invokeLater(() -> {
            new LayoutDemo().setVisible(true);
        });
    }
}