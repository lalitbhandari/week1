package week1.week20;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

/**
 * SubscriptionGUI - Main GUI class for managing AI model subscription plans.
 * This class creates the window and all components for adding Personal/Pro plans
 * and performing actions like purchasing prompts or managing team members.
 * 
 * @author SubscriptionSystem
 * @version 1.0
 */
public class SubscriptionGUI extends JFrame {

    /** ArrayList to store all the AI model subscription plans (PersonalPlan and ProPlan objects) */
    private ArrayList<AIModel> plans = new ArrayList<>();

    // nput Fields for adding a new plan 
    private JTextField txtModelName;      // Field to enter model name
    private JTextField txtPrice;          // Field to enter price per 1 lakh tokens
    private JTextField txtParams;         // Field to enter number of parameters in billions
    private JTextField txtContext;        // Field to enter context window size
    private JTextField txtExtra;          // Field for monthly prompts (Personal) or team slots (Pro)

    //  Fields used in Action Panel 
    private JTextField txtActionIndex;    // Field to enter plan index (0-based)
    private JTextField txtActionValue;    // Field to enter value like name or number of prompts

    // Output Area 
    private JTextArea outputArea;         // Area where all messages and results are shown

    //  Dynamic Label
    private JLabel lblExtra;              // Label that changes between "Monthly Prompts" and "Team Slots"

