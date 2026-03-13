package week1.week18;


import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ValidationForm extends JFrame {

    private JTextField nameField, ageField, phoneField;
    private JLabel nameError, ageError, phoneError;

    public ValidationForm() {
        setTitle("Staff Registration - Validated Form");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(450, 350);
        setLayout(new GridLayout(0, 2, 10, 8));
        setLocationRelativeTo(null);

        // Name Field
        add(new JLabel("Name:"));
        nameField = new JTextField();
        add(nameField);

        add(new JLabel(""));
        nameError = new JLabel("");
        nameError.setForeground(Color.RED);
        add(nameError);

        // Age Field
        add(new JLabel("Age:"));
        ageField = new JTextField();
        add(ageField);

        add(new JLabel(""));
        ageError = new JLabel("");
        ageError.setForeground(Color.RED);
        add(ageError);

        // ── Phone Field 
        add(new JLabel("Phone Number:"));
        phoneField = new JTextField();
        add(phoneField);

        add(new JLabel(""));
        phoneError = new JLabel("");
        phoneError.setForeground(Color.RED);
        add(phoneError);

        // ── Submit Button
        add(new JLabel(""));
        JButton submitBtn = new JButton("Submit");
        submitBtn.addActionListener(e -> validateAndSubmit());
        add(submitBtn);

        setVisible(true);
    }

    
    private void validateAndSubmit() {
        boolean isValid = true;

        // ─ PRESENCE CHECK — Name must not be empty 
        String name = nameField.getText().trim();
        if (name.isEmpty()) {
            nameError.setText(" Name cannot be empty.");
            nameField.setBackground(new Color(255, 200, 200)); 
            isValid = false;
        } else {
            nameError.setText("✅");
            nameField.setBackground(Color.WHITE);
        }

        // Age must be a number 
        //  RANGE CHECK — Age must be between 18 and 100
        String ageText = ageField.getText().trim();
        if (ageText.isEmpty()) {
            ageError.setText("❌ Age cannot be empty.");
            ageField.setBackground(new Color(255, 200, 200));
            isValid = false;
        } else {
            try {
                int age = Integer.parseInt(ageText);  
                if (age < 18 || age > 100) {         
                    ageError.setText("❌ Age must be between 18 and 100.");
                    ageField.setBackground(new Color(255, 200, 200));
                    isValid = false;
                } else {
                    ageError.setText("✅");
                    ageField.setBackground(Color.WHITE);
                }
            } catch (NumberFormatException ex) {
                ageError.setText("❌ Age must be a number (e.g. 25).");
                ageField.setBackground(new Color(255, 200, 200));
                isValid = false;
            }
        }

       
        // Accepts: 10-digit numbers or formats like +977-98123456789
        String phone = phoneField.getText().trim();
        String phoneRegex = "^(\\+?[0-9]{1,3}-?)?[0-9]{7,10}$";
        if (phone.isEmpty()) {
            phoneError.setText("❌ Phone cannot be empty.");
            phoneField.setBackground(new Color(255, 200, 200));
            isValid = false;
        } else if (!phone.matches(phoneRegex)) {
            phoneError.setText("❌ Invalid format (e.g. 9812345678).");
            phoneField.setBackground(new Color(255, 200, 200));
            isValid = false;
        } else {
            phoneError.setText("✅");
            phoneField.setBackground(Color.WHITE);
        }

    
        if (isValid) {
            JOptionPane.showMessageDialog(this,
                "✅ Form submitted successfully!",
                "Success", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this,
                "⚠️ Please fix the errors highlighted in red.",
                "Validation Failed", JOptionPane.WARNING_MESSAGE);
        }
    }

    public static void main(String[] args) {
        new ValidationForm();
    }
}
