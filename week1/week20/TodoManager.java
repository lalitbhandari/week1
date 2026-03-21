package week1.week20;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.event.ActionListener;

public class TodoManager extends JFrame {

    // Instance Variables
    private JTextField taskField;
    private JComboBox<String> priorityBox;
    private JPanel taskPanel;

    private JLabel title;
    private JPanel topPanel, leftPanel, downPanel;
    private JButton addBtn;
    private JButton allBtn, completedBtn, pendingBtn, importantBtn, clearBtn, doneBtn;

    public TodoManager() {
        setTitle("My ToDo Manager");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Title
        title = new JLabel("My ToDo Manager", JLabel.CENTER);
        add(title, BorderLayout.NORTH);

        // Top Panel (Input)
        topPanel = new JPanel();
        topPanel.add(new JLabel("Task:"));

        taskField = new JTextField(20);
        topPanel.add(taskField);

        String[] priorities = {"High", "Medium", "Low"};
        priorityBox = new JComboBox<>(priorities);
        topPanel.add(priorityBox);

        addBtn = new JButton("Add Task");
        addBtn.setBackground(Color.GREEN);
        topPanel.add(addBtn);

        add(topPanel, BorderLayout.SOUTH);

        // Center Panel (Tasks)
        taskPanel = new JPanel();
        taskPanel.setLayout(new BoxLayout(taskPanel, BoxLayout.Y_AXIS));
        add(new JScrollPane(taskPanel), BorderLayout.CENTER);

        

        // Left Panel (Menu)
        leftPanel = new JPanel();
        leftPanel.setLayout(new GridLayout(4,1));
       
        allBtn = new JButton("All Tasks");
        completedBtn = new JButton("Completed");
        pendingBtn = new JButton("Pending");
        importantBtn = new JButton("Important");
        clearBtn = new JButton("Clear");
        doneBtn= new JButton("Done");
        
        downPanel = new JPanel();
        downPanel.add(clearBtn);
        downPanel.add(doneBtn);
        add(downPanel, BorderLayout.EAST);
        
        

        leftPanel.add(allBtn);
        leftPanel.add(completedBtn);
        leftPanel.add(pendingBtn);
        leftPanel.add(importantBtn);

        add(leftPanel, BorderLayout.WEST);

        // Button Action
        clearBtn.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                taskPanel.removeAll();
                taskPanel.revalidate();
                taskPanel.repaint();
            }
        });
        addBtn.addActionListener(new ActionListener() {
    public void actionPerformed(ActionEvent e) {
        String text = taskField.getText().trim();
        if (!text.isEmpty()) {
            addTask(text);
            taskField.setText("");
        }
    }
});

        setVisible(true);
    }

    // Add Task UI
    private void addTask(String taskText) {
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout(FlowLayout.LEFT));

        JCheckBox checkBox = new JCheckBox(taskText);
        panel.add(checkBox);

        JButton deleteBtn = new JButton("Delete");
        deleteBtn.setBackground(Color.PINK);
        panel.add(deleteBtn);

        // Delete action
        deleteBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                taskPanel.remove(panel);
                taskPanel.revalidate();
                taskPanel.repaint();
            }
        });

        taskPanel.add(panel);
        taskPanel.revalidate();
        taskPanel.repaint();
    }

    public static void main(String[] args) {
        new TodoManager();
    }
}