package com.marcelohdez.bedroom.main;

import com.marcelohdez.bedroom.enums.SetTime;
import com.marcelohdez.bedroom.settings.SettingsDialog;
import com.marcelohdez.bedroom.util.Ops;
import com.marcelohdez.bedroom.util.Theme;
import com.marcelohdez.bedroom.util.Time;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class UI extends JPanel implements ActionListener, KeyListener {

    private final Window parent;
    public static boolean freeze = true; // Ignore input of change order and set break actions

    // Components used outside of constructor
    private static final JTextArea stats = new JTextArea("Please clock in.\n\n");
    private static final JButton breakButton = new JButton("Set Break");
    private static final JButton addOrder = new JButton("Add Order");

    // ======= Public reusable colors & fonts =======
    public static Font boldText = new Font(Font.SANS_SERIF, Font.BOLD, 14);

    // Get colors for UI from user's preferences
    public static Color textColor = loadColorOf("text", 240);
    // Get color of button text
    public static Color buttonTextColor = loadColorOf("buttonText", 240);
    // Get color of buttons
    public static Color buttonColor = loadColorOf("button", 80);
    // Get color of background
    public static Color bg = loadColorOf("bg", 64);

    public UI(Window parent) { // Set UI's properties

        this.parent = parent;

        setFocusable(true);
        addKeyListener(this);

        // Set components' properties
        stats.setEditable(false);
        stats.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 14));
        stats.addKeyListener(this);
        addOrder.addKeyListener(this);
        addOrder.addActionListener(this);
        breakButton.addKeyListener(this);
        breakButton.addActionListener(this);
        breakButton.setToolTipText("<html><b>Currently no break is set</b></html>"); // Default tooltip

        // Set colors
        colorComponents();

        // Add components
        add(breakButton);
        add(addOrder);
        add(stats);

        Main.updateStats();

    }

    public void actionPerformed(ActionEvent e) {

        switch (e.getActionCommand()) {
            case "Add Order" -> Main.changeOrders(1);
            case "Set Break" -> enterBreak();
        }

    }

    private void enterBreak() {
        if (!freeze) {
            Main.enterBreakWnd.centerOnMainWindow(); // Set to current center of main window
            Main.enterBreakWnd.setUITime(SetTime.CURRENT);
            Main.enterBreakWnd.setVisible(true);
        }
    }

    public void keyTyped(KeyEvent e) {}
    public void keyReleased(KeyEvent e) {}

    public void keyPressed(KeyEvent e) {
        System.out.println(e.getKeyCode());
        // ======= Shortcuts =======
        switch (e.getKeyCode()) {
            case KeyEvent.VK_DOWN ->
                    Main.changeOrders(-1); // Remove orders with BckSpc & Down Arrow
            case KeyEvent.VK_0 -> enterBreak();             // Set break times with 0
            case KeyEvent.VK_UP -> Main.changeOrders(1); // Add orders with up arrow
            case KeyEvent.VK_DELETE, KeyEvent.VK_ESCAPE, KeyEvent.VK_BACK_SPACE ->
                    new SettingsDialog(new int[]{parent.getX(), parent.getY(),
                            parent.getWidth(), parent.getHeight()}); // Open settings with Del or Esc keys
        }
    }

    static void display(String message) {

        stats.setText(message);
        setTooltips();

    }

    static void setTooltips() {

        // Add Order's tool tips
        double neededForTarget = (double) Main.totalSecClockedIn/3600 * Main.target;
        StringBuilder sb;
        if (neededForTarget > Main.orders) { // Tell us how many orders we need to reach our target

            sb = new StringBuilder();
            int amountMissing = (int) Math.round(Math.ceil(neededForTarget - Main.orders));
            addOrder.setToolTipText(sb.append("<html><b>You are ")
                    .append(amountMissing)
                    .append(" order")
                    .append(Ops.isPlural(amountMissing))
                    .append(" behind your hourly target</b></html>").toString());

        } else addOrder.setToolTipText("<html><b>You are on track with your hourly target</b></html>");

        // Set Break's tool tips
        if (Main.breakTimesChosen) { // If we have chosen break times, change the tooltip to them.

            sb = new StringBuilder();
            sb.append("<html><b>Current: ");
            Time.append12HrTimeTo(sb, Main.breakInTime);
            sb.append("-");
            Time.append12HrTimeTo(sb, Main.breakOutTime);
            sb.append("</b></html>");

            breakButton.setToolTipText(sb.toString());

        }

    }

    private static Color loadColorOf(String component, int def) {

        return new Color(Main.userPrefs.getInt(component + "Red", def),
                Main.userPrefs.getInt(component + "Green", def),
                Main.userPrefs.getInt(component + "Blue", def));

    }

    public void reloadColors() {

        textColor = loadColorOf("text", 240);
        buttonTextColor = loadColorOf("buttonText", 240);
        buttonColor = loadColorOf("button", 80);
        bg = loadColorOf("bg", 64);

        this.colorComponents();

    }

    private void colorComponents() {

        Theme.colorThis(breakButton);
        Theme.colorThis(addOrder);
        Theme.colorThis(stats);
        setBackground(bg);

    }

    void sizeButtons() {

        // Get the largest width * 1.2 for some buffer
        int length = (int) ((Math.max(addOrder.getWidth(), breakButton.getWidth())) * 1.2);
        // Set the buttons to that width, and half that for height to make identical rectangles
        addOrder.setPreferredSize(new Dimension(length, length/2));
        breakButton.setPreferredSize(new Dimension(length, length/2));

    }

}
