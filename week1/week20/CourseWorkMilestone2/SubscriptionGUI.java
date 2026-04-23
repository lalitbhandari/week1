package week1.week20.CourseWorkMilestone2;                    

import javax.swing.*;                     // GUI (JFrame, JButton, etc.)
import javax.swing.border.*;              //  EmptyBorder, TitledBorder
import java.awt.*;                        //  layout, colors, fonts, etc.
import java.awt.event.*;                  // event handling classes ActionListener
import java.util.ArrayList;               //  ArrayList

/**
 * SubscriptionGUI - Main GUI class for managing AI model subscription plans.
 */
public class SubscriptionGUI extends JFrame {  

    private ArrayList<AIModel> plans = new ArrayList<>();   // List to store all AI subscription plans (Personal + Pro)

    // Input Fields
    private JTextField txtModelName, txtPrice, txtParams, txtContext, txtExtra;   
    private JLabel lblExtra;                                                   
    // Action Fields
    private JTextField txtActionIndex, txtActionValue;   

    // Output
    private JTextArea outputArea;   // Text area to display results and messages

    public SubscriptionGUI() {      // Constructor 
        setTitle("AI Subscription Manager - Lalit Tech");          
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);            
        setSize(860, 740);                                         
        setLocationRelativeTo(null);                               
        setResizable(false);                                      
        buildUI();                                                 
        setVisible(true);                                          // Makes the window visible
    }

    private void buildUI() {                                       //  entire user interface
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));   // Creates main container panel with spacing
        mainPanel.setBorder(new EmptyBorder(12, 12, 12, 12));      // Adds padding around the edges
        mainPanel.setBackground(new Color(240, 245, 255));         // Sets light blue background color

        JLabel title = new JLabel("AI Model Subscription Manager", SwingConstants.CENTER);  
        title.setFont(new Font("Arial", Font.BOLD, 22));           
        title.setForeground(new Color(30, 60, 120));              
        mainPanel.add(title, BorderLayout.NORTH);                  

        JPanel centerPanel = new JPanel(new GridLayout(1, 2, 15, 0));  
        centerPanel.setBackground(new Color(240, 245, 255));           
        centerPanel.add(buildInputPanel());                            
        centerPanel.add(buildActionPanel());                           
        mainPanel.add(centerPanel, BorderLayout.CENTER);               

        mainPanel.add(buildOutputPanel(), BorderLayout.SOUTH);        
        add(mainPanel);                                               
    }

    private JPanel buildInputPanel() {                                 // Builds the left panel for adding new plans
        JPanel panel = new JPanel(new GridBagLayout());                
        panel.setBorder(BorderFactory.createTitledBorder(              
            BorderFactory.createLineBorder(new Color(100, 140, 200), 2),
            "Add New Plan", TitledBorder.LEFT, TitledBorder.TOP,
            new Font("Arial", Font.BOLD, 14), new Color(30, 60, 120)));
        panel.setBackground(Color.WHITE);                             

        GridBagConstraints gbc = new GridBagConstraints();             
        gbc.insets = new Insets(6, 10, 6, 10);                         
        gbc.fill = GridBagConstraints.HORIZONTAL;                     
        gbc.weightx = 1.0;                                            

        txtModelName = addField(panel, gbc, "Model Name:", 0);         
        txtPrice = addField(panel, gbc, "Price (NRS/1L tokens):", 1);
        txtParams = addField(panel, gbc, "Parameters (Billions):", 2);
        txtContext = addField(panel, gbc, "Context Window (e.g. 64K):", 3);

        lblExtra = new JLabel("Monthly Prompts (Personal):");          
        lblExtra.setFont(new Font("Arial", Font.PLAIN, 13));          
        gbc.gridx = 0; gbc.gridy = 4;                                  
        panel.add(lblExtra, gbc);

        txtExtra = new JTextField(15);                               
        txtExtra.setFont(new Font("Arial", Font.PLAIN, 13));
        gbc.gridx = 1; gbc.gridy = 4;
        panel.add(txtExtra, gbc);

        // Create buttons
        JButton btnPersonal = makeButton("Add Personal Plan", new Color(50, 130, 200));
        JButton btnPro = makeButton("Add Pro Plan", new Color(70, 160, 90));
        JButton btnDisplay = makeButton("Display All Plans", new Color(180, 100, 30));
        JButton btnClear = makeButton("Clear Fields", new Color(190, 50, 50));

        gbc.gridx = 0; gbc.gridy = 5; gbc.gridwidth = 2;              // Buttons span both columns
        panel.add(btnPersonal, gbc);
        gbc.gridy = 6; panel.add(btnPro, gbc);
        gbc.gridy = 7; panel.add(btnDisplay, gbc);
        gbc.gridy = 8; panel.add(btnClear, gbc);

        // Button action listeners
        btnPersonal.addActionListener(e -> {
            lblExtra.setText("Monthly Prompts (Personal):");
            addPersonalPlan();
        });
        btnPro.addActionListener(e -> {
            lblExtra.setText("Team Slots (Pro):");
            addProPlan();
        });
        btnDisplay.addActionListener(e -> displayAll());
        btnClear.addActionListener(e -> clearFields());

        return panel;
    }

    private JPanel buildActionPanel() {                                // Builds the right panel for actions
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(new Color(100, 140, 200), 2),
            "Plan Actions", TitledBorder.LEFT, TitledBorder.TOP,
            new Font("Arial", Font.BOLD, 14), new Color(30, 60, 120)));
        panel.setBackground(Color.WHITE);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(6, 10, 6, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;

        addLabelField(panel, gbc, "Plan Index (0-based):", 0);         // Label for index input
        txtActionIndex = new JTextField(10);
        txtActionIndex.setFont(new Font("Arial", Font.PLAIN, 13));
        gbc.gridx = 1; gbc.gridy = 0;
        panel.add(txtActionIndex, gbc);

        addLabelField(panel, gbc, "Value / Prompt Name:", 1);          // Label for value/prompt
        txtActionValue = new JTextField(15);
        txtActionValue.setFont(new Font("Arial", Font.PLAIN, 13));
        gbc.gridx = 1; gbc.gridy = 1;
        panel.add(txtActionValue, gbc);

        // Action buttons
        String[] btnLabels = {
            "Purchase Prompts (Personal)",
            "Enter Prompt (Personal)",
            "Enter Prompt (Pro)",
            "Add Team Member (Pro)",
            "Remove Team Member (Pro)"
        };
        Color[] btnColors = {
            new Color(50, 130, 200),
            new Color(70, 160, 90),
            new Color(0, 150, 200),
            new Color(150, 80, 180),
            new Color(190, 100, 30)
        };

        for (int i = 0; i < btnLabels.length; i++) {
            JButton btn = makeButton(btnLabels[i], btnColors[i]);
            gbc.gridx = 0; gbc.gridy = i + 2; gbc.gridwidth = 2;
            panel.add(btn, gbc);
            final int index = i;
            btn.addActionListener(e -> handleAction(index));           // Lambda for button action
        }

        return panel;
    }

    private JPanel buildOutputPanel() {                                // Builds the bottom output panel
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(new Color(100, 140, 200), 2),
            "Output", TitledBorder.LEFT, TitledBorder.TOP,
            new Font("Arial", Font.BOLD, 14), new Color(30, 60, 120)));
        panel.setBackground(Color.WHITE);
        panel.setPreferredSize(new Dimension(0, 240));                

        outputArea = new JTextArea();                                  // Create output text area
        outputArea.setFont(new Font("Monospaced", Font.PLAIN, 12));    
        outputArea.setEditable(false);                                 // Make it read-only
        outputArea.setBackground(new Color(248, 250, 255));

        JScrollPane scroll = new JScrollPane(outputArea);              // Add scroll capability
        panel.add(scroll, BorderLayout.CENTER);

        return panel;
    }

    

    private JTextField addField(JPanel panel, GridBagConstraints gbc, String label, int row) {
        JLabel lbl = new JLabel(label);                                // Create label
        lbl.setFont(new Font("Arial", Font.PLAIN, 13));
        gbc.gridx = 0; gbc.gridy = row;
        panel.add(lbl, gbc);

        JTextField tf = new JTextField(15);                            // Create text field
        tf.setFont(new Font("Arial", Font.PLAIN, 13));
        gbc.gridx = 1;
        panel.add(tf, gbc);
        return tf;
    }

    private void addLabelField(JPanel panel, GridBagConstraints gbc, String label, int row) {
        JLabel lbl = new JLabel(label);
        lbl.setFont(new Font("Arial", Font.PLAIN, 13));
        gbc.gridx = 0; gbc.gridy = row;
        panel.add(lbl, gbc);
    }

    private JButton makeButton(String text, Color bg) {                // Helper to create styled buttons
        JButton btn = new JButton(text);
        btn.setFont(new Font("Arial", Font.BOLD, 13));
        btn.setBackground(bg);
        btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false);
        btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        return btn;
    }

    

    private void addPersonalPlan() {                                   // Adds a Personal Plan
        try {
            String name = validateName(txtModelName.getText().trim());
            double price = validatePrice(txtPrice.getText().trim());
            int params = validateParams(txtParams.getText().trim());
            String context = validateContext(txtContext.getText().trim());
            int prompts = Integer.parseInt(txtExtra.getText().trim());
            if (prompts < 0) throw new IllegalArgumentException("Monthly prompts must be >= 0.");
            plans.add(new PersonalPlan(name, price, params, context, prompts));
            output("✅ Personal Plan added successfully at index " + (plans.size() - 1) + "\n\n" +
                   plans.get(plans.size() - 1).display());
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Input Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void addProPlan() {                                        // Adds a Pro Plan
        try {
            String name = validateName(txtModelName.getText().trim());
            double price = validatePrice(txtPrice.getText().trim());
            int params = validateParams(txtParams.getText().trim());
            String context = validateContext(txtContext.getText().trim());
            int slots = Integer.parseInt(txtExtra.getText().trim());
            if (slots < 0) throw new IllegalArgumentException("Team slots must be >= 0.");
            plans.add(new ProPlan(name, price, params, context, slots));
            output("✅ Pro Plan added successfully at index " + (plans.size() - 1) + "\n\n" +
                   plans.get(plans.size() - 1).display());
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Input Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void displayAll() {                                        // Displays all plans
        if (plans.isEmpty()) {
            output("No plans added yet.");
            return;
        }
        StringBuilder sb = new StringBuilder("=== ALL PLANS ===\n\n");
        for (int i = 0; i < plans.size(); i++) {
            sb.append("[Index ").append(i).append("]\n");
            sb.append(plans.get(i).display()).append("\n");
            sb.append("------------------------------------\n\n");
        }
        output(sb.toString());
    }

    private void clearFields() {                                       // Clears all input fields
        txtModelName.setText("");
        txtPrice.setText("");
        txtParams.setText("");
        txtContext.setText("");
        txtExtra.setText("");
        txtActionIndex.setText("");
        txtActionValue.setText("");
        outputArea.setText("");
    }

    private void handleAction(int actionIdx) {                         // Handles all action button clicks
        try {
            int index = Integer.parseInt(txtActionIndex.getText().trim());
            if (index < 0 || index >= plans.size()) {
                JOptionPane.showMessageDialog(this,
                    "Invalid index! Use 0 to " + (plans.size() - 1),
                    "Index Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            AIModel plan = plans.get(index);
            String value = txtActionValue.getText().trim();

            switch (actionIdx) {
                case 0: // Purchase Prompts (Personal)
                    if (!(plan instanceof PersonalPlan)) {
                        JOptionPane.showMessageDialog(this, "This action is only for Personal Plans!", "Type Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    int extra = Integer.parseInt(value);
                    output(((PersonalPlan) plan).purchasePrompts(extra));
                    break;
                case 1: // Enter Prompt (Personal)
                    if (!(plan instanceof PersonalPlan)) {
                        JOptionPane.showMessageDialog(this, "This action is only for Personal Plans!", "Type Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    int tokens1 = getTokenInput();
                    if (tokens1 == -1) return;
                    output(((PersonalPlan) plan).enterPrompt(value, tokens1));
                    break;
                case 2: // Enter Prompt (Pro)
                    if (!(plan instanceof ProPlan)) {
                        JOptionPane.showMessageDialog(this, "This action is only for Pro Plans!", "Type Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    int tokens2 = getTokenInput();
                    if (tokens2 == -1) return;
                    output(((ProPlan) plan).enterPrompt(value, tokens2));
                    break;
                case 3: // Add Team Member
                    if (!(plan instanceof ProPlan)) {
                        JOptionPane.showMessageDialog(this, "This action is only for Pro Plans!", "Type Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    output(((ProPlan) plan).addTeamMember(value));
                    break;
                case 4: // Remove Team Member
                    if (!(plan instanceof ProPlan)) {
                        JOptionPane.showMessageDialog(this, "This action is only for Pro Plans!", "Type Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    output(((ProPlan) plan).removeTeamMember(value));
                    break;
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Invalid input!\n" + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private int getTokenInput() {                                      // Shows dialog to get token count
        String input = JOptionPane.showInputDialog(this,
            "Enter expected output length (tokens):",
            "Token Input", JOptionPane.QUESTION_MESSAGE);
        if (input == null || input.trim().isEmpty()) return -1;
        try {
            return Integer.parseInt(input.trim());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Please enter a valid number!", "Invalid Input", JOptionPane.ERROR_MESSAGE);
            return -1;
        }
    }

    private String validateName(String name) {                         // Validates model name
        if (name.isEmpty()) throw new IllegalArgumentException("Model name cannot be empty.");
        return name;
    }

    private double validatePrice(String s) {                           // Validates price
        double v = Double.parseDouble(s);
        if (v < 0) throw new IllegalArgumentException("Price cannot be negative.");
        return v;
    }

    private int validateParams(String s) {                             // Validates parameters
        int v = Integer.parseInt(s);
        if (v <= 0) throw new IllegalArgumentException("Parameters must be greater than 0.");
        return v;
    }

    private String validateContext(String s) {                         // Validates context window
        if (s.isEmpty()) throw new IllegalArgumentException("Context window cannot be empty.");
        return s;
    }

    private void output(String msg) {                                  // Displays message in output area
        outputArea.setText(msg);
    }

    public static void main(String[] args) {                           // Main method - entry point
        SwingUtilities.invokeLater(() -> new SubscriptionGUI());       // Thread-safe GUI creation
    }
}
