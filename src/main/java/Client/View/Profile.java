package Client.View;

import Client.ClientRun;

import javax.swing.*;
import java.awt.*;

public class Profile extends JFrame {
    private JPanel mainPanel;
    private JLabel usernameLabel;
    private JLabel emailLabel;
    private JButton editButton;
    private JButton changePasswordButton;
    private JButton logoutButton;
    private JButton backButton;

    public Profile() {
        setTitle("Profile");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        mainPanel.setBackground(new Color(245, 245, 245));

        backButton = new JButton("Back");
        backButton.setPreferredSize(new Dimension(80, 30));
        backButton.setBackground(new Color(128, 128, 128));
        backButton.setForeground(Color.WHITE);
        backButton.setFont(new Font("Arial", Font.BOLD, 14));
        backButton.setFocusPainted(false);
        backButton.addActionListener(e -> {
            ClientRun.closeScene(ClientRun.SceneName.PROFILE);
            ClientRun.navigateScene(ClientRun.SceneName.DASHBOARD);
        });

        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBackground(new Color(245, 245, 245));
        topPanel.add(backButton, BorderLayout.WEST);

        JLabel titleLabel = new JLabel("User Profile", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(new Color(0, 102, 204));
        topPanel.add(titleLabel, BorderLayout.CENTER);

        mainPanel.add(topPanel, BorderLayout.NORTH);

        JPanel profileDetailsPanel = new JPanel();
        profileDetailsPanel.setLayout(new GridLayout(3, 2, 10, 10));
        profileDetailsPanel.setBackground(new Color(245, 245, 245));

        JLabel usernameTextLabel = new JLabel("Username:");
        usernameTextLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        usernameLabel = new JLabel();
        usernameLabel.setFont(new Font("Arial", Font.PLAIN, 14));

        JLabel emailTextLabel = new JLabel("Email:");
        emailTextLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        emailLabel = new JLabel();
        emailLabel.setFont(new Font("Arial", Font.PLAIN, 14));

        profileDetailsPanel.add(usernameTextLabel);
        profileDetailsPanel.add(usernameLabel);
        profileDetailsPanel.add(emailTextLabel);
        profileDetailsPanel.add(emailLabel);

        mainPanel.add(profileDetailsPanel, BorderLayout.CENTER);

        add(mainPanel);
    }

    public void updateProfileInfo() {
        if (ClientRun.currentUser != null) {
            usernameLabel.setText(ClientRun.currentUser.getUsername());
            emailLabel.setText(ClientRun.currentUser.getEmail());
        }
    }
}
