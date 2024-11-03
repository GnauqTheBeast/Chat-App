package Client.View;

import Client.ClientRun;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.net.Socket;

public class Dashboard extends JFrame {
    private JPanel panel1;

    public Dashboard() {
        setTitle("Chat App");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Main panel
        panel1 = new JPanel();
        panel1.setLayout(new BorderLayout());
        panel1.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Top label
        JLabel titleLabel = new JLabel("Welcome to the Dashboard", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        titleLabel.setForeground(new Color(0, 102, 204));
        panel1.add(titleLabel, BorderLayout.NORTH);

        // Center content area
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new GridLayout(3, 3, 15, 15));
        contentPanel.setBackground(new Color(245, 245, 245));

        // Placeholder buttons for different sections
        JButton profileButton = new JButton("Profile");
        JButton settingsButton = new JButton("Settings");
        JButton notificationsButton = new JButton("Notifications");
        JButton logoutButton = new JButton("Logout");

        // Add buttons to the content panel
        contentPanel.add(profileButton);
        contentPanel.add(settingsButton);
        contentPanel.add(notificationsButton);
        contentPanel.add(logoutButton);

        panel1.add(contentPanel, BorderLayout.CENTER);

        logoutButton.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "Logging out...");
            ClientRun.closeScene(ClientRun.SceneName.DASHBOARD);
            ClientRun.navigateScene(ClientRun.SceneName.LOGIN);
        });

        add(panel1);
    }

    public static void main(String[] args) {
    }
}
