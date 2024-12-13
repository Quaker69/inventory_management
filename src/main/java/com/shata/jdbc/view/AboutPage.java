package com.shata.jdbc.view;

import javax.swing.*;
import java.awt.*;

public class AboutPage extends JFrame {

    private static final long serialVersionUID = 1L;

    public AboutPage() {
        // Set the frame title
        super("About us");

        // Set the layout manager
        setLayout(new BorderLayout());

        // Create a label with information about the application
        JLabel aboutLabel = new JLabel("<html><h2>Inventory Management System</h2><br>" +
                                      "<p>Version 1.0</p>" +
                                      "<p>Developed by Shata company</p>" +
                                      "<p>For managing inventory and stock information.</p></html>", 
                                      SwingConstants.CENTER);

        // Set font and color for the label
        aboutLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        aboutLabel.setForeground(Color.BLACK);

        // Add the label to the frame
        add(aboutLabel, BorderLayout.CENTER);

        // Add an OK button to close the about page
        JButton buttonOK = new JButton("OK");
        buttonOK.addActionListener(e -> dispose());  // Close the about page on click

        add(buttonOK, BorderLayout.SOUTH);

        // Set the frame size and visibility
        setSize(400, 400);
        setLocationRelativeTo(null); // Center the window
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
    }
}
