package week1.week18;


import javax.swing.*;
import java.awt.*;

public class AccessibleForm extends JFrame {

    public AccessibleForm() {
        setTitle("Accessible Student Form");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(450, 320);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(0, 2, 10, 12));

        // ── Name Field 
        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setDisplayedMnemonic('N');        // ALT+N focuses this label
        JTextField nameField = new JTextField();
        nameLabel.setLabelFor(nameField);           // links label mnemonic → field
        nameField.setToolTipText("Enter your full name (e.g. Alice Sharma)");
        add(nameLabel);
        add(nameField);

        // ── Age Field 
        JLabel ageLabel = new JLabel("Age:");
        ageLabel.setDisplayedMnemonic('A');         // ALT+A focuses age field
        JTextField ageField = new JTextField();
        ageLabel.setLabelFor(ageField);
        ageField.setToolTipText("Enter your age (must be between 18 and 100)");
        add(ageLabel);
        add(ageField);

        // ── Phone Field
        JLabel phoneLabel = new JLabel("Phone:");
        phoneLabel.setDisplayedMnemonic('P');       // ALT+P focuses phone field
        JTextField phoneField = new JTextField();
        phoneLabel.setLabelFor(phoneField);
        phoneField.setToolTipText("Enter 10-digit phone number (e.g. 9801234567)");
        add(phoneLabel);
        add(phoneField);

        // ── Department ComboBox ───────────────────────────────────
        JLabel deptLabel = new JLabel("Department:");
        deptLabel.setDisplayedMnemonic('D');        // ALT+D focuses dropdown
        String[] depts = {"Computer Science", "Engineering", "Business",
                          "Medicine", "Law", "Arts", "Architecture"};
        JComboBox<String> deptBox = new JComboBox<>(depts);
        deptLabel.setLabelFor(deptBox);
        deptBox.setToolTipText("Select your department from the list");
        add(deptLabel);
        add(deptBox);


        JButton saveBtn = new JButton("Save");

        // 1. Mnemonic — keyboard users press ALT+S to trigger Save
        saveBtn.setMnemonic('S');

        // 2. ToolTip — shown on hover, helpful for all users
        saveBtn.setToolTipText("Save the form (Shortcut: ALT + S)");

        // 3. Accessible description — read by screen readers
        saveBtn.getAccessibleContext()
               .setAccessibleDescription(
                   "Saves the student registration data to the system");

        saveBtn.setBackground(new Color(70, 160, 70));
        saveBtn.setForeground(Color.WHITE);
        saveBtn.setFont(new Font("Arial", Font.BOLD, 13));
        saveBtn.addActionListener(e ->
            JOptionPane.showMessageDialog(this,
                "✅ Data saved successfully!",
                "Saved", JOptionPane.INFORMATION_MESSAGE));

        
        JButton cancelBtn = new JButton("Cancel");
        cancelBtn.setMnemonic('C');                 // ALT+C triggers Cancel
        cancelBtn.setToolTipText("Cancel and clear the form (Shortcut: ALT + C)");
        cancelBtn.getAccessibleContext()
                 .setAccessibleDescription("Cancels input and resets all fields");
        cancelBtn.setBackground(new Color(200, 60, 60));
        cancelBtn.setForeground(Color.WHITE);
        cancelBtn.addActionListener(e -> {
            nameField.setText("");
            ageField.setText("");
            phoneField.setText("");
        });

        add(saveBtn);
        add(cancelBtn);

        setVisible(true);
    }

    public static void main(String[] args) {
        new AccessibleForm();
    }
}