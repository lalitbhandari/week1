package week1.week20;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class AISubscriptionManagementSystem extends JFrame {

    // COLOUR PALETTE
    private static final Color CLR_NAV_BG = new Color(15, 21, 46);
    private static final Color CLR_NAV_HOVER = new Color(35, 47, 88);
    private static final Color CLR_NAV_SELECTED = new Color(67, 97, 238);
    private static final Color CLR_HEADER = new Color(67, 97, 238);
    private static final Color CLR_CONTENT_BG = new Color(238, 240, 250);
    private static final Color CLR_CARD = Color.WHITE;
    private static final Color CLR_BORDER = new Color(210, 215, 235);
    private static final Color CLR_PRIMARY = new Color(67, 97, 238);
    private static final Color CLR_SUCCESS = new Color(6, 214, 160);
    private static final Color CLR_DANGER = new Color(239, 71, 111);

    // FONTS
    private static final Font FNT_TITLE = new Font("Segoe UI", Font.BOLD, 20);
    private static final Font FNT_HEADING = new Font("Segoe UI", Font.BOLD, 16);
    private static final Font FNT_BODY = new Font("Segoe UI", Font.PLAIN, 13);
    private static final Font FNT_BTN = new Font("Segoe UI", Font.BOLD, 13);

    // UI COMPONENTS
    private CardLayout cardLayout;
    private JPanel contentArea;
    private String currentPage = "Home";
    private final ArrayList<JButton> navButtons = new ArrayList<>();

    // Home Page
    private JTable homeTable;
    private JComboBox<String> homeFilterCombo;
    private JTextField homeSearchField;

    // Add Plan Page
    private JComboBox<String> addTypeCombo;
    private JTextField addModelField, addPriceField, addParamField, addContextField, addTokensField;
    private JTextField addDailyLimitField, addSupportField, addSlotsField;
    private JCheckBox addApiCheckBox;
    private JPanel extraFieldsPanel;

    // Tables
    private JTable personalTable, proTable, teamTable;

    // Prompt Page
    private JTextField promptIdField, promptTokenField;
    private JTextArea promptInputArea, promptOutputArea;

    // Team Page
    private JTextField teamMemberNameField;
    private JTextArea teamMembersDisplay;

    // Reports Page
    private JTextArea reportArea;
    private JComboBox<String> reportTypeCombo;

    public AISubscriptionManagementSystem() {
        setTitle("AI Subscription Management System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1250, 780);
        setMinimumSize(new Dimension(1000, 620));
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(0, 0));

        add(buildHeader(), BorderLayout.NORTH);
        add(buildSidebar(), BorderLayout.WEST);
        add(buildContent(), BorderLayout.CENTER);

        showPage("Home");
    }

    // ==================== HEADER ====================
    private JPanel buildHeader() {
        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(CLR_HEADER);
        header.setPreferredSize(new Dimension(0, 66));
        header.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 16));

        JPanel titleSide = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
        titleSide.setOpaque(false);
        titleSide.add(new JLabel("◈") {{ setFont(new Font("Segoe UI", Font.BOLD, 30)); setForeground(Color.CYAN); }});
        titleSide.add(new JLabel("AI Subscription Management System") {{ setFont(FNT_TITLE); setForeground(Color.WHITE); }});

        JPanel btnSide = new JPanel(new FlowLayout(FlowLayout.RIGHT, 8, 0));
        btnSide.setOpaque(false);
        btnSide.add(makeHeaderBtn("⌂ Home"));
        btnSide.add(makeHeaderBtn("💾 Save"));
        btnSide.add(makeHeaderBtn("⟳ Reload"));
        btnSide.add(makeHeaderBtn("✕ Exit"));

        header.add(titleSide, BorderLayout.WEST);
        header.add(btnSide, BorderLayout.EAST);
        return header;
    }

    private JButton makeHeaderBtn(String text) {
        JButton b = new JButton(text);
        b.setFont(FNT_BTN);
        b.setForeground(Color.WHITE);
        b.setBackground(new Color(255, 255, 255, 40));
        b.setFocusPainted(false);
        b.setBorderPainted(false);
        b.setContentAreaFilled(false);
        b.setBorder(BorderFactory.createEmptyBorder(9, 18, 9, 18));
        b.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        return b;
    }

    // ==================== SIDEBAR ====================
    private JPanel buildSidebar() {
        JPanel sidebar = new JPanel();
        sidebar.setLayout(new BoxLayout(sidebar, BoxLayout.Y_AXIS));
        sidebar.setBackground(CLR_NAV_BG);
        sidebar.setPreferredSize(new Dimension(220, 0));

        // Brand
        JPanel brand = new JPanel(new BorderLayout(10, 0));
        brand.setBackground(new Color(10, 14, 36));
        brand.setBorder(BorderFactory.createEmptyBorder(14, 16, 14, 16));
        brand.add(new JLabel("🤖") {{ setFont(new Font("Segoe UI", Font.PLAIN, 28)); }}, BorderLayout.WEST);
        brand.add(new JLabel("<html><b>AI SubManager</b><br><span style='color:#6878a8'>v1.0.0</span></html>"), BorderLayout.CENTER);
        sidebar.add(brand);

        JLabel menuLabel = new JLabel(" NAVIGATION");
        menuLabel.setFont(new Font("Segoe UI", Font.BOLD, 10));
        menuLabel.setForeground(new Color(80, 100, 150));
        menuLabel.setBorder(BorderFactory.createEmptyBorder(16, 12, 6, 0));
        sidebar.add(menuLabel);

        String[][] navItems = {
            {"Home", "Home", "🏠"},
            {"Add Plan", "AddPlan", "➕"},
            {"Personal Plans", "Personal", "👤"},
            {"Pro Plans", "Pro", "⭐"},
            {"Prompt Operations", "Prompt", "💬"},
            {"Team Operations", "Team", "👥"},
            {"Reports", "Reports", "📊"},
            {"About", "About", "ℹ"}
        };

        for (String[] item : navItems) {
            JButton btn = makeNavBtn(item[0], item[1], item[2]);
            navButtons.add(btn);
            sidebar.add(btn);
        }

        sidebar.add(Box.createVerticalGlue());
        return sidebar;
    }

    private JButton makeNavBtn(String label, String page, String icon) {
        JButton b = new JButton(icon + " " + label);
        b.setFont(FNT_BODY);
        b.setForeground(new Color(200, 210, 235));
        b.setBackground(CLR_NAV_BG);
        b.setOpaque(true);
        b.setBorderPainted(false);
        b.setFocusPainted(false);
        b.setHorizontalAlignment(SwingConstants.LEFT);
        b.setBorder(BorderFactory.createEmptyBorder(13, 22, 13, 12));
        b.setMaximumSize(new Dimension(Integer.MAX_VALUE, 48));
        b.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        b.addActionListener(e -> showPage(page));
        return b;
    }

    // ==================== CONTENT AREA (FIXED) ====================
    private JPanel buildContent() {
        cardLayout = new CardLayout();
        contentArea = new JPanel(cardLayout);
        contentArea.setBackground(CLR_CONTENT_BG);

        // All pages added correctly
        contentArea.add(buildHomePage(), "Home");
        contentArea.add(buildAddPlanPage(), "AddPlan");
        contentArea.add(buildPersonalPage(), "Personal");
        contentArea.add(buildProPage(), "Pro");
        contentArea.add(buildPromptPage(), "Prompt");
        contentArea.add(buildTeamPage(), "Team");
        contentArea.add(buildReportsPage(), "Reports");
        contentArea.add(buildAboutPage(), "About");

        return contentArea;
    }

    private void showPage(String page) {
        currentPage = page;
        cardLayout.show(contentArea, page);
        navButtons.forEach(JComponent::repaint);
        refreshCurrentPage();
    }

    private void refreshCurrentPage() {
        switch (currentPage) {
            case "Home": refreshHomeTable(); break;
            case "Personal": refreshPersonalTable(); break;
            case "Pro": refreshProTable(); break;
            case "Team": refreshTeamTable(); break;
        }
    }

    // ==================== HELPER METHODS ====================
    private JPanel makePage() {
        JPanel p = new JPanel(new BorderLayout(0, 16));
        p.setBackground(CLR_CONTENT_BG);
        p.setBorder(BorderFactory.createEmptyBorder(22, 22, 22, 22));
        return p;
    }

    private JPanel makePageHeader(String title, String subtitle) {
        JPanel header = new JPanel(new BorderLayout(0, 4));
        header.setOpaque(false);
        JLabel titleLbl = new JLabel(title);
        titleLbl.setFont(FNT_TITLE);
        titleLbl.setForeground(new Color(22, 30, 60));
        JLabel subLbl = new JLabel(subtitle);
        subLbl.setFont(FNT_BODY);
        subLbl.setForeground(new Color(110, 120, 160));

        JPanel text = new JPanel(new BorderLayout(0, 2));
        text.setOpaque(false);
        text.add(titleLbl, BorderLayout.NORTH);
        text.add(subLbl, BorderLayout.SOUTH);
        header.add(text, BorderLayout.CENTER);
        header.add(new JSeparator(), BorderLayout.SOUTH);
        return header;
    }

    private JTable makeTable(String[] cols) {
        DefaultTableModel model = new DefaultTableModel(cols, 0) {
            @Override
            public boolean isCellEditable(int r, int c) { return false; }
        };
        JTable table = new JTable(model);
        table.setRowHeight(32);
        table.setFont(FNT_BODY);
        table.setGridColor(CLR_BORDER);
        table.setShowGrid(true);
        JTableHeader header = table.getTableHeader();
        header.setFont(new Font("Segoe UI", Font.BOLD, 13));
        header.setBackground(CLR_PRIMARY);
        header.setForeground(Color.WHITE);
        return table;
    }

    private JScrollPane makeScrollPane(JComponent comp) {
        JScrollPane sp = new JScrollPane(comp);
        sp.setBorder(BorderFactory.createLineBorder(CLR_BORDER));
        return sp;
    }

    private JTextField makeField(String placeholder) {
        JTextField f = new JTextField();
        f.setFont(FNT_BODY);
        f.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(CLR_BORDER),
            BorderFactory.createEmptyBorder(7, 9, 7, 9)));
        return f;
    }

    private JButton makeBtn(String text, Color bg) {
        JButton b = new JButton(text);
        b.setFont(FNT_BTN);
        b.setForeground(Color.WHITE);
        b.setBackground(bg);
        b.setFocusPainted(false);
        b.setBorderPainted(false);
        b.setBorder(BorderFactory.createEmptyBorder(8, 18, 8, 18));
        b.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        return b;
    }

    // ==================== PAGE BUILDERS ====================

    private JPanel buildHomePage() {
        JPanel p = makePage();
        p.add(makePageHeader("🏠 Home Dashboard", "Overview of all AI subscription plans"), BorderLayout.NORTH);

        homeTable = makeTable(new String[]{"ID", "Plan Type", "Model Name", "Price (NPR)", "Parameters"});
        refreshHomeTable();

        JPanel center = new JPanel(new BorderLayout());
        center.add(makeScrollPane(homeTable), BorderLayout.CENTER);
        p.add(center, BorderLayout.CENTER);
        return p;
    }

    private void refreshHomeTable() {
        DefaultTableModel m = (DefaultTableModel) homeTable.getModel();
        m.setRowCount(0);
        m.addRow(new Object[]{1, "Personal Plan", "GPT-4o", "2999.00", "175B"});
        m.addRow(new Object[]{2, "Personal Plan", "Gemini-2", "1999.00", "70B"});
        m.addRow(new Object[]{3, "Pro Plan", "Claude-3", "6999.00", "200B"});
    }

    private JPanel buildAddPlanPage() {
        JPanel p = makePage();
        p.add(makePageHeader("➕ Add New Plan", "Create a new AI subscription plan"), BorderLayout.NORTH);

        addTypeCombo = new JComboBox<>(new String[]{"Personal Plan", "Pro Plan", "Team Plan"});
        addModelField = makeField("e.g. GPT-4o");
        addPriceField = makeField("e.g. 2999.00");
        addParamField = makeField("e.g. 175B");
        addContextField = makeField("e.g. 128000");
        addTokensField = makeField("e.g. 50000");

        JPanel form = new JPanel(new GridLayout(6, 2, 10, 10));
        form.add(new JLabel("Plan Type:")); form.add(addTypeCombo);
        form.add(new JLabel("Model Name:")); form.add(addModelField);
        form.add(new JLabel("Price (NPR):")); form.add(addPriceField);
        form.add(new JLabel("Parameters:")); form.add(addParamField);
        form.add(new JLabel("Context Window:")); form.add(addContextField);
        form.add(new JLabel("Available Tokens:")); form.add(addTokensField);

        JButton btnSubmit = makeBtn("Add Plan", CLR_SUCCESS);
        JPanel bottom = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        bottom.add(btnSubmit);

        p.add(form, BorderLayout.CENTER);
        p.add(bottom, BorderLayout.SOUTH);
        return p;
    }

    private JPanel buildPersonalPage() {
        JPanel p = makePage();
        p.add(makePageHeader("👤 Personal Plans", "Manage individual subscriptions"), BorderLayout.NORTH);
        personalTable = makeTable(new String[]{"ID", "Model", "Price", "Parameters", "Tokens", "Daily Limit"});
        refreshPersonalTable();
        p.add(makeScrollPane(personalTable), BorderLayout.CENTER);
        return p;
    }

    private void refreshPersonalTable() {
        DefaultTableModel m = (DefaultTableModel) personalTable.getModel();
        m.setRowCount(0);
        m.addRow(new Object[]{1, "GPT-4o", "2999", "175B", "50000", "100"});
    }

    private JPanel buildProPage() {
        JPanel p = makePage();
        p.add(makePageHeader("⭐ Pro Plans", "Manage professional subscriptions"), BorderLayout.NORTH);
        proTable = makeTable(new String[]{"ID", "Model", "Price", "Parameters", "Tokens", "Support"});
        refreshProTable();
        p.add(makeScrollPane(proTable), BorderLayout.CENTER);
        return p;
    }

    private void refreshProTable() {
        DefaultTableModel m = (DefaultTableModel) proTable.getModel();
        m.setRowCount(0);
        m.addRow(new Object[]{3, "Claude-3", "6999", "200B", "150000", "24/7"});
    }

    private JPanel buildPromptPage() {
        JPanel p = makePage();
        p.add(makePageHeader("💬 Prompt Operations", "Test your subscription"), BorderLayout.NORTH);

        promptInputArea = new JTextArea(10, 40);
        promptOutputArea = new JTextArea(10, 40);
        promptOutputArea.setEditable(false);

        JSplitPane split = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,
                new JScrollPane(promptInputArea), new JScrollPane(promptOutputArea));

        JButton runBtn = makeBtn("Run Prompt", CLR_SUCCESS);
        JPanel bottom = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        bottom.add(runBtn);

        p.add(split, BorderLayout.CENTER);
        p.add(bottom, BorderLayout.SOUTH);
        return p;
    }

    private JPanel buildTeamPage() {
        JPanel p = makePage();
        p.add(makePageHeader("👥 Team Operations", "Manage team members"), BorderLayout.NORTH);
        teamTable = makeTable(new String[]{"ID", "Model", "Price", "Slots", "Used"});
        refreshTeamTable();
        p.add(makeScrollPane(teamTable), BorderLayout.CENTER);
        return p;
    }

    private void refreshTeamTable() {
        DefaultTableModel m = (DefaultTableModel) teamTable.getModel();
        m.setRowCount(0);
        m.addRow(new Object[]{5, "Claude-3", "19999", "20", "3"});
    }

    private JPanel buildReportsPage() {
        JPanel p = makePage();
        p.add(makePageHeader("📊 Reports", "Analytics & Summary"), BorderLayout.NORTH);

        reportTypeCombo = new JComboBox<>(new String[]{"Full Summary", "Personal Only", "Pro Only", "Team Only"});
        reportArea = new JTextArea("Report will appear here...");
        reportArea.setEditable(false);

        JPanel top = new JPanel(new FlowLayout(FlowLayout.LEFT));
        top.add(new JLabel("Report Type: "));
        top.add(reportTypeCombo);
        top.add(makeBtn("Generate", CLR_PRIMARY));

        p.add(top, BorderLayout.NORTH);
        p.add(new JScrollPane(reportArea), BorderLayout.CENTER);
        return p;
    }

    private JPanel buildAboutPage() {
        JPanel p = makePage();
        p.add(makePageHeader("ℹ About", "Application Information"), BorderLayout.NORTH);

        JTextArea aboutText = new JTextArea("""
            AI Subscription Management System
            Version: 1.0.0 (GUI Only)
            Simple OOP Java Swing Application
            """);
        aboutText.setEditable(false);
        p.add(new JScrollPane(aboutText), BorderLayout.CENTER);
        return p;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception ignored) {}
            new AISubscriptionManagementSystem().setVisible(true);
        });
    }
}
