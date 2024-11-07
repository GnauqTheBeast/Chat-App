package Client.View;

import Client.ClientRun;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Profile extends JFrame {
    private JPanel mainPanel;
    private JLabel usernameLabel;
    private JLabel emailLabel;
    private JButton editButton;
    private JButton changePasswordButton;
    private JButton logoutButton;

    public Profile() {
        setTitle("Profile");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        mainPanel.setBackground(new Color(245, 245, 245));

        JLabel titleLabel = new JLabel("User Profile", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(new Color(0, 102, 204));
        mainPanel.add(titleLabel, BorderLayout.NORTH);

        JPanel profileDetailsPanel = new JPanel();
        profileDetailsPanel.setLayout(new GridLayout(3, 2, 10, 10));
        profileDetailsPanel.setBackground(new Color(245, 245, 245));

        JLabel usernameTextLabel = new JLabel("Username:");
        usernameTextLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        usernameLabel = new JLabel("JohnDoe123"); // Placeholder for username
        usernameLabel.setFont(new Font("Arial", Font.PLAIN, 14));

        JLabel emailTextLabel = new JLabel("Email:");
        emailTextLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        emailLabel = new JLabel("johndoe123@example.com");
        emailLabel.setFont(new Font("Arial", Font.PLAIN, 14));

        profileDetailsPanel.add(usernameTextLabel);
        profileDetailsPanel.add(usernameLabel);
        profileDetailsPanel.add(emailTextLabel);
        profileDetailsPanel.add(emailLabel);

        mainPanel.add(profileDetailsPanel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());
        buttonPanel.setBackground(new Color(245, 245, 245));

        // Edit profile button
        editButton = new JButton("Edit Profile");
        editButton.setPreferredSize(new Dimension(150, 30));
        editButton.setBackground(new Color(0, 153, 255));
        editButton.setForeground(Color.WHITE);
        editButton.setFont(new Font("Arial", Font.BOLD, 14));
        editButton.setFocusPainted(false);
        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(Profile.this, "Edit Profile functionality");
            }
        });

        changePasswordButton = new JButton("Change Password");
        changePasswordButton.setPreferredSize(new Dimension(150, 30));
        changePasswordButton.setBackground(new Color(255, 99, 71));
        changePasswordButton.setForeground(Color.WHITE);
        changePasswordButton.setFont(new Font("Arial", Font.BOLD, 14));
        changePasswordButton.setFocusPainted(false);
        changePasswordButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(Profile.this, "Change Password functionality");
            }
        });

        // Logout button
        logoutButton = new JButton("Logout");
        logoutButton.setPreferredSize(new Dimension(150, 30));
        logoutButton.setBackground(new Color(255, 69, 0));
        logoutButton.setForeground(Color.WHITE);
        logoutButton.setFont(new Font("Arial", Font.BOLD, 14));
        logoutButton.setFocusPainted(false);
        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Handle logout logic
                JOptionPane.showMessageDialog(Profile.this, "Logging out...");
                ClientRun.closeScene(ClientRun.SceneName.PROFILE);
                ClientRun.navigateScene(ClientRun.SceneName.LOGIN);
            }
        });

        buttonPanel.add(editButton);
        buttonPanel.add(changePasswordButton);
        buttonPanel.add(logoutButton);

        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(mainPanel);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Profile().setVisible(true);
            }
        });
    }
}
