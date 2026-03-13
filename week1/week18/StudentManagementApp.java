package week1.week18;


import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class StudentManagementApp extends JFrame {

    // ── Form Fields 
    private JTextField      idField, nameField, searchField;
    private JComboBox<String> courseBox;
    private JRadioButton    maleBtn, femaleBtn, otherBtn;
    private ButtonGroup     genderGroup;
    private JCheckBox       javaCheck, pythonCheck, webCheck, sqlCheck;

    // ── Table 
    private JTable          studentTable;
    private DefaultTableModel tableModel;

    // ── Buttons 
    private JButton submitBtn, resetBtn, searchBtn, deleteBtn;

    public StudentManagementApp() {
        setTitle("Department Administration — Student Registration");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 620);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        // ── Seed some sample data 
        Student.addStudent(new Student(101, "Alice Sharma",  "Female", "Java",   "BSc"));
        Student.addStudent(new Student(102, "Bob Thapa",     "Male",   "Python", "BIT"));
        Student.addStudent(new Student(103, "Carol Poudel",  "Female", "Web",    "BBA"));

        add(buildFormPanel(),  BorderLayout.NORTH);
        add(buildTablePanel(), BorderLayout.CENTER);

        setVisible(true);
    }

    
    private JPanel buildFormPanel() {
        JPanel form = new JPanel(new GridBagLayout());
        form.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(new Color(70, 130, 180), 2),
            "Student Registration Form",
            javax.swing.border.TitledBorder.LEFT,
            javax.swing.border.TitledBorder.TOP,
            new Font("Arial", Font.BOLD, 13),
            new Color(70, 130, 180)));

        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(6, 10, 6, 10);
        c.fill   = GridBagConstraints.HORIZONTAL;

        // ── Row 0: Student ID 
        c.gridx = 0; c.gridy = 0;
        form.add(new JLabel("Student ID:"), c);
        c.gridx = 1;
        idField = new JTextField(12);
        idField.setToolTipText("Enter unique student ID (e.g. 101)");
        form.add(idField, c);

        // ── Row 0: Student Name 
        c.gridx = 2;
        form.add(new JLabel("Student Name:"), c);
        c.gridx = 3;
        nameField = new JTextField(12);
        nameField.setToolTipText("Enter full student name");
        form.add(nameField, c);

        // ── Row 1: Course 
        c.gridx = 0; c.gridy = 1;
        form.add(new JLabel("Course:"), c);
        c.gridx = 1;
        courseBox = new JComboBox<>(new String[]{"BSc", "BBA", "BIT", "BCA", "MBA"});
        courseBox.setToolTipText("Select the enrolled course");
        form.add(courseBox, c);

        // ── Row 1: Gender
        c.gridx = 2;
        form.add(new JLabel("Gender:"), c);
        c.gridx = 3;
        JPanel genderPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));
        maleBtn   = new JRadioButton("Male");
        femaleBtn = new JRadioButton("Female");
        otherBtn  = new JRadioButton("Other");
        maleBtn.setSelected(true);                  // default selection
        genderGroup = new ButtonGroup();
        genderGroup.add(maleBtn);
        genderGroup.add(femaleBtn);
        genderGroup.add(otherBtn);
        genderPanel.add(maleBtn);
        genderPanel.add(femaleBtn);
        genderPanel.add(otherBtn);
        form.add(genderPanel, c);

        // ── Row 2: Skills (multi-select checkboxes) 
        c.gridx = 0; c.gridy = 2;
        form.add(new JLabel("Skills:"), c);
        c.gridx = 1; c.gridwidth = 3;
        JPanel skillPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 0));
        javaCheck   = new JCheckBox("Java");
        pythonCheck = new JCheckBox("Python");
        webCheck    = new JCheckBox("Web Dev");
        sqlCheck    = new JCheckBox("SQL");
        skillPanel.add(javaCheck);
        skillPanel.add(pythonCheck);
        skillPanel.add(webCheck);
        skillPanel.add(sqlCheck);
        form.add(skillPanel, c);
        c.gridwidth = 1;

        // ── Row 3: Search field + Buttons 
        c.gridx = 0; c.gridy = 3;
        form.add(new JLabel("Search by ID:"), c);
        c.gridx = 1;
        searchField = new JTextField(12);
        searchField.setToolTipText("Enter Student ID to search or delete");
        form.add(searchField, c);

        // ── Buttons Panel 
        c.gridx = 2; c.gridwidth = 2;
        form.add(buildButtonPanel(), c);

        return form;
    }

    
    private JPanel buildButtonPanel() {
        JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 0));

        // Submit / Add
        submitBtn = new JButton("➕ Submit");
        submitBtn.setMnemonic('S');
        submitBtn.setToolTipText("Add student record (ALT+S)");
        submitBtn.setBackground(new Color(60, 160, 60));
        submitBtn.setForeground(Color.WHITE);
        submitBtn.setFont(new Font("Arial", Font.BOLD, 12));
        submitBtn.addActionListener(e -> handleSubmit());

        // Reset
        resetBtn = new JButton("🔄 Reset");
        resetBtn.setMnemonic('R');
        resetBtn.setToolTipText("Clear all fields (ALT+R)");
        resetBtn.setBackground(new Color(100, 100, 200));
        resetBtn.setForeground(Color.WHITE);
        resetBtn.addActionListener(e -> handleReset());

        // Search
        searchBtn = new JButton("🔍 Search");
        searchBtn.setMnemonic('F');
        searchBtn.setToolTipText("Find student by ID (ALT+F)");
        searchBtn.setBackground(new Color(200, 140, 30));
        searchBtn.setForeground(Color.WHITE);
        searchBtn.addActionListener(e -> handleSearch());

        // Delete
        deleteBtn = new JButton("🗑 Delete");
        deleteBtn.setMnemonic('D');
        deleteBtn.setToolTipText("Delete student by ID (ALT+D)");
        deleteBtn.setBackground(new Color(200, 60, 60));
        deleteBtn.setForeground(Color.WHITE);
        deleteBtn.addActionListener(e -> handleDelete());

        btnPanel.add(submitBtn);
        btnPanel.add(resetBtn);
        btnPanel.add(searchBtn);
        btnPanel.add(deleteBtn);
        return btnPanel;
    }

   
    private JPanel buildTablePanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(new Color(70, 130, 180), 2),
            "Registered Students",
            javax.swing.border.TitledBorder.LEFT,
            javax.swing.border.TitledBorder.TOP,
            new Font("Arial", Font.BOLD, 13),
            new Color(70, 130, 180)));

        String[] columns = {"ID", "Name", "Gender", "Skills", "Course"};
        tableModel = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int col) { return false; }
        };

        studentTable = new JTable(tableModel);
        studentTable.setRowHeight(28);
        studentTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        studentTable.getTableHeader().setBackground(new Color(70, 130, 180));
        studentTable.getTableHeader().setForeground(Color.WHITE);
        studentTable.getTableHeader().setFont(new Font("Arial", Font.BOLD, 12));

        // Populate from Student.studentList
        refreshTable();

        panel.add(new JScrollPane(studentTable), BorderLayout.CENTER);
        return panel;
    }

   

    private void handleSubmit() {
        String idText = idField.getText().trim();
        String name   = nameField.getText().trim();

        // Presence check
        if (idText.isEmpty() || name.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                "❌ Student ID and Name are required.",
                "Validation Error", JOptionPane.WARNING_MESSAGE);
            return;
        }
        // Type check
        int id;
        try {
            id = Integer.parseInt(idText);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this,
                "❌ Student ID must be a number.",
                "Validation Error", JOptionPane.WARNING_MESSAGE);
            return;
        }

        String gender = maleBtn.isSelected() ? "Male"
                      : femaleBtn.isSelected() ? "Female" : "Other";

        // Collect checked skills
        StringBuilder skills = new StringBuilder();
        if (javaCheck.isSelected())   skills.append("Java ");
        if (pythonCheck.isSelected()) skills.append("Python ");
        if (webCheck.isSelected())    skills.append("Web ");
        if (sqlCheck.isSelected())    skills.append("SQL");
        if (skills.length() == 0)     skills.append("None");

        String course = (String) courseBox.getSelectedItem();

        Student s = new Student(id, name, gender, skills.toString().trim(), course);
        boolean added = Student.addStudent(s);

        if (added) {
            refreshTable();
            handleReset();
            JOptionPane.showMessageDialog(this,
                "✅ Student added successfully!",
                "Success", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this,
                "❌ Student ID " + id + " already exists. Use a unique ID.",
                "Duplicate ID", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void handleReset() {
        idField.setText("");
        nameField.setText("");
        searchField.setText("");
        courseBox.setSelectedIndex(0);
        maleBtn.setSelected(true);
        javaCheck.setSelected(false);
        pythonCheck.setSelected(false);
        webCheck.setSelected(false);
        sqlCheck.setSelected(false);
    }

    private void handleSearch() {
        String idText = searchField.getText().trim();
        if (idText.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                "⚠️ Enter a Student ID in the Search field.",
                "Search", JOptionPane.WARNING_MESSAGE);
            return;
        }
        try {
            int searchId = Integer.parseInt(idText);
            for (Student s : Student.getStudentList()) {
                if (s.getId() == searchId) {
                    // Highlight matching row in table
                    for (int row = 0; row < tableModel.getRowCount(); row++) {
                        if ((int) tableModel.getValueAt(row, 0) == searchId) {
                            studentTable.setRowSelectionInterval(row, row);
                            studentTable.scrollRectToVisible(
                                studentTable.getCellRect(row, 0, true));
                        }
                    }
                    JOptionPane.showMessageDialog(this,
                        "✅ Found: " + s.getName() + " | " + s.getCourse()
                        + " | " + s.getGender(),
                        "Student Found", JOptionPane.INFORMATION_MESSAGE);
                    return;
                }
            }
            JOptionPane.showMessageDialog(this,
                "❌ No student found with ID: " + searchId,
                "Not Found", JOptionPane.WARNING_MESSAGE);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this,
                "❌ ID must be a number.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void handleDelete() {
        String idText = searchField.getText().trim();
        if (idText.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                "⚠️ Enter a Student ID in the Search field to delete.",
                "Delete", JOptionPane.WARNING_MESSAGE);
            return;
        }
        try {
            int deleteId = Integer.parseInt(idText);
            int confirm  = JOptionPane.showConfirmDialog(this,
                "Are you sure you want to delete student ID: " + deleteId + "?",
                "Confirm Delete", JOptionPane.YES_NO_OPTION);

            if (confirm == JOptionPane.YES_OPTION) {
                boolean deleted = Student.deleteStudent(deleteId);
                if (deleted) {
                    refreshTable();
                    searchField.setText("");
                    JOptionPane.showMessageDialog(this,
                        "✅ Student deleted successfully.",
                        "Deleted", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(this,
                        "❌ No student found with ID: " + deleteId,
                        "Not Found", JOptionPane.WARNING_MESSAGE);
                }
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this,
                "❌ ID must be a number.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // ── Reload table from Student.studentList ─────────────────────
    private void refreshTable() {
        tableModel.setRowCount(0); // clear existing rows
        for (Student s : Student.getStudentList()) {
            tableModel.addRow(new Object[]{
                s.getId(), s.getName(), s.getGender(),
                s.getSkill(), s.getCourse()
            });
        }
    }

    public static void main(String[] args) {
        new StudentManagementApp();
    }
}
 