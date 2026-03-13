package week1.week19;

import javax.swing.*;
import java.awt.*;

public class Form extends JFrame {

    public Form() {

        setTitle("Layout Demo");
        setSize(700,700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JPanel titlePanel= new JPanel();
        JLabel titleLabel= new JLabel("Welcome to Admin Dashboard!");
        titlePanel.add(titleLabel);

        JPanel sidePanel= new JPanel();
        sidePanel.setLayout(new BoxLayout(sidePanel, BoxLayout.Y_AXIS));
        sidePanel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));

        String[] buttons= {"Add", "Edit", "Delete", "View"};

        sidePanel.add(Box.createVerticalGlue());

        for(String btn: buttons)
        {
            JButton button= new JButton(btn);
            button.setMaximumSize(new Dimension(100,30));
            sidePanel.add(button);
            sidePanel.add(Box.createVerticalStrut(10));
        }

        sidePanel.add(Box.createVerticalGlue());

        JPanel formpanel = new JPanel();
        formpanel.setLayout(new BoxLayout(formpanel, BoxLayout.Y_AXIS));

        JPanel row1 = new JPanel();
        row1.setLayout(new BoxLayout(row1, BoxLayout.X_AXIS));

        JLabel namelabel = new JLabel("Name: ");
        JTextField txtname = new JTextField();
        txtname.setPreferredSize(new Dimension(10,10));

        row1.add(namelabel);
        row1.add(Box.createHorizontalStrut(10));
        row1.add(txtname);

        JPanel row2 = new JPanel();
        row2.setLayout(new BoxLayout(row2, BoxLayout.X_AXIS));

        JLabel genderLabel = new JLabel("Gender:");
        row2.add(genderLabel);

        String[] genders ={"Male", "Female","Other"};
        ButtonGroup btnGrp = new ButtonGroup();

        for (String gender : genders){
            JRadioButton btn = new JRadioButton(gender);
            btnGrp.add(btn);
            row2.add(btn);
        }

        JLabel hobbyLabel = new JLabel("Hobbies:");

        JPanel hobbiesPanel = new JPanel(new GridLayout(0, 1));
        String[] hobbies = {"Reading", "Gaming", "Cooking", "Traveling", "Sports"};

        for (String hobby : hobbies) {
            hobbiesPanel.add(new JCheckBox(hobby));
        }

        formpanel.add(row1);
        formpanel.add(row2);
        formpanel.add(hobbyLabel);
        formpanel.add(hobbiesPanel);

        add(sidePanel, BorderLayout.WEST);
        add(titlePanel, BorderLayout.NORTH);
        add(formpanel, BorderLayout.CENTER);

    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new Form().setVisible(true);
        });
    }
}