import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;
import java.awt.event.*;

public class EmployeeManagementTemplate extends JFrame implements ActionListener {

    // UI Components
    private JLabel headerLabel;
    private JTextField empIdField, nameField, salaryField;
    private JRadioButton fullTime, partTime, contract;
    private JCheckBox healthInsurance, dentalInsurance, retirementPlan;
    private JComboBox<String> departmentBox;
    private JTextArea displayArea;
    private JLabel charCountLabel, mousePositionLabel;
    private JLabel statusLabel;

    private JButton registerBtn, clearFormBtn, clearAllBtn;

    private ArrayList<Employee> employees = new ArrayList<>();

    public EmployeeManagementTemplate() {
        setTitle("Employee Management System");
        setLayout(new BorderLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1200, 700);

        // ===== Header =====
        JPanel header = new JPanel(new FlowLayout(FlowLayout.LEFT));
        headerLabel = new JLabel("<html><h1>Employee Management System</h1></html>");
        header.add(headerLabel);
        header.setBackground(new Color(70, 130, 200));
        headerLabel.setForeground(Color.WHITE);
        add(header, BorderLayout.NORTH);

        // MouseListener on header for hover effects
        header.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                try {
                    headerLabel.setText("<html><h1>Employee Records View</h1></html>");
                    header.setBackground(new Color(230, 160, 50));
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null,
                        "Error updating header on mouse enter: " + ex.getMessage(),
                        "Header Hover Error", JOptionPane.ERROR_MESSAGE);
                }
            }

            @Override
            public void mouseExited(MouseEvent e) {
                try {
                    headerLabel.setText("<html><h1>Employee Management System</h1></html>");
                    header.setBackground(new Color(70, 130, 200));
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null,
                        "Error reverting header on mouse exit: " + ex.getMessage(),
                        "Header Hover Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // ===== Sidebar =====
        JPanel sidebar = new JPanel();
        sidebar.setLayout(new BoxLayout(sidebar, BoxLayout.Y_AXIS));
        sidebar.setPreferredSize(new Dimension(200, 0));
        sidebar.setBackground(new Color(240, 240, 240));
        sidebar.setBorder(BorderFactory.createEmptyBorder(20, 10, 20, 10));

        JButton addBtn    = new JButton("Add Employee");
        JButton viewBtn   = new JButton("View Employees");
        JButton updateBtn = new JButton("Update Employee");
        JButton deleteBtn = new JButton("Delete Employee");
        JButton searchBtn = new JButton("Search Employee");

        sidebar.add(Box.createRigidArea(new Dimension(0, 10)));
        sidebar.add(addBtn);
        sidebar.add(Box.createRigidArea(new Dimension(0, 10)));
        sidebar.add(viewBtn);
        sidebar.add(Box.createRigidArea(new Dimension(0, 10)));
        sidebar.add(updateBtn);
        sidebar.add(Box.createRigidArea(new Dimension(0, 10)));
        sidebar.add(deleteBtn);
        sidebar.add(Box.createRigidArea(new Dimension(0, 10)));
        sidebar.add(searchBtn);
        sidebar.add(Box.createVerticalGlue());

        add(sidebar, BorderLayout.WEST);

        // ===== Form Panel =====
        JPanel form = new JPanel();
        form.setLayout(new BoxLayout(form, BoxLayout.Y_AXIS));
        form.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        form.setBackground(Color.WHITE);

        // MouseMotionListener to track mouse position on the form
        
            
        // ===== Name Field =====
        nameField = new JTextField(20);
        nameField.setText("Enter full name");
        nameField.setForeground(Color.GRAY);
        JPanel namePanel = createFieldPanel("Full Name:", nameField);
        form.add(namePanel);
        form.add(Box.createRigidArea(new Dimension(0, 10)));

        // FocusListener on name field
        nameField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                try {
                    if (nameField.getText().equals("Enter full name")) {
                        nameField.setText("");
                        nameField.setForeground(Color.BLACK);
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null,
                        "Error handling name field focus: " + ex.getMessage(),
                        "Focus Error", JOptionPane.ERROR_MESSAGE);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                try {
                    if (nameField.getText().trim().isEmpty()) {
                        nameField.setText("Enter full name");
                        nameField.setForeground(Color.GRAY);
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null,
                        "Error handling name field focus lost: " + ex.getMessage(),
                        "Focus Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // KeyListener on name field — character counter + letters-only validation
        
        // ===== Salary Field =====
        salaryField = new JTextField(20);
        JPanel salaryPanel = createFieldPanel("Salary:", salaryField);
        form.add(salaryPanel);
        form.add(Box.createRigidArea(new Dimension(0, 10)));

        // FocusListener on salary field
        salaryField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                try {
                    salaryField.setForeground(Color.BLACK);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null,
                        "Error focusing salary field: " + ex.getMessage(),
                        "Focus Error", JOptionPane.ERROR_MESSAGE);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                try {
                    String text = salaryField.getText().trim();
                    if (!text.isEmpty()) {
                        double salary = Double.parseDouble(text);
                        if (salary < 0) {
                            throw new IllegalArgumentException("Salary cannot be negative.");
                        }
                    }
                } catch (NumberFormatException ex) {
                    salaryField.setText("");
                    JOptionPane.showMessageDialog(null,
                        "Invalid salary value. Please enter a valid number.",
                        "Salary Format Error", JOptionPane.ERROR_MESSAGE);
                } catch (IllegalArgumentException ex) {
                    salaryField.setText("");
                    JOptionPane.showMessageDialog(null,
                        ex.getMessage(),
                        "Salary Value Error", JOptionPane.ERROR_MESSAGE);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null,
                        "Unexpected error in salary field: " + ex.getMessage(),
                        "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // KeyListener on salary field — digits and dot only
        
        // ===== Employment Type =====
        JPanel employmentPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        employmentPanel.add(new JLabel("Employment Type:"));
        fullTime = new JRadioButton("Full-Time");
        partTime = new JRadioButton("Part-Time");
        contract = new JRadioButton("Contract");
        ButtonGroup empGroup = new ButtonGroup();
        empGroup.add(fullTime);
        empGroup.add(partTime);
        empGroup.add(contract);
        employmentPanel.add(fullTime);
        employmentPanel.add(partTime);
        employmentPanel.add(contract);
        form.add(employmentPanel);
        form.add(Box.createRigidArea(new Dimension(0, 10)));

        // ActionListeners on radio buttons
        ActionListener radioListener = ev -> {
            try {
                String selected = ((JRadioButton) ev.getSource()).getText();
                statusLabel.setText("<html><h3>Status: Type - " + selected + "</h3></html>");
            } catch (ClassCastException ex) {
                JOptionPane.showMessageDialog(null,
                    "Error reading employment type: " + ex.getMessage(),
                    "Radio Button Error", JOptionPane.ERROR_MESSAGE);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null,
                    "Unexpected error on employment type: " + ex.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
            }
        };
        fullTime.addActionListener(radioListener);
        partTime.addActionListener(radioListener);
        contract.addActionListener(radioListener);

        // ===== Benefits =====
        JPanel benefitsPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        benefitsPanel.add(new JLabel("Benefits:"));
        healthInsurance  = new JCheckBox("Health Insurance");
        dentalInsurance  = new JCheckBox("Dental Insurance");
        retirementPlan   = new JCheckBox("Retirement Plan");

        // ActionListeners on checkboxes
        ActionListener checkboxListener = ev -> {
            try {
                JCheckBox src = (JCheckBox) ev.getSource();
                String state = src.isSelected() ? "Added" : "Removed";
                statusLabel.setText("<html><h3>Status: " + src.getText() + " " + state + "</h3></html>");
            } catch (ClassCastException ex) {
                JOptionPane.showMessageDialog(null,
                    "Error reading benefit selection: " + ex.getMessage(),
                    "Checkbox Error", JOptionPane.ERROR_MESSAGE);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null,
                    "Unexpected error on benefit selection: " + ex.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
            }
        };
        healthInsurance.addActionListener(checkboxListener);
        dentalInsurance.addActionListener(checkboxListener);
        retirementPlan.addActionListener(checkboxListener);

        benefitsPanel.add(healthInsurance);
        benefitsPanel.add(dentalInsurance);
        benefitsPanel.add(retirementPlan);
        form.add(benefitsPanel);
        form.add(Box.createRigidArea(new Dimension(0, 10)));

        // ===== Department =====
        JPanel deptPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        deptPanel.add(new JLabel("Department:"));
        String[] departments = {"IT", "HR", "Finance", "Marketing", "Operations"};
        departmentBox = new JComboBox<>(departments);

        departmentBox.addActionListener(ev -> {
            try {
                String selected = departmentBox.getSelectedItem().toString();
                if (selected == null || selected.isEmpty()) {
                    throw new IllegalStateException("No department selected.");
                }
                statusLabel.setText("<html><h3>Status: Dept - " + selected + "</h3></html>");
            } catch (NullPointerException ex) {
                JOptionPane.showMessageDialog(null,
                    "Department selection returned null: " + ex.getMessage(),
                    "Department Error", JOptionPane.ERROR_MESSAGE);
            } catch (IllegalStateException ex) {
                JOptionPane.showMessageDialog(null,
                    ex.getMessage(),
                    "Department Error", JOptionPane.WARNING_MESSAGE);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null,
                    "Error reading department: " + ex.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        deptPanel.add(departmentBox);
        form.add(deptPanel);
        form.add(Box.createRigidArea(new Dimension(0, 10)));

        // ===== Action Buttons =====
        registerBtn = new JButton("Register Employee");
        clearFormBtn = new JButton("Clear Form");
        clearAllBtn  = new JButton("Clear All Records");

        JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));

        registerBtn.setForeground(Color.WHITE);
        registerBtn.setBackground(new Color(70, 130, 200));
        registerBtn.setOpaque(true);
        registerBtn.setBorderPainted(false);
        registerBtn.addActionListener(this);
        registerBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                try {
                    registerBtn.setBackground(Color.BLUE);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null,
                        "Error on register button hover: " + ex.getMessage(),
                        "Button Hover Error", JOptionPane.ERROR_MESSAGE);
                }
            }

            @Override
            public void mouseExited(MouseEvent e) {
                try {
                    registerBtn.setBackground(new Color(70, 130, 200));
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null,
                        "Error reverting register button color: " + ex.getMessage(),
                        "Button Hover Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        clearFormBtn.setForeground(Color.WHITE);
        clearFormBtn.setBackground(Color.RED);
        clearFormBtn.setOpaque(true);
        clearFormBtn.setBorderPainted(false);
        clearFormBtn.addActionListener(ev -> {
            try {
                clearForm();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null,
                    "Error clearing form: " + ex.getMessage(),
                    "Clear Form Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        clearAllBtn.setForeground(Color.BLACK);
        clearAllBtn.setBackground(Color.GREEN);
        clearAllBtn.setOpaque(true);
        clearAllBtn.setBorderPainted(false);
        clearAllBtn.addActionListener(ev -> {
            try {
                clearAllRecords();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null,
                    "Error clearing all records: " + ex.getMessage(),
                    "Clear All Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        btnPanel.add(registerBtn);
        btnPanel.add(clearFormBtn);
        btnPanel.add(clearAllBtn);
        form.add(btnPanel);

        add(form, BorderLayout.CENTER);

        // ===== Display Area =====
        JPanel rightPanel = new JPanel(new BorderLayout());
        rightPanel.setPreferredSize(new Dimension(400, 0));

        displayArea = new JTextArea(20, 30);
        displayArea.setEditable(false);
        displayArea.setWrapStyleWord(true);
        displayArea.setLineWrap(true);
        displayArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
        displayArea.setBackground(new Color(252, 252, 252));
        rightPanel.add(new JScrollPane(displayArea), BorderLayout.CENTER);
        add(rightPanel, BorderLayout.EAST);

        // ===== Footer =====
        JPanel footer = new JPanel(new GridLayout(1, 2));
        footer.setBackground(new Color(60, 60, 60));
        footer.setPreferredSize(new Dimension(0, 60));

        charCountLabel     = new JLabel("<html><h3>Characters: 0</h3></html>");
        mousePositionLabel = new JLabel("<html><h3>Mouse: (0, 0)</h3></html>");
        statusLabel        = new JLabel("<html><h3>Status: Ready</h3></html>");

        charCountLabel.setForeground(Color.WHITE);
        mousePositionLabel.setForeground(Color.WHITE);
        statusLabel.setForeground(Color.WHITE);

        JPanel leftFooter = new JPanel(new FlowLayout(FlowLayout.LEFT));
        leftFooter.setBackground(new Color(60, 60, 60));
        leftFooter.add(charCountLabel);
        leftFooter.add(mousePositionLabel);

        JPanel rightFooter = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        rightFooter.setBackground(new Color(60, 60, 60));
        rightFooter.add(statusLabel);

        footer.add(leftFooter);
        footer.add(rightFooter);
        add(footer, BorderLayout.SOUTH);

        setVisible(true);
    }

    // ===== ActionPerformed =====
    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            if (e.getSource() == registerBtn) {
                handleRegister();
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this,
                "Unexpected error during action: " + ex.getMessage(),
                "Action Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // ===== Handle Register =====
    public void handleRegister() {
        try {
            // ── Validate Name ──
            String name = nameField.getText().trim();
            if (name.isEmpty() || name.equals("Enter full name")) {
                throw new IllegalArgumentException("Full name is required.");
            }
            if (!name.matches("[a-zA-Z ]+")) {
                throw new IllegalArgumentException("Name must contain letters only.");
            }
            if (name.length() < 2) {
                throw new IllegalArgumentException("Name must be at least 2 characters long.");
            }

            
            String employmentType = "";
            if (fullTime.isSelected())       employmentType = fullTime.getText();
            else if (partTime.isSelected())  employmentType = partTime.getText();
            else if (contract.isSelected())  employmentType = contract.getText();

            if (employmentType.isEmpty()) {
                throw new IllegalArgumentException("Please select an employment type.");
            }

            
            String salaryText = salaryField.getText().trim();
            if (salaryText.isEmpty()) {
                throw new IllegalArgumentException("Salary is required.");
            }

            double salary;
            try {
                salary = Double.parseDouble(salaryText);
            } catch (NumberFormatException ex) {
                throw new IllegalArgumentException("Salary must be a valid number. Example: 50000 or 45000.50");
            }

            if (salary < 0) {
                throw new IllegalArgumentException("Salary cannot be negative.");
            }
            if (salary > 10_000_000) {
                throw new IllegalArgumentException("Salary exceeds the maximum allowed value of 10,000,000.");
            }

            
            String department;
            try {
                department = departmentBox.getSelectedItem().toString();
                if (department == null || department.isEmpty()) {
                    throw new IllegalStateException("Please select a department.");
                }
            } catch (NullPointerException ex) {
                throw new IllegalStateException("Department selection failed. Please try again.");
            }

            
            ArrayList<String> benefits = new ArrayList<>();
            try {
                if (healthInsurance.isSelected())  benefits.add(healthInsurance.getText());
                if (dentalInsurance.isSelected())  benefits.add(dentalInsurance.getText());
                if (retirementPlan.isSelected())   benefits.add(retirementPlan.getText());
            } catch (Exception ex) {
                throw new RuntimeException("Error reading benefit selections: " + ex.getMessage());
            }

            
            try {
                String empId = "EMP" + (employees.size() + 1);
                Employee emp = new Employee(name, salary, employmentType, benefits, department);
                employees.add(emp);
                displayArea.append(emp.toString() + "\n");
                statusLabel.setText("<html><h3>Status: Employee Registered</h3></html>");
                JOptionPane.showMessageDialog(this,
                    "Employee registered successfully!\nID: " + empId,
                    "Success", JOptionPane.INFORMATION_MESSAGE);
                clearForm();
            } catch (Exception ex) {
                throw new RuntimeException("Failed to register employee: " + ex.getMessage());
            }

        } catch (IllegalArgumentException ex) {
            // Validation errors — show friendly message
            JOptionPane.showMessageDialog(this,
                ex.getMessage(),
                "Validation Error", JOptionPane.WARNING_MESSAGE);
        } catch (IllegalStateException ex) {
            JOptionPane.showMessageDialog(this,
                ex.getMessage(),
                "State Error", JOptionPane.WARNING_MESSAGE);
        } catch (RuntimeException ex) {
            JOptionPane.showMessageDialog(this,
                ex.getMessage(),
                "Runtime Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this,
                "Unexpected error during registration: " + ex.getMessage(),
                "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // ===== Clear Form =====
    public void clearForm() {
        try {
            nameField.setText("Enter full name");
            nameField.setForeground(Color.GRAY);
            salaryField.setText("");
            fullTime.setSelected(false);
            partTime.setSelected(false);
            contract.setSelected(false);
            healthInsurance.setSelected(false);
            dentalInsurance.setSelected(false);
            retirementPlan.setSelected(false);
            departmentBox.setSelectedIndex(0);
            charCountLabel.setText("<html><h3>Characters: 0</h3></html>");
            statusLabel.setText("<html><h3>Status: Form Cleared</h3></html>");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this,
                "Error while clearing the form: " + ex.getMessage(),
                "Clear Form Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // ===== Clear All Records =====
    public void clearAllRecords() {
        try {
            int confirm = JOptionPane.showConfirmDialog(this,
                "Are you sure you want to clear ALL employee records?",
                "Confirm Clear All", JOptionPane.YES_NO_OPTION);

            if (confirm == JOptionPane.YES_OPTION) {
                employees.clear();
                displayArea.setText("");
                statusLabel.setText("<html><h3>Status: All Records Cleared</h3></html>");
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this,
                "Error while clearing records: " + ex.getMessage(),
                "Clear Records Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // ===== Helper: Create Field Panel =====
    private JPanel createFieldPanel(String label, JTextField field) {
        try {
            JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
            JLabel jlabel = new JLabel(label);
            jlabel.setPreferredSize(new Dimension(120, 25));
            panel.add(jlabel);
            field.setPreferredSize(new Dimension(300, 30));
            panel.add(field);
            panel.setBackground(Color.WHITE);
            return panel;
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null,
                "Error creating field panel for '" + label + "': " + ex.getMessage(),
                "UI Error", JOptionPane.ERROR_MESSAGE);
            return new JPanel(); // return empty panel as fallback
        }
    }

    public static void main(String[] args) {
        try {
            SwingUtilities.invokeLater(() -> {
                try {
                    new EmployeeManagementTemplate();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null,
                        "Failed to launch application: " + ex.getMessage(),
                        "Launch Error", JOptionPane.ERROR_MESSAGE);
                    ex.printStackTrace();
                }
            });
        } catch (Exception ex) {
            System.err.println("Critical error launching app: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
}