    /**
     * Constructor: Sets up the window properties and builds the user interface.
     */
    public SubscriptionGUI() {
        setTitle("AI Subscription Manager - Itahari Tech");        // Set window title
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);            // Close application when window is closed
        setSize(820, 680);                                         // Set window size
        setLocationRelativeTo(null);                               // Center the window on screen
        setResizable(false);                                       // Prevent user from resizing the window
        buildUI();                                                 // Call method to create all GUI components
        setVisible(true);                                          // Make the window visible
    }

    /**
     * Builds the overall layout of the GUI using BorderLayout.
     */
    private void buildUI() {
        JPanel mainPanel = new JPanel(new BorderLayout(8, 8));     // Main container with spacing
        mainPanel.setBorder(new EmptyBorder(10, 10, 10, 10));      // Add padding around the edges
        mainPanel.setBackground(new Color(240, 245, 255));         // Light blue background

        // Create and add title label at the top
        JLabel title = new JLabel("AI Model Subscription Manager", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 20));
        title.setForeground(new Color(30, 60, 120));
        title.setBorder(new EmptyBorder(0, 0, 8, 0));
        mainPanel.add(title, BorderLayout.NORTH);

        // Center panel to hold Input and Action panels side by side
        JPanel centerPanel = new JPanel(new GridLayout(1, 2, 10, 0));
        centerPanel.setBackground(new Color(240, 245, 255));
        centerPanel.add(buildInputPanel());                        // Left side: Add new plan
        centerPanel.add(buildActionPanel());                       // Right side: Actions on existing plans
        mainPanel.add(centerPanel, BorderLayout.CENTER);

        // Bottom panel for output messages
        mainPanel.add(buildOutputPanel(), BorderLayout.SOUTH);

        add(mainPanel);                                            // Add mainPanel to the JFrame
    }

    /**
     * Creates the left panel where user can input details to add a new plan.
     */
    private JPanel buildInputPanel() {
        JPanel panel = new JPanel(new GridBagLayout());            // Use GridBagLayout for flexible positioning
        panel.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(new Color(100, 140, 200)),
            "Add New Plan", TitledBorder.LEFT, TitledBorder.TOP,
            new Font("Arial", Font.BOLD, 13), new Color(30, 60, 120)));
        panel.setBackground(Color.WHITE);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 8, 5, 8);                       // Spacing between components
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;

        // Add input fields row by row
        txtModelName = addField(panel, gbc, "Model Name:", 0);
        txtPrice = addField(panel, gbc, "Price (NRS/1L tokens):", 1);
        txtParams = addField(panel, gbc, "Parameters (Billions):", 2);
        txtContext = addField(panel, gbc, "Context Window (e.g. 64K):", 3);

        // Label and field for extra information (prompts or slots)
        lblExtra = new JLabel("Monthly Prompts (Personal):");
        lblExtra.setFont(new Font("Arial", Font.PLAIN, 12));
        gbc.gridx = 0; gbc.gridy = 4;
        panel.add(lblExtra, gbc);

        txtExtra = new JTextField();
        txtExtra.setFont(new Font("Arial", Font.PLAIN, 12));
        gbc.gridx = 1; gbc.gridy = 4;
        panel.add(txtExtra, gbc);

        // Create buttons
        JButton btnPersonal = makeButton("Add Personal Plan", new Color(50, 130, 200));
        JButton btnPro = makeButton("Add Pro Plan", new Color(70, 160, 90));
        JButton btnDisplay = makeButton("Display All", new Color(180, 100, 30));
        JButton btnClear = makeButton("Clear", new Color(190, 50, 50));

        // Place buttons vertically
        gbc.gridx = 0; gbc.gridy = 5; gbc.gridwidth = 2;
        panel.add(btnPersonal, gbc);
        gbc.gridy = 6;
        panel.add(btnPro, gbc);
        gbc.gridy = 7;
        panel.add(btnDisplay, gbc);
        gbc.gridy = 8;
        panel.add(btnClear, gbc);

        // Attach action listeners to buttons
        btnPersonal.addActionListener(e -> {
            lblExtra.setText("Monthly Prompts (Personal):");   // Reset label
            addPersonalPlan();
        });

        btnPro.addActionListener(e -> {
            lblExtra.setText("Team Slots (Pro):");             // Change label for Pro plan
            addProPlan();
        });

        btnDisplay.addActionListener(e -> displayAll());
        btnClear.addActionListener(e -> clearFields());

        return panel;
    }

    /**
     * Creates the right panel for performing actions on existing plans.
     */
    private JPanel buildActionPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(new Color(100, 140, 200)),
            "Plan Actions", TitledBorder.LEFT, TitledBorder.TOP,
            new Font("Arial", Font.BOLD, 13), new Color(30, 60, 120)));
        panel.setBackground(Color.WHITE);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 8, 5, 8);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;

        // Fields for action: index and value
        addLabelField(panel, gbc, "Plan Index (0-based):", 0);
        txtActionIndex = new JTextField();
        txtActionIndex.setFont(new Font("Arial", Font.PLAIN, 12));
        gbc.gridx = 1; gbc.gridy = 0;
        panel.add(txtActionIndex, gbc);

        addLabelField(panel, gbc, "Value / Name:", 1);
        txtActionValue = new JTextField();
        txtActionValue.setFont(new Font("Arial", Font.PLAIN, 12));
        gbc.gridx = 1; gbc.gridy = 1;
        panel.add(txtActionValue, gbc);

        // Action buttons (Export and Import removed as requested)
        String[] btnLabels = {
            "Purchase Prompts (Personal)",
            "Enter Prompt (Personal)",
            "Add Team Member (Pro)",
            "Remove Team Member (Pro)"
        };

        Color[] btnColors = {
            new Color(50, 130, 200),
            new Color(70, 160, 90),
            new Color(150, 80, 180),
            new Color(190, 100, 30)
        };

        // Create and add each action button
        for (int i = 0; i < btnLabels.length; i++) {
            JButton btn = makeButton(btnLabels[i], btnColors[i]);
            gbc.gridx = 0; gbc.gridy = i + 2; gbc.gridwidth = 2;
            panel.add(btn, gbc);
            final int idx = i;
            btn.addActionListener(e -> handleAction(idx));     // Call handleAction with button index
        }

        return panel;
    }

    /**
     * Creates the bottom panel containing the output text area.
     */
    private JPanel buildOutputPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(new Color(100, 140, 200)),
            "Output", TitledBorder.LEFT, TitledBorder.TOP,
            new Font("Arial", Font.BOLD, 13), new Color(30, 60, 120)));
        panel.setBackground(Color.WHITE);
        panel.setPreferredSize(new Dimension(0, 200));

        outputArea = new JTextArea();
        outputArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
        outputArea.setEditable(false);                         // User cannot edit the output
        outputArea.setBackground(new Color(248, 250, 255));

        JScrollPane scroll = new JScrollPane(outputArea);      // Add scrolling
        panel.add(scroll, BorderLayout.CENTER);
        return panel;
    }

    // Helper Methods

    /**
     * Helper method to add a label and text field in one row.
     */
    private JTextField addField(JPanel panel, GridBagConstraints gbc, String label, int row) {
        JLabel lbl = new JLabel(label);
        lbl.setFont(new Font("Arial", Font.PLAIN, 12));
        gbc.gridx = 0; gbc.gridy = row; gbc.gridwidth = 1;
        panel.add(lbl, gbc);

        JTextField tf = new JTextField();
        tf.setFont(new Font("Arial", Font.PLAIN, 12));
        gbc.gridx = 1; gbc.gridy = row;
        panel.add(tf, gbc);
        return tf;
    }

    /**
     * Helper method to add only a label (used in Action panel).
     */
    private void addLabelField(JPanel panel, GridBagConstraints gbc, String label, int row) {
        JLabel lbl = new JLabel(label);
        lbl.setFont(new Font("Arial", Font.PLAIN, 12));
        gbc.gridx = 0; gbc.gridy = row; gbc.gridwidth = 1;
        panel.add(lbl, gbc);
    }

    /**
     * Helper method to create a styled button with given text and background color.
     */
    private JButton makeButton(String text, Color bg) {
        JButton btn = new JButton(text);
        btn.setFont(new Font("Arial", Font.BOLD, 12));
        btn.setBackground(bg);
        btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false);
        btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        return btn;
    }

    //Business Logic Methods 

    /**
     * Reads input fields and adds a new PersonalPlan to the list.
     */
    private void addPersonalPlan() {
        try {
            // getText enter the JtextField and , trim name, validateName check itis number or String.
            String name = validateName(txtModelName.getText().trim());
            double price = validatePrice(txtPrice.getText().trim());
            int params = validateParams(txtParams.getText().trim());
            String context = validateContext(txtContext.getText().trim());
            int prompts = Integer.parseInt(txtExtra.getText().trim());

            if (prompts < 0) 
                throw new IllegalArgumentException("Prompts must be >= 0.");

            plans.add(new PersonalPlan(name, price, params, context, prompts));
            
            output("Personal Plan added at index " + (plans.size() - 1) + ":\n" +
                   plans.get(plans.size() - 1).display());

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Invalid number format.", "Input Error", JOptionPane.ERROR_MESSAGE);
        } 
        catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Validation Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Reads input fields and adds a new ProPlan to the list.
     */
    private void addProPlan() {
        try {
            String name = validateName(txtModelName.getText().trim());
            double price = validatePrice(txtPrice.getText().trim());
            int params = validateParams(txtParams.getText().trim());
            String context = validateContext(txtContext.getText().trim());
            int slots = Integer.parseInt(txtExtra.getText().trim());

            if (slots < 0) 
                throw new IllegalArgumentException("Team slots must be >= 0.");

            plans.add(new ProPlan(name, price, params, context, slots));
            
            output("Pro Plan added at index " + (plans.size() - 1) + ":\n" +
                   plans.get(plans.size() - 1).display());

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Invalid number format.", "Input Error", JOptionPane.ERROR_MESSAGE);
        } 
        catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Validation Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Displays all added plans in the output area.
     */
    private void displayAll() {
        if (plans.isEmpty()) {
            output("No plans added yet.");
            return;
        }

        StringBuilder sb = new StringBuilder("=== All Plans ===\n");
        for (int i = 0; i < plans.size(); i++) {
            sb.append("\n[Index ").append(i).append("]\n");
            sb.append(plans.get(i).display()).append("\n");
            sb.append("-----------------------------");
        }
        output(sb.toString());
    }

    /**
     * Clears all input fields and the output area.
     */
    private void clearFields() {
        txtModelName.setText("");
        txtPrice.setText("");
        txtParams.setText("");
        txtContext.setText("");
        txtExtra.setText("");
        txtActionIndex.setText("");
        txtActionValue.setText("");
        outputArea.setText("");
    }

    /**
     * Handles the four action buttons based on their index.
     * actionIdx = 0 → Purchase Prompts
     * actionIdx = 1 → Enter Prompt
     * actionIdx = 2 → Add Team Member
     * actionIdx = 3 → Remove Team Member
     */
    private void handleAction(int actionIdx) {
        try {
            int index = Integer.parseInt(txtActionIndex.getText().trim());
            
            if (index < 0 || index >= plans.size()) {
                JOptionPane.showMessageDialog(this,
                    "Index out of range. Valid range: 0 to " + (plans.size() - 1),
                    "Index Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            AIModel plan = plans.get(index);
            String value = txtActionValue.getText().trim();

            switch (actionIdx) {
                case 0: // Purchase Prompts for Personal Plan
                //type casting, 
                    if (!(plan instanceof PersonalPlan)) {
                        JOptionPane.showMessageDialog(this, "Selected plan is not a Personal Plan.",
                            "Type Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    int extra = Integer.parseInt(value);
                    output(((PersonalPlan) plan).purchasePrompts(extra));
                    break;

                case 1: // Enter a Prompt for Personal Plan
                    if (!(plan instanceof PersonalPlan)) {
                        JOptionPane.showMessageDialog(this, "Selected plan is not a Personal Plan.",
                            "Type Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    String tokenStr = JOptionPane.showInputDialog(this,
                        "Enter expected output length (tokens):", "Token Input",
                        JOptionPane.QUESTION_MESSAGE);
                    if (tokenStr == null) return;
                    int tokens = Integer.parseInt(tokenStr.trim());
                    output(((PersonalPlan) plan).enterPrompt(value, tokens));
                    break;

                case 2: // Add Team Member for Pro Plan
                    if (!(plan instanceof ProPlan)) {
                        JOptionPane.showMessageDialog(this, "Selected plan is not a Pro Plan.",
                            "Type Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    output(((ProPlan) plan).addTeamMember(value));
                    break;

                case 3: // Remove Team Member for Pro Plan
                    if (!(plan instanceof ProPlan)) {
                        JOptionPane.showMessageDialog(this, "Selected plan is not a Pro Plan.",
                            "Type Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    output(((ProPlan) plan).removeTeamMember(value));
                    break;
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter a valid number.", "Input Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Validation Helper Methods

    private String validateName(String name) {
        if (name == null || name.isEmpty())
            throw new IllegalArgumentException("Model name cannot be empty.");
        return name;
    }

    private double validatePrice(String s) {
        double v = Double.parseDouble(s);
        if (v < 0) throw new IllegalArgumentException("Price must be >= 0.");
        return v;
    }

    private int validateParams(String s) {
        int v = Integer.parseInt(s);
        if (v <= 0) throw new IllegalArgumentException("Parameter count must be > 0.");
        return v;
    }

    private String validateContext(String s) {
        if (s == null || s.isEmpty())
            throw new IllegalArgumentException("Context window cannot be empty.");
        return s;
    }

    /**
     * Displays the given message in the output text area.
     */
    private void output(String msg) {
        outputArea.setText(msg);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new SubscriptionGUI());
    }
}
