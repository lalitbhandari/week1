package week1.week18;


import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;

public class StateManagementForm extends JFrame {

    private JTextField nameField, ageField, phoneField;
    private JButton submitBtn;

    public StateManagementForm() {
        setTitle("State-Managed Registration Form");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 250);
        setLayout(new GridLayout(0, 2, 10, 10));
        setLocationRelativeTo(null);

        // ── Name Field 
        add(new JLabel("Name:"));
        nameField = new JTextField();
        add(nameField);

        // ── Age Field 
        add(new JLabel("Age:"));
        ageField = new JTextField();
        add(ageField);

        // ── Phone Field 
        add(new JLabel("Phone:"));
        phoneField = new JTextField();
        add(phoneField);

        // ── Submit Button (DISABLED by default) 
        add(new JLabel(""));
        submitBtn = new JButton("Submit");
        submitBtn.setEnabled(false);               
        submitBtn.setBackground(Color.LIGHT_GRAY);
        add(submitBtn);

        
        // Fires on every keystroke: insert, remove, or change
        DocumentListener listener = new DocumentListener() {
            @Override public void insertUpdate(DocumentEvent e)  { checkFormState(); }
            @Override public void removeUpdate(DocumentEvent e)  { checkFormState(); }
            @Override public void changedUpdate(DocumentEvent e) { checkFormState(); }
        };

        nameField.getDocument().addDocumentListener(listener);
        ageField.getDocument().addDocumentListener(listener);
        phoneField.getDocument().addDocumentListener(listener);

        // ── Submit Action 
        submitBtn.addActionListener(e ->
            JOptionPane.showMessageDialog(this,
                "✅ Form submitted successfully!",
                "Success", JOptionPane.INFORMATION_MESSAGE)
        );

        setVisible(true);
    }

    
    private void checkFormState() {
        boolean nameOk  = !nameField.getText().trim().isEmpty();
        boolean ageOk   = !ageField.getText().trim().isEmpty();
        boolean phoneOk = !phoneField.getText().trim().isEmpty();

        boolean allFilled = nameOk && ageOk && phoneOk;

        submitBtn.setEnabled(allFilled);

        // Visual feedback: green when ready, gray when not
        submitBtn.setBackground(allFilled
            ? new Color(100, 200, 100)   
            : Color.LIGHT_GRAY);         
    }

    public static void main(String[] args) {
        new StateManagementForm();
    }
}
