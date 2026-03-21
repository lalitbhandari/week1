package week1.week20;


import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.*;

public class TodoApp extends JFrame implements ActionListener
{
    private JPanel titleBar,footer,sidebar,centerPanel,inputRow,rightPanel,row;
    private JLabel titleLabel, footerLabel,taskLabel,nameLabel;
    private JTextField taskField;
    private JButton btn,addBtn,doneBtn,clearAllBtn,deleteBtn;
    private JCheckBox cb;
    
    static class Task
    {
        String name;
        String priority;
        boolean completed;
        Task(String name, String priority)
        {
            this.name = name;
            this.priority = priority;
            this.completed = false;
        }
    }
    private final List<Task> tasks = new ArrayList<>();
    private String viewFilter = "All";
    private JPanel taskListPanel;
    private JScrollPane scrollPane;
    public static void main(String[] args)
    {
        SwingUtilities.invokeLater(TodoApp::new);
    }
    public TodoApp()
    {

        tasks.add(new Task("Finish Java Assignment", "High"));
        tasks.add(new Task("Prepare Networking Notes", "Medium"));

        JFrame frame = new JFrame("My ToDo Manager");
        frame.setSize(700, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);frame.setLayout(new BorderLayout());
        titleBar = new JPanel(new FlowLayout(FlowLayout.CENTER));
        titleBar.setBackground(new Color(173, 216, 230));
        titleLabel = new JLabel("My ToDo Manager");
        titleLabel.setFont(new Font("SansSerif", Font.BOLD, 24));
        titleBar.add(titleLabel);
        frame.add(titleBar, BorderLayout.NORTH);
        footer = new JPanel(new FlowLayout(FlowLayout.CENTER));

        footer.setBackground(new Color(220, 220, 220));
        footerLabel = new JLabel("Developed using Java Swing");
        footerLabel.setFont(new Font("SansSerif", Font.BOLD, 13));
        footer.add(footerLabel);
        frame.add(footer, BorderLayout.SOUTH);

        sidebar = new JPanel();
        sidebar.setLayout(new BoxLayout(sidebar, BoxLayout.Y_AXIS));
        sidebar.setBackground(new Color(200, 200, 200));
        sidebar.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        String[] filterNames = {"All Tasks", "Completed", "Pending", "Important"};
        for (String f : filterNames)
        {
            btn = new JButton(f);
            btn.setMaximumSize(new Dimension(110, 32));
        
            btn.setPreferredSize(new Dimension(110, 32));
            btn.setAlignmentX(Component.LEFT_ALIGNMENT);
    
            String key = f.equals("All Tasks") ? "All" : f;
    
        
            btn.addActionListener(e -> { viewFilter = key; refreshTaskList(); });
            sidebar.add(btn);
            sidebar.add(Box.createVerticalStrut(4));
        }
        frame.add(sidebar, BorderLayout.WEST);

        centerPanel = new JPanel(new BorderLayout());

        centerPanel.setBackground(new Color(230, 230, 230));

        inputRow = new JPanel(new FlowLayout(FlowLayout.CENTER, 8, 6));
        inputRow.setBackground(new Color(230, 230, 230));

        taskLabel = new JLabel("Task:");
        taskLabel.setFont(new Font("SansSerif", Font.PLAIN, 13));

        taskField = new JTextField(20);
        taskField.setPreferredSize(new Dimension(200, 26));

        JComboBox<String> priorityBox = new JComboBox<>(new String[]{"High", "Medium","Low"});
        priorityBox.setPreferredSize(new Dimension(90, 26));

        addBtn = new JButton("Add Task");
        addBtn.setBackground(Color.GREEN);
        addBtn.setForeground(Color.BLACK);
        addBtn.setFont(new Font("SansSerif", Font.BOLD, 13));
        addBtn.setOpaque(true);
        addBtn.setBorderPainted(false);
        addBtn.setPreferredSize(new Dimension(100, 28));

        addBtn.addActionListener(this);

        taskField.addActionListener(e -> addBtn.doClick());

        inputRow.add(taskLabel);
        inputRow.add(taskField);
        inputRow.add(priorityBox);
        inputRow.add(addBtn);
        centerPanel.add(inputRow, BorderLayout.NORTH);

        taskListPanel = new JPanel();
        taskListPanel.setLayout(new BoxLayout(taskListPanel, BoxLayout.Y_AXIS));
        taskListPanel.setBackground(new Color(230, 230, 230));
        taskListPanel.setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));

        scrollPane = new JScrollPane(taskListPanel);
        scrollPane.getViewport().setBackground(new Color(230, 230, 230));
        scrollPane.setBorder(BorderFactory.createLineBorder(new Color(180, 180, 180)));
        centerPanel.add(scrollPane, BorderLayout.CENTER);

        rightPanel = new JPanel();
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
        rightPanel.setBackground(new Color(200, 200, 200));
        rightPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        doneBtn = new JButton("Done");
        doneBtn.setMaximumSize(new Dimension(110, 32));
        doneBtn.setPreferredSize(new Dimension(110, 32));
        doneBtn.setBackground(Color.RED);
        doneBtn.setForeground(Color.WHITE);
        doneBtn.setOpaque(true);
        doneBtn.setBorderPainted(false);
        doneBtn.setAlignmentX(Component.LEFT_ALIGNMENT);

        clearAllBtn = new JButton("Clear All");
        clearAllBtn.setMaximumSize(new Dimension(110, 32));
        clearAllBtn.setPreferredSize(new Dimension(110, 32));
        clearAllBtn.setBackground(Color.GREEN);
        clearAllBtn.setForeground(Color.BLACK);
        clearAllBtn.setOpaque(true);
        clearAllBtn.setBorderPainted(false);
        clearAllBtn.setAlignmentX(Component.LEFT_ALIGNMENT);

        rightPanel.add(doneBtn);
        rightPanel.add(Box.createVerticalStrut(4));
        rightPanel.add(clearAllBtn);

        frame.add(rightPanel, BorderLayout.EAST);
        
        doneBtn.addActionListener(e -> {
        for (Task task : tasks)
        {
        task.completed = true;
        }
        refreshTaskList();
        });
        
        clearAllBtn.addActionListener(e -> {
        int confirm = JOptionPane.showConfirmDialog(
        null,
        "Are you sure you want to delete all tasks?",
        "Confirm",
        JOptionPane.YES_NO_OPTION
            );
        if (confirm == JOptionPane.YES_OPTION)
        {
        tasks.clear();
        refreshTaskList();
        }
        });
        
        
        frame.add(centerPanel, BorderLayout.CENTER);
    
        refreshTaskList();

        frame.setLocationRelativeTo(null);
    
        frame.setVisible(true);
    }

    private void refreshTaskList()
    {
        taskListPanel.removeAll();

    
        for (Task task : tasks)
        {
            if (viewFilter.equals("Completed") && !task.completed) continue;
            if (viewFilter.equals("Pending") && task.completed) continue;
            if (viewFilter.equals("Important") && !task.priority.equals("High")) continue;

            row = new JPanel(new FlowLayout(FlowLayout.LEFT, 6, 4));
        
            row.setBackground(new Color(230, 230, 230));
            row.setMaximumSize(new Dimension(Integer.MAX_VALUE, 35));
        
            row.setAlignmentX(Component.LEFT_ALIGNMENT);

            cb = new JCheckBox();
        
            cb.setBackground(row.getBackground());
            cb.setSelected(task.completed);
            cb.addActionListener(e -> { task.completed = cb.isSelected(); refreshTaskList(); });

            String display = task.completed

            ? "<html><strike>" + task.name + "</strike></html>"
            : task.name;
            nameLabel = new JLabel(display);
            nameLabel.setFont(new Font("SansSerif", Font.BOLD, 13));

            deleteBtn = new JButton("Delete");
            deleteBtn.setBackground(new Color(255, 150, 150));
            deleteBtn.setOpaque(true);
            deleteBtn.setBorderPainted(false);
            deleteBtn.setFont(new Font("SansSerif", Font.PLAIN, 12));
            deleteBtn.addActionListener(e -> { tasks.remove(task); refreshTaskList(); });

            row.add(cb);
            row.add(nameLabel);
            row.add(deleteBtn);

            taskListPanel.add(row);
            taskListPanel.add(Box.createVerticalStrut(60));
        }

        taskListPanel.revalidate();
        taskListPanel.repaint();
    }
    
    
    @Override
    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource()== addBtn)
        {
            validate();
        }
    }
    
    public void validate()
    {
    if (taskField.getText().trim().isEmpty())
    {
        JOptionPane.showMessageDialog(null, "Enter a task!", "Message", JOptionPane.INFORMATION_MESSAGE);
    }
    else
    {
        addTask();
    }
    }
    public void addTask()
    {
        String name = taskField.getText().trim();
        tasks.add(new Task(name, "High")); 
        taskField.setText("");
        refreshTaskList();
    }
}