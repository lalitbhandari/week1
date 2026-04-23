package week1.week20.CourseWorkFinal;


/**
 * Write a description of class k here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */


import javax.swing.*;                      // Core Swing components (JFrame, JButton, JLabel, etc.)
import javax.swing.border.*;              // Border utilities (EmptyBorder, TitledBorder, etc.)
import javax.swing.plaf.basic.*;          // Basic UI delegates for custom painting
import java.awt.*;                        // AWT (layout managers, Color, Font, Graphics, etc.)
import java.awt.event.*;                  // Event handling (ActionListener, MouseAdapter, etc.)
import java.awt.geom.*;                   // 2D geometry (RoundRectangle2D for rounded buttons)
import java.time.LocalDateTime;           // For timestamp in output
import java.time.format.DateTimeFormatter;// Format timestamps
import java.util.ArrayList;              // Dynamic list for storing plans

/**
 * SubscriptionGUI - Enhanced main GUI class for managing AI model subscription plans.
 *
 * Design: Dark-themed professional dashboard with:
 *   - Sidebar navigation panel (left)
 *   - Input / Action panels (center)
 *   - Rich output console (bottom)
 *   - Live stats bar (top-right)
 *   - Status bar (bottom strip)
 *
 * All original logic is preserved exactly.
 * Only the visual presentation and layout have been improved.
 */
public class SubscriptionGUI extends JFrame {

    // ─── Color Palette ─────────────────────────────────────────────────────
    private static final Color BG_DARK       = new Color(15,  20,  35);   // Main dark background
    private static final Color BG_PANEL      = new Color(22,  30,  50);   // Panel/card background
    private static final Color BG_CARD       = new Color(30,  40,  65);   // Lighter card surface
    private static final Color ACCENT_BLUE   = new Color(64, 156, 255);   // Primary accent (blue)
    private static final Color ACCENT_GREEN  = new Color(52, 211, 153);   // Success / Personal
    private static final Color ACCENT_PURPLE = new Color(167, 139, 250);  // Pro plan color
    private static final Color ACCENT_ORANGE = new Color(251, 146, 60);   // Warning / purchase
    private static final Color ACCENT_RED    = new Color(248,  83,  83);  // Error / remove
    private static final Color TEXT_PRIMARY  = new Color(236, 240, 255);  // Main text
    private static final Color TEXT_MUTED    = new Color(130, 145, 180);  // Muted/secondary text
    private static final Color BORDER_COLOR  = new Color(45,  58,  90);   // Subtle border

    // ─── Fonts ─────────────────────────────────────────────────────────────
    private static final Font FONT_TITLE   = new Font("Segoe UI", Font.BOLD,  20);
    private static final Font FONT_SECTION = new Font("Segoe UI", Font.BOLD,  13);
    private static final Font FONT_LABEL   = new Font("Segoe UI", Font.PLAIN, 12);
    private static final Font FONT_INPUT   = new Font("Segoe UI", Font.PLAIN, 13);
    private static final Font FONT_MONO    = new Font("Consolas",  Font.PLAIN, 12);
    private static final Font FONT_SMALL   = new Font("Segoe UI", Font.PLAIN, 11);

    // ─── Data ──────────────────────────────────────────────────────────────
    private ArrayList<AIModel> plans = new ArrayList<>(); // All added subscription plans

    // ─── Input Fields ──────────────────────────────────────────────────────
    private JTextField txtModelName, txtPrice, txtParams, txtContext, txtExtra;
    private JLabel     lblExtra;   // Dynamic label — changes between Personal/Pro mode

    // ─── Action Fields ─────────────────────────────────────────────────────
    private JTextField txtActionIndex, txtActionValue;

    // ─── Output & Status ───────────────────────────────────────────────────
    private JTextArea outputArea;  // Main console / results area
    private JLabel    lblStatus;   // Bottom status bar message
    private JLabel    lblPlanCount;// Live plan counter in header

    // ─── Constructor ───────────────────────────────────────────────────────

