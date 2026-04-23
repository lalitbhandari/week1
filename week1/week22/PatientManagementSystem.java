package week1.week22;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class PatientManagementSystem extends JFrame implements ActionListener {

    private JTextField txtName, txtAge;
    private JComboBox<String> comboGender, comboDisease;
    private JButton btnSubmit, btnClear;
    private JPanel formPanel;
    private JTextArea displayArea;

    private ArrayList<Patient> patients = new ArrayList<>();

    public PatientManagementSystem() {
        setTitle("Patient Management System");
        setSize(500, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        formPanel = new JPanel();
        formPanel.setLayout(new GridLayout(5, 2, 10, 10));
        formPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Name
        formPanel.add(new JLabel("Patient Name:"));
        txtName = new JTextField();
        formPanel.add(txtName);

        // Age
        formPanel.add(new JLabel("Age:"));
        txtAge = new JTextField();
        formPanel.add(txtAge);

        // Gender
        formPanel.add(new JLabel("Gender:"));
        String[] genders = {"Select Gender", "Male", "Female", "Other"};
        comboGender = new JComboBox<>(genders);
        formPanel.add(comboGender);

        // Disease
        formPanel.add(new JLabel("Disease:"));
        String[] diseases = {"Select Disease", "Flu", "Covid", "Diabetes", "Cancer"};
        comboDisease = new JComboBox<>(diseases);
        formPanel.add(comboDisease);

        // Buttons
        btnSubmit = new JButton("Submit");
        btnSubmit.addActionListener(this);

        btnClear = new JButton("Clear");
        btnClear.addActionListener(e -> clearForm());

        formPanel.add(btnSubmit);
        formPanel.add(btnClear);

        add(formPanel, BorderLayout.NORTH);

        displayArea = new JTextArea();
        displayArea.setEditable(false);
        JScrollPane scroll = new JScrollPane(displayArea);
        scroll.setBorder(BorderFactory.createTitledBorder("Patient Records"));

        add(scroll, BorderLayout.CENTER);

        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new PatientManagementSystem());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnSubmit) {
            try {
                handleSubmit();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public void handleSubmit() {
        String name = txtName.getText().trim();
        if (name.isEmpty()) {
            throw new IllegalArgumentException("Name field cannot be empty");
        }

        String ageText = txtAge.getText().trim();
        int age;
        try {
            age = Integer.parseInt(ageText);
        } catch (NumberFormatException e) {
            throw new NumberFormatException("Age must be a number");
        }

        String gender = comboGender.getSelectedItem().toString();
        if (gender.equals("Select Gender")) {
            throw new IllegalArgumentException("Please select a valid gender");
        }

        String disease = comboDisease.getSelectedItem().toString();
        if (disease.equals("Select Disease")) {
            throw new IllegalArgumentException("Please select a disease");
        }

        Patient patient = new Patient(name, age, gender, disease);
        patients.add(patient);

        displayArea.append(patient.toString() + "\n");

        JOptionPane.showMessageDialog(this, "Patient added successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);

        clearForm();
    }

    public void clearForm() {
        txtName.setText("");
        txtAge.setText("");
        comboGender.setSelectedIndex(0);
        comboDisease.setSelectedIndex(0);
    }
}