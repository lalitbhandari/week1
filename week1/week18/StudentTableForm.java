package week1.week18;


import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;

public class StudentTableForm extends JFrame {

    private JTable studentTable;
    private DefaultTableModel tableModel;

    public StudentTableForm() {
        setTitle("Student Records Table");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        // ============================================================
        // PART 1: Define Column Headers & Row Data (2D Object Array)
        // ============================================================

        // Column headers
        String[] columns = {"ID", "Name", "Age", "Department", "Phone"};

        // Row data — 2D Object array (Object allows mixed types)
        Object[][] data = {
            {1, "Alice Sharma",   21, "Computer Science", "9801234567"},
            {2, "Bob Thapa",      22, "Engineering",      "9807654321"},
            {3, "Carol Poudel",   20, "Business",         "9812345678"},
            {4, "David Rai",      23, "Medicine",         "9845678901"},
            {5, "Eva Gurung",     21, "Architecture",     "9856789012"},
        };

        // ── Create NON-EDITABLE TableModel ───────────────────────
        // Override isCellEditable() to block all editing
        tableModel = new DefaultTableModel(data, columns) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;   // ✅ Makes entire table NON-EDITABLE
            }
        };

        // ============================================================
        // PART 2: Create & Configure the JTable
        // ============================================================
        studentTable = new JTable(tableModel);

        // ── Row Height ───────────────────────────────────────────
        studentTable.setRowHeight(30);                          // pixels per row

        // ── Single Row Selection Mode ────────────────────────────
        studentTable.setSelectionMode(
            ListSelectionModel.SINGLE_SELECTION);               // only 1 row at a time

        // ── Column Headers are visible by default via JScrollPane ─
        studentTable.getTableHeader().setBackground(new Color(70, 130, 180));
        studentTable.getTableHeader().setForeground(Color.WHITE);
        studentTable.getTableHeader().setFont(
            new Font("Arial", Font.BOLD, 13));

        // ── Alternating row colors for readability ───────────────
        studentTable.setDefaultRenderer(Object.class,
            new javax.swing.table.DefaultTableCellRenderer() {
                @Override
                public Component getTableCellRendererComponent(
                        JTable table, Object value, boolean isSelected,
                        boolean hasFocus, int row, int col) {
                    super.getTableCellRendererComponent(
                        table, value, isSelected, hasFocus, row, col);
                    if (!isSelected) {
                        setBackground(row % 2 == 0
                            ? Color.WHITE
                            : new Color(220, 235, 255)); // light blue stripe
                    }
                    return this;
                }
            });

        // ── Wrap in JScrollPane (also shows column headers!) ─────
        JScrollPane scrollPane = new JScrollPane(studentTable);
        add(scrollPane, BorderLayout.CENTER);

        // ============================================================
        // PART 3: Retrieve Selected Row SAFELY
        // ============================================================
        JButton viewBtn = new JButton("View Selected Student");
        viewBtn.addActionListener(e -> retrieveSelectedRow());

        JPanel bottomPanel = new JPanel();
        bottomPanel.add(viewBtn);
        add(bottomPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    // ============================================================
    //  SAFE ROW RETRIEVAL — always check getSelectedRow() != -1
    // ============================================================
    private void retrieveSelectedRow() {
        int selectedRow = studentTable.getSelectedRow(); // returns -1 if none selected

        // ── Safety Check ─────────────────────────────────────────
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this,
                "⚠️ No row selected. Please click a student first.",
                "No Selection", JOptionPane.WARNING_MESSAGE);
            return; // exit early — avoid index out of bounds
        }

        // ── Safely retrieve each column value ────────────────────
        int    id         = (int)    tableModel.getValueAt(selectedRow, 0);
        String name       = (String) tableModel.getValueAt(selectedRow, 1);
        int    age        = (int)    tableModel.getValueAt(selectedRow, 2);
        String department = (String) tableModel.getValueAt(selectedRow, 3);
        String phone      = (String) tableModel.getValueAt(selectedRow, 4);

        // ── Display retrieved data ────────────────────────────────
        JOptionPane.showMessageDialog(this,
            "✅ Selected Student Details:\n\n" +
            "ID         : " + id         + "\n" +
            "Name       : " + name       + "\n" +
            "Age        : " + age        + "\n" +
            "Department : " + department + "\n" +
            "Phone      : " + phone,
            "Student Info", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void main(String[] args) {
        new StudentTableForm();
    }
}