    /**
     * Initialises the frame and builds the full UI.
     */
    public SubscriptionGUI() {
        setTitle("AI Subscription Manager  ·  Lalit Tech");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(980, 780);
        setMinimumSize(new Dimension(900, 700));
        setLocationRelativeTo(null);            // Centre on screen
        setResizable(true);

        // Apply dark title bar on supported systems
        getRootPane().putClientProperty("JRootPane.titleBarBackground", BG_DARK);
        getRootPane().putClientProperty("JRootPane.titleBarForeground", TEXT_PRIMARY);

        buildUI();
        setVisible(true);
    }

    // ─── UI Construction ───────────────────────────────────────────────────

    /**
     * Assembles the full frame layout:
     *   NORTH  → branded header bar
     *   CENTER → split input / action panels
     *   SOUTH  → output console + status strip
     */
    private void buildUI() {
        JPanel root = new JPanel(new BorderLayout(0, 0));
        root.setBackground(BG_DARK);

        root.add(buildHeaderBar(),   BorderLayout.NORTH);
        root.add(buildCenterArea(),  BorderLayout.CENTER);
        root.add(buildBottomArea(),  BorderLayout.SOUTH);

        add(root);
    }

    /**
     * Builds the top branded header bar with title, subtitle, and live plan counter.
     */
    private JPanel buildHeaderBar() {
        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(BG_PANEL);
        header.setBorder(BorderFactory.createCompoundBorder(
            new MatteBorder(0, 0, 1, 0, BORDER_COLOR),   // Bottom divider line
            new EmptyBorder(14, 24, 14, 24)               // Inner padding
        ));

        // Left: logo-style title + subtitle
        JPanel left = new JPanel(new GridLayout(2, 1, 0, 2));
        left.setOpaque(false);

        JLabel title = new JLabel("⚡  AI Subscription Manager");
        title.setFont(FONT_TITLE);
        title.setForeground(TEXT_PRIMARY);

        JLabel subtitle = new JLabel("Manage Personal & Pro AI Model Plans  ·  Lalit Tech");
        subtitle.setFont(FONT_SMALL);
        subtitle.setForeground(TEXT_MUTED);

        left.add(title);
        left.add(subtitle);

        // Right: live plan counter badge
        lblPlanCount = new JLabel("0 Plans");
        lblPlanCount.setFont(new Font("Segoe UI", Font.BOLD, 12));
        lblPlanCount.setForeground(ACCENT_BLUE);
        lblPlanCount.setHorizontalAlignment(SwingConstants.RIGHT);
        lblPlanCount.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(ACCENT_BLUE, 1, true),
            new EmptyBorder(4, 12, 4, 12)
        ));

        header.add(left,          BorderLayout.WEST);
        header.add(lblPlanCount,  BorderLayout.EAST);
        return header;
    }

    /**
     * Builds the center area with the two side-by-side panels:
     *   left  → Add New Plan (inputs)
     *   right → Plan Actions
     */
    private JPanel buildCenterArea() {
        JPanel center = new JPanel(new GridLayout(1, 2, 12, 0));
        center.setBackground(BG_DARK);
        center.setBorder(new EmptyBorder(14, 14, 8, 14));
        center.add(buildInputPanel());
        center.add(buildActionPanel());
        return center;
    }

    /**
     * Builds the bottom area:
     *   top    → output console
     *   bottom → status bar strip
     */
    private JPanel buildBottomArea() {
        JPanel bottom = new JPanel(new BorderLayout(0, 0));
        bottom.setBackground(BG_DARK);
        bottom.add(buildOutputPanel(), BorderLayout.CENTER);
        bottom.add(buildStatusBar(),   BorderLayout.SOUTH);
        return bottom;
    }

    // ─── Left Panel: Add New Plan ──────────────────────────────────────────

    /**
     * Builds the "Add New Plan" card with styled input fields and action buttons.
     */
    private JPanel buildInputPanel() {
        JPanel card = createCard("➕  Add New Plan");

        GridBagConstraints gbc = defaultGbc();

        // ── Input fields ──────────────────────────────────────────────────
        txtModelName = addStyledField(card, gbc, "Model Name",               0);
        txtPrice     = addStyledField(card, gbc, "Price (NRS / 1L tokens)",  1);
        txtParams    = addStyledField(card, gbc, "Parameters (Billions)",     2);
        txtContext   = addStyledField(card, gbc, "Context Window (e.g. 64K)",3);

        // Dynamic extra field (changes label based on plan type)
        lblExtra = styledLabel("Monthly Prompts (Personal)");
        gbc.gridx = 0; gbc.gridy = 4; gbc.gridwidth = 1;
        card.add(lblExtra, gbc);

        txtExtra = styledTextField();
        gbc.gridx = 1; gbc.gridy = 4;
        card.add(txtExtra, gbc);

        // ── Separator ─────────────────────────────────────────────────────
        gbc.gridx = 0; gbc.gridy = 5; gbc.gridwidth = 2;
        card.add(makeSeparator(), gbc);

        // ── Buttons ───────────────────────────────────────────────────────
        gbc.gridy = 6;
        card.add(makeRoundButton("👤  Add Personal Plan", ACCENT_GREEN,  BG_DARK), gbc);
        gbc.gridy = 7;
        card.add(makeRoundButton("🏢  Add Pro Plan",       ACCENT_PURPLE, BG_DARK), gbc);
        gbc.gridy = 8;
        card.add(makeRoundButton("📋  Display All Plans",  ACCENT_BLUE,   BG_DARK), gbc);
        gbc.gridy = 9;
        card.add(makeRoundButton("🗑   Clear Fields",       ACCENT_RED,    BG_DARK), gbc);

        // Retrieve buttons by index to attach listeners
        Component[] btns = getButtonsFrom(card);
        ((JButton) btns[0]).addActionListener(e -> {
            lblExtra.setText("Monthly Prompts (Personal)");
            addPersonalPlan();
        });
        ((JButton) btns[1]).addActionListener(e -> {
            lblExtra.setText("Team Slots (Pro)");
            addProPlan();
        });
        ((JButton) btns[2]).addActionListener(e -> displayAll());
        ((JButton) btns[3]).addActionListener(e -> clearFields());

        return card;
    }

    // ─── Right Panel: Plan Actions ─────────────────────────────────────────

    /**
     * Builds the "Plan Actions" card with index/value inputs and action buttons.
     */
    private JPanel buildActionPanel() {
        JPanel card = createCard("⚙️  Plan Actions");

        GridBagConstraints gbc = defaultGbc();

        // Index and value fields
        addStyledLabelOnly(card, gbc, "Plan Index (0-based)", 0);
        txtActionIndex = styledTextField();
        gbc.gridx = 1; gbc.gridy = 0;
        card.add(txtActionIndex, gbc);

        addStyledLabelOnly(card, gbc, "Value / Prompt Name", 1);
        txtActionValue = styledTextField();
        gbc.gridx = 1; gbc.gridy = 1;
        card.add(txtActionValue, gbc);

        // Separator before action buttons
        gbc.gridx = 0; gbc.gridy = 2; gbc.gridwidth = 2;
        card.add(makeSeparator(), gbc);

        // ── Action Buttons ────────────────────────────────────────────────
        String[] labels = {
            "💳  Purchase Prompts (Personal)",
            "💬  Enter Prompt (Personal)",
            "💬  Enter Prompt (Pro)",
            "➕  Add Team Member (Pro)",
            "➖  Remove Team Member (Pro)"
        };
        Color[] colors = {
            ACCENT_ORANGE,
            ACCENT_GREEN,
            ACCENT_BLUE,
            ACCENT_PURPLE,
            ACCENT_RED
        };

        for (int i = 0; i < labels.length; i++) {
            JButton btn = makeRoundButton(labels[i], colors[i], BG_DARK);
            gbc.gridy = i + 3;
            card.add(btn, gbc);
            final int idx = i;
            btn.addActionListener(e -> handleAction(idx)); // Dispatch to handler
        }

        return card;
    }

    // ─── Output Console ────────────────────────────────────────────────────

    /**
     * Builds the output console panel with a timestamped text area.
     */
    private JPanel buildOutputPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(BG_PANEL);
        panel.setBorder(BorderFactory.createCompoundBorder(
            new EmptyBorder(0, 14, 6, 14),
            BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(BORDER_COLOR, 1, true),
                new EmptyBorder(0, 0, 0, 0)
            )
        ));
        panel.setPreferredSize(new Dimension(0, 230));

        // Console header bar
        JPanel consoleHeader = new JPanel(new BorderLayout());
        consoleHeader.setBackground(BG_CARD);
        consoleHeader.setBorder(new EmptyBorder(7, 14, 7, 14));

        JLabel consoleTitle = new JLabel("🖥   Console Output");
        consoleTitle.setFont(FONT_SECTION);
        consoleTitle.setForeground(ACCENT_BLUE);
        consoleHeader.add(consoleTitle, BorderLayout.WEST);

        JButton btnClearOutput = new JButton("Clear");
        btnClearOutput.setFont(FONT_SMALL);
        btnClearOutput.setBackground(BG_PANEL);
        btnClearOutput.setForeground(TEXT_MUTED);
        btnClearOutput.setBorder(BorderFactory.createLineBorder(BORDER_COLOR, 1, true));
        btnClearOutput.setFocusPainted(false);
        btnClearOutput.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnClearOutput.addActionListener(e -> outputArea.setText(""));
        consoleHeader.add(btnClearOutput, BorderLayout.EAST);

        // Main text area
        outputArea = new JTextArea();
        outputArea.setFont(FONT_MONO);
        outputArea.setEditable(false);
        outputArea.setBackground(new Color(10, 14, 26));  // Very dark console background
        outputArea.setForeground(new Color(180, 220, 180)); // Soft green terminal text
        outputArea.setCaretColor(ACCENT_GREEN);
        outputArea.setSelectionColor(new Color(64, 156, 255, 80));
        outputArea.setBorder(new EmptyBorder(10, 14, 10, 14));
        outputArea.setText("  Welcome to AI Subscription Manager\n" +
                           "  ─────────────────────────────────────\n" +
                           "  Add plans using the left panel, then\n" +
                           "  use the right panel for actions.\n");

        JScrollPane scroll = new JScrollPane(outputArea);
        scroll.setBorder(BorderFactory.createEmptyBorder());
        scroll.getViewport().setBackground(new Color(10, 14, 26));

        panel.add(consoleHeader, BorderLayout.NORTH);
        panel.add(scroll,        BorderLayout.CENTER);
        return panel;
    }

    /**
     * Builds the slim status bar at the very bottom of the frame.
     */
    private JPanel buildStatusBar() {
        JPanel bar = new JPanel(new BorderLayout());
        bar.setBackground(new Color(10, 14, 26));
        bar.setBorder(new EmptyBorder(4, 16, 4, 16));

        lblStatus = new JLabel("Ready  ·  No plans loaded");
        lblStatus.setFont(FONT_SMALL);
        lblStatus.setForeground(TEXT_MUTED);

        JLabel version = new JLabel("v2.0  ·  Lalit Tech");
        version.setFont(FONT_SMALL);
        version.setForeground(new Color(60, 80, 110));

        bar.add(lblStatus, BorderLayout.WEST);
        bar.add(version,   BorderLayout.EAST);
        return bar;
    }

    // ─── Business Logic Handlers ───────────────────────────────────────────

    /** Validates inputs and creates a new PersonalPlan, adds to list. */
    private void addPersonalPlan() {
        try {
            String name    = validateName(txtModelName.getText().trim());
            double price   = validatePrice(txtPrice.getText().trim());
            int    params  = validateParams(txtParams.getText().trim());
            String context = validateContext(txtContext.getText().trim());
            int    prompts = Integer.parseInt(txtExtra.getText().trim());
            if (prompts < 0) throw new IllegalArgumentException("Monthly prompts must be ≥ 0.");
            plans.add(new PersonalPlan(name, price, params, context, prompts));
            updatePlanCounter();
            output("✅ Personal Plan added at index " + (plans.size() - 1) + "\n\n" +
                   plans.get(plans.size() - 1).display());
            setStatus("Personal Plan '" + name + "' added successfully.", ACCENT_GREEN);
        } catch (Exception ex) {
            showError(ex.getMessage());
        }
    }

    /** Validates inputs and creates a new ProPlan, adds to list. */
    private void addProPlan() {
        try {
            String name    = validateName(txtModelName.getText().trim());
            double price   = validatePrice(txtPrice.getText().trim());
            int    params  = validateParams(txtParams.getText().trim());
            String context = validateContext(txtContext.getText().trim());
            int    slots   = Integer.parseInt(txtExtra.getText().trim());
            if (slots < 0) throw new IllegalArgumentException("Team slots must be ≥ 0.");
            plans.add(new ProPlan(name, price, params, context, slots));
            updatePlanCounter();
            output("✅ Pro Plan added at index " + (plans.size() - 1) + "\n\n" +
                   plans.get(plans.size() - 1).display());
            setStatus("Pro Plan '" + name + "' added successfully.", ACCENT_PURPLE);
        } catch (Exception ex) {
            showError(ex.getMessage());
        }
    }

    /** Displays all stored plans in the console. */
    private void displayAll() {
        if (plans.isEmpty()) {
            output("  No plans added yet.\n  Use the left panel to add your first plan.");
            setStatus("No plans to display.", TEXT_MUTED);
            return;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("═══════════════════════════════════\n");
        sb.append("         ALL SUBSCRIPTION PLANS\n");
        sb.append("═══════════════════════════════════\n\n");
        for (int i = 0; i < plans.size(); i++) {
            String type = (plans.get(i) instanceof PersonalPlan) ? "Personal" : "Pro";
            sb.append("[").append(i).append("]  ── ").append(type).append(" Plan ──\n");
            sb.append(plans.get(i).display()).append("\n");
            sb.append("───────────────────────────────────\n\n");
        }
        output(sb.toString());
        setStatus("Displaying " + plans.size() + " plan(s).", ACCENT_BLUE);
    }

    /** Clears all input fields and output area. */
    private void clearFields() {
        txtModelName.setText("");
        txtPrice.setText("");
        txtParams.setText("");
        txtContext.setText("");
        txtExtra.setText("");
        txtActionIndex.setText("");
        txtActionValue.setText("");
        outputArea.setText("");
        setStatus("All fields cleared.", TEXT_MUTED);
    }

    /**
     * Dispatches one of 5 plan actions based on button index:
     *   0 → Purchase Prompts (Personal)
     *   1 → Enter Prompt     (Personal)
     *   2 → Enter Prompt     (Pro)
     *   3 → Add Team Member  (Pro)
     *   4 → Remove Member    (Pro)
     */
    private void handleAction(int actionIdx) {
        try {
            int idx = Integer.parseInt(txtActionIndex.getText().trim());
            if (idx < 0 || idx >= plans.size()) {
                showError("Invalid index! Valid range: 0 to " + (plans.size() - 1));
                return;
            }
            AIModel plan  = plans.get(idx);
            String  value = txtActionValue.getText().trim();

            switch (actionIdx) {
                case 0: // Purchase Prompts — Personal only
                    requireType(plan, PersonalPlan.class, "Personal");
                    int extra = Integer.parseInt(value);
                    output(((PersonalPlan) plan).purchasePrompts(extra));
                    setStatus("Prompts purchased for plan #" + idx, ACCENT_ORANGE);
                    break;

                case 1: // Enter Prompt — Personal
                    requireType(plan, PersonalPlan.class, "Personal");
                    int t1 = getTokenInput(); if (t1 == -1) return;
                    output(((PersonalPlan) plan).enterPrompt(value, t1));
                    setStatus("Prompt entered on Personal plan #" + idx, ACCENT_GREEN);
                    break;

                case 2: // Enter Prompt — Pro
                    requireType(plan, ProPlan.class, "Pro");
                    int t2 = getTokenInput(); if (t2 == -1) return;
                    output(((ProPlan) plan).enterPrompt(value, t2));
                    setStatus("Prompt entered on Pro plan #" + idx, ACCENT_BLUE);
                    break;

                case 3: // Add Team Member — Pro
                    requireType(plan, ProPlan.class, "Pro");
                    output(((ProPlan) plan).addTeamMember(value));
                    setStatus("Team member action on plan #" + idx, ACCENT_PURPLE);
                    break;

                case 4: // Remove Team Member — Pro
                    requireType(plan, ProPlan.class, "Pro");
                    output(((ProPlan) plan).removeTeamMember(value));
                    setStatus("Member removed from plan #" + idx, ACCENT_RED);
                    break;
            }
        } catch (Exception ex) {
            showError("Invalid input: " + ex.getMessage());
        }
    }

    // ─── Helper: Type Guard ────────────────────────────────────────────────

    /**
     * Throws an error dialog and exception if the plan is not of the required type.
     *
     * @param plan      The plan to check
     * @param type      Required class (PersonalPlan or ProPlan)
     * @param typeName  Human-readable name for the error message
     */
    private void requireType(AIModel plan, Class<?> type, String typeName) {
        if (!type.isInstance(plan)) {
            showError("This action is only available for " + typeName + " Plans.");
            throw new IllegalArgumentException("Wrong plan type.");
        }
    }

    // ─── Helper: Token Dialog ──────────────────────────────────────────────

    /**
     * Shows an input dialog asking for token count.
     * @return Parsed integer, or -1 if cancelled/invalid.
     */
    private int getTokenInput() {
        String input = JOptionPane.showInputDialog(this,
            "Enter expected output length (tokens):",
            "Token Count", JOptionPane.QUESTION_MESSAGE);
        if (input == null || input.trim().isEmpty()) return -1;
        try {
            return Integer.parseInt(input.trim());
        } catch (NumberFormatException e) {
            showError("Please enter a valid integer for tokens.");
            return -1;
        }
    }

    // ─── Validation Helpers ────────────────────────────────────────────────

    private String validateName(String s) {
        if (s.isEmpty()) throw new IllegalArgumentException("Model name cannot be empty.");
        return s;
    }

    private double validatePrice(String s) {
        double v = Double.parseDouble(s);
        if (v < 0) throw new IllegalArgumentException("Price cannot be negative.");
        return v;
    }

    private int validateParams(String s) {
        int v = Integer.parseInt(s);
        if (v <= 0) throw new IllegalArgumentException("Parameters must be > 0.");
        return v;
    }

    private String validateContext(String s) {
        if (s.isEmpty()) throw new IllegalArgumentException("Context window cannot be empty.");
        return s;
    }

    // ─── UI Output / Status Helpers ────────────────────────────────────────

    /** Writes timestamped message to the console area. */
    private void output(String msg) {
        String ts = LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss"));
        outputArea.setText("[" + ts + "]\n" + msg);
        // Auto-scroll to top so user sees the result immediately
        outputArea.setCaretPosition(0);
    }

    /** Updates the status bar label text and color. */
    private void setStatus(String msg, Color color) {
        lblStatus.setText("●  " + msg);
        lblStatus.setForeground(color);
    }

    /** Updates the plan counter badge in the header. */
    private void updatePlanCounter() {
        int n = plans.size();
        lblPlanCount.setText(n + (n == 1 ? " Plan" : " Plans"));
    }

    /** Shows a styled error dialog. */
    private void showError(String msg) {
        JOptionPane.showMessageDialog(this, msg, "⚠  Error", JOptionPane.ERROR_MESSAGE);
        setStatus("Error: " + msg, ACCENT_RED);
    }

    // ─── UI Building Helpers ───────────────────────────────────────────────

    /**
     * Creates a styled dark card panel with a titled top border.
     * @param title Section title shown in the top-left corner
     */
    private JPanel createCard(String title) {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(BG_PANEL);
        panel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(BORDER_COLOR, 1, true),
            BorderFactory.createCompoundBorder(
                new EmptyBorder(4, 4, 4, 4),
                BorderFactory.createTitledBorder(
                    BorderFactory.createEmptyBorder(),
                    title,
                    TitledBorder.LEFT, TitledBorder.TOP,
                    FONT_SECTION, ACCENT_BLUE
                )
            )
        ));
        return panel;
    }

    /** Returns a standard GridBagConstraints for form layouts. */
    private GridBagConstraints defaultGbc() {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets   = new Insets(5, 10, 5, 10);
        gbc.fill     = GridBagConstraints.HORIZONTAL;
        gbc.weightx  = 1.0;
        gbc.gridwidth = 1;
        return gbc;
    }

    /**
     * Adds a styled label + text field row to a panel.
     * @return The created text field (for storing reference)
     */
    private JTextField addStyledField(JPanel panel, GridBagConstraints gbc,
                                       String labelText, int row) {
        gbc.gridx = 0; gbc.gridy = row; gbc.gridwidth = 1;
        panel.add(styledLabel(labelText), gbc);
        JTextField tf = styledTextField();
        gbc.gridx = 1;
        panel.add(tf, gbc);
        return tf;
    }

    /** Adds only a label (no field) at the given row. */
    private void addStyledLabelOnly(JPanel panel, GridBagConstraints gbc,
                                     String text, int row) {
        gbc.gridx = 0; gbc.gridy = row; gbc.gridwidth = 1;
        panel.add(styledLabel(text), gbc);
    }

    /** Creates a styled dark-themed text field. */
    private JTextField styledTextField() {
        JTextField tf = new JTextField(15);
        tf.setFont(FONT_INPUT);
        tf.setBackground(BG_CARD);
        tf.setForeground(TEXT_PRIMARY);
        tf.setCaretColor(ACCENT_BLUE);
        tf.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(BORDER_COLOR, 1, true),
            new EmptyBorder(5, 9, 5, 9)
        ));
        // Highlight border on focus
        tf.addFocusListener(new FocusAdapter() {
            @Override public void focusGained(FocusEvent e) {
                tf.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(ACCENT_BLUE, 1, true),
                    new EmptyBorder(5, 9, 5, 9)));
            }
            @Override public void focusLost(FocusEvent e) {
                tf.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(BORDER_COLOR, 1, true),
                    new EmptyBorder(5, 9, 5, 9)));
            }
        });
        return tf;
    }

    /** Creates a styled muted label. */
    private JLabel styledLabel(String text) {
        JLabel lbl = new JLabel(text);
        lbl.setFont(FONT_LABEL);
        lbl.setForeground(TEXT_MUTED);
        return lbl;
    }

    /**
     * Creates a rounded pill-style button with the given label and accent color.
     * Hover effect lightens the button background.
     */
    private JButton makeRoundButton(String text, Color accent, Color textColor) {
        JButton btn = new JButton(text) {
            @Override protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                                    RenderingHints.VALUE_ANTIALIAS_ON);
                // Fill rounded rectangle with accent color
                g2.setColor(getModel().isRollover()
                    ? accent.brighter()       // Hover: brighter
                    : accent);
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 10, 10);
                g2.dispose();
                super.paintComponent(g);
            }
        };
        btn.setFont(new Font("Segoe UI", Font.BOLD, 12));
        btn.setForeground(textColor.equals(BG_DARK) ? Color.WHITE : textColor);
        btn.setFocusPainted(false);
        btn.setBorderPainted(false);
        btn.setContentAreaFilled(false);  // We paint manually above
        btn.setOpaque(false);
        btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btn.setPreferredSize(new Dimension(200, 36));
        return btn;
    }

    /** Creates a thin horizontal separator line for visual grouping. */
    private JSeparator makeSeparator() {
        JSeparator sep = new JSeparator();
        sep.setForeground(BORDER_COLOR);
        sep.setBackground(BG_PANEL);
        return sep;
    }

    /**
     * Extracts all JButton children from a panel in order of addition.
     * Used to bind action listeners to buttons in buildInputPanel().
     */
    private Component[] getButtonsFrom(JPanel panel) {
        java.util.List<Component> btns = new java.util.ArrayList<>();
        for (Component c : panel.getComponents()) {
            if (c instanceof JButton) btns.add(c);
        }
        return btns.toArray(new Component[0]);
    }

    // ─── Entry Point ───────────────────────────────────────────────────────

    /**
     * Main method — launches the GUI on the Swing Event Dispatch Thread.
     * Using invokeLater ensures thread-safe UI initialisation.
     */
    public static void main(String[] args) {
        // Apply system look-and-feel as a base, then override with custom styles
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ignored) { /* Fall back to default Swing L&F */ }

        SwingUtilities.invokeLater(() -> new SubscriptionGUI());
    }
}
