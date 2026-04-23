import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class SimpleEmployeeForm extends JFrame implements ActionListener {

    private JTextField nameField, salaryField;
    private JRadioButton fullTime, partTime, contract;
    private JCheckBox health, dental, retirement;
    private JComboBox<String> departmentBox;
    private JTextArea displayArea;
    private JButton registerBtn, clearBtn;

    private ArrayList<String> employees = new ArrayList<>();

    public SimpleEmployeeForm() {
        setTitle("Employee Form");
        setSize(500, 500);
        setLayout(new FlowLayout());
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Name
        add(new JLabel("Full Name:"));
        nameField = new JTextField(20);
        add(nameField);

        // Salary
        add(new JLabel("Salary:"));
        salaryField = new JTextField(20);
        add(salaryField);

        // Employment Type
        add(new JLabel("Employment Type:"));
        fullTime = new JRadioButton("Full-Time");
        partTime = new JRadioButton("Part-Time");
        contract = new JRadioButton("Contract");

        ButtonGroup group = new ButtonGroup();
        group.add(fullTime);
        group.add(partTime);
        group.add(contract);

        add(fullTime); add(partTime); add(contract);

        // Benefits
        add(new JLabel("Benefits:"));
        health = new JCheckBox("Health");
        dental = new JCheckBox("Dental");
        retirement = new JCheckBox("Retirement");

        add(health); add(dental); add(retirement);

        // Department
        add(new JLabel("Department:"));
        String[] depts = {"IT", "HR", "Finance"};
        departmentBox = new JComboBox<>(depts);
        add(departmentBox);

        // Buttons
        registerBtn = new JButton("Register");
        clearBtn = new JButton("Clear");

        registerBtn.addActionListener(this);
        clearBtn.addActionListener(e -> clearForm());

        add(registerBtn);
        add(clearBtn);

        // Display
        displayArea = new JTextArea(10, 40);
        displayArea.setEditable(false);
        add(new JScrollPane(displayArea));

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        registerEmployee();
    }

    private void registerEmployee() {
        try {
            String name = nameField.getText().trim();
            if (name.isEmpty()) throw new Exception("Name required");

            double salary = Double.parseDouble(salaryField.getText());

            String type = "";
            if (fullTime.isSelected()) type = "Full-Time";
            else if (partTime.isSelected()) type = "Part-Time";
            else if (contract.isSelected()) type = "Contract";

            if (type.isEmpty()) throw new Exception("Select employment type");

            ArrayList<String> benefits = new ArrayList<>();
            if (health.isSelected()) benefits.add("Health");
            if (dental.isSelected()) benefits.add("Dental");
            if (retirement.isSelected()) benefits.add("Retirement");

            String dept = departmentBox.getSelectedItem().toString();

            String emp = name + " | " + salary + " | " + type + " | " + benefits + " | " + dept;
            employees.add(emp);

            displayArea.append(emp + "\n");

            JOptionPane.showMessageDialog(this, "Employee Added!");

            clearForm();

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Salary must be number");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }
    }

    private void clearForm() {
        nameField.setText("");
        salaryField.setText("");
        fullTime.setSelected(false);
        partTime.setSelected(false);
        contract.setSelected(false);
        health.setSelected(false);
        dental.setSelected(false);
        retirement.setSelected(false);
        departmentBox.setSelectedIndex(0);
    }

    public static void main(String[] args) {
        new SimpleEmployeeForm();
    }
}