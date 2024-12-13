package com.shata.jdbc.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginFrame extends JFrame {

    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;

    public LoginFrame() {
        // Set the window title and default close operation
        setTitle("Login");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Set the window size and center it on the screen
        setSize(400, 250);
        setLocationRelativeTo(null);

        // Use a custom look and feel for the window
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Set the background color
        getContentPane().setBackground(new Color(60, 63, 65));

        // Create a panel for the login form with a GridBagLayout for better control
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        panel.setBackground(new Color(60, 63, 65));  // Set background color to match the frame
        add(panel);

        // Create a GridBagConstraints object to manage positioning
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);  // Add padding around components

        // Title Label
        JLabel titleLabel = new JLabel("Login", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(Color.BLACK);
        gbc.gridwidth = 2;  // Span across two columns
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(titleLabel, gbc);

        // Username Label
        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        usernameLabel.setForeground(Color.WHITE);
        gbc.gridwidth = 1;
        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(usernameLabel, gbc);

        // Username Field
        usernameField = new JTextField(20);
        usernameField.setFont(new Font("Arial", Font.PLAIN, 14));
        usernameField.setBackground(new Color(220, 220, 220));
        usernameField.setBorder(BorderFactory.createLineBorder(new Color(100, 100, 100)));
        gbc.gridx = 1;
        gbc.gridy = 1;
        panel.add(usernameField, gbc);

        // Password Label
        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        passwordLabel.setForeground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(passwordLabel, gbc);

        // Password Field
        passwordField = new JPasswordField(20);
        passwordField.setFont(new Font("Arial", Font.PLAIN, 14));
        passwordField.setBackground(new Color(220, 220, 220));
        passwordField.setBorder(BorderFactory.createLineBorder(new Color(100, 100, 100)));
        gbc.gridx = 1;
        gbc.gridy = 2;
        panel.add(passwordField, gbc);

        // Login Button
        loginButton = new JButton("Login");
        loginButton.setFont(new Font("Arial", Font.BOLD, 16));
        loginButton.setBackground(new Color(34, 167, 240));  // Blue color for the button
        loginButton.setForeground(Color.BLACK);
        loginButton.setFocusPainted(false);
        loginButton.setBorder(BorderFactory.createLineBorder(new Color(34, 167, 240)));
        loginButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        // Hover effect for the login button
        loginButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                loginButton.setBackground(new Color(28, 140, 211));  // Darken the button color when hovered
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                loginButton.setBackground(new Color(34, 167, 240));  // Reset the button color when the mouse leaves
            }
        });

        // Action listener for the login button
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());

                // Validate credentials (admin/admin)
                if (username.equals("admin") && password.equals("admin")) {
                    // Launch the StockFrameMain if valid login
                    dispose();  // Close login frame
                    new StockFrameMain().setVisible(true);  // Open the main frame
                } else {
                    // Show an error message if invalid credentials
                    JOptionPane.showMessageDialog(LoginFrame.this, "Invalid credentials, please try again.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Position the login button at the bottom
        gbc.gridwidth = 2;
        gbc.gridx = 0;
        gbc.gridy = 3;
        panel.add(loginButton, gbc);
    }
}
