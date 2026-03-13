package week1.week18;


import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class ScrollableTableDemo extends JFrame {

    public ScrollableTableDemo() {
        setTitle("JScrollPane Demo - Student Records");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 300);                  
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // ── Column Headers
        String[] columns = {"ID", "Name", "Age", "Department", "Phone"};

        
        Object[][] data = {
            {1,  "Alice Sharma",   21, "Computer Science", "9801234567"},
            {2,  "Bob Thapa",      22, "Engineering",      "9807654321"},
            {3,  "Carol Poudel",   20, "Business",         "9812345678"},
            {4,  "David Rai",      23, "Medicine",         "9845678901"},
            {5,  "Eva Gurung",     21, "Architecture",     "9856789012"},
            {6,  "Frank Basnet",   24, "Law",              "9823456789"},
            {7,  "Grace Shrestha", 22, "Psychology",       "9834567890"},
            {8,  "Henry Magar",    25, "Economics",        "9867890123"},
            {9,  "Iris Tamang",    20, "Biology",          "9878901234"},
            {10, "Jake Limbu",     23, "Chemistry",        "9889012345"},
            {11, "Karen Karki",    21, "Arts",             "9890123456"},
            {12, "Leo Pandey",     22, "Computer Science", "9801122334"},
        };

        // ── Non-editable TableModel ───────────────────────────────
        DefaultTableModel model = new DefaultTableModel(data, columns) {
            @Override
            public boolean isCellEditable(int row, int col) {
                return false;
            }
        };

        JTable table = new JTable(model);
        table.setRowHeight(28);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        
        JScrollPane scrollPane = new JScrollPane(table);

        // ── Optional: Configure scroll behaviour 
        scrollPane.setVerticalScrollBarPolicy(
            JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);   
        scrollPane.setHorizontalScrollBarPolicy(
            JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);   

        // ── Optional: Set preferred viewport size
        table.setPreferredScrollableViewportSize(
            new Dimension(560, 200));  

        // ── Add ScrollPane (NOT the table) to the frame
        add(scrollPane, BorderLayout.CENTER);

        // ── Status label 
        JLabel statusLabel = new JLabel(
            "  ✅ Scroll down to see all " + data.length + " records",
            SwingConstants.LEFT);
        statusLabel.setForeground(new Color(0, 120, 0));
        statusLabel.setFont(new Font("Arial", Font.ITALIC, 12));
        add(statusLabel, BorderLayout.SOUTH);

        setVisible(true);
    }

    public static void main(String[] args) {
        new ScrollableTableDemo();
    }
}
