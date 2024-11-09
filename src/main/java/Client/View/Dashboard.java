package Client.View;

import Client.ClientRun;

import javax.swing.*;
import java.awt.*;

public class Dashboard extends JFrame {
    private JPanel mainPanel;

    public Dashboard() {
        setTitle("Chat App - Dashboard");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        mainPanel.setBackground(new Color(245, 245, 245));

        JLabel titleLabel = new JLabel("Welcome to the Dashboard", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(new Color(0, 102, 204));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 20, 0));
        mainPanel.add(titleLabel, BorderLayout.NORTH);

        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new GridLayout(2, 2, 15, 15));
        contentPanel.setBackground(new Color(245, 245, 245));

        JButton profileButton = createStyledButton("Profile", new Color(0, 153, 255));
        JButton playButton = createStyledButton("Play", new Color(0, 153, 204));
        JButton notificationsButton = createStyledButton("Setting", new Color(102, 204, 255));
        JButton logoutButton = createStyledButton("Logout", new Color(255, 102, 102));

        contentPanel.add(profileButton);
        contentPanel.add(playButton);
        contentPanel.add(notificationsButton);
        contentPanel.add(logoutButton);

        mainPanel.add(contentPanel, BorderLayout.CENTER);

        profileButton.addActionListener(e -> {
            ClientRun.closeScene(ClientRun.SceneName.DASHBOARD);
            ClientRun.navigateScene(ClientRun.SceneName.PROFILE);
        });

        logoutButton.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "Logging out...");
            ClientRun.closeScene(ClientRun.SceneName.DASHBOARD);
            ClientRun.navigateScene(ClientRun.SceneName.LOGIN);
        });

        playButton.addActionListener(e -> {
            ClientRun.closeScene(ClientRun.SceneName.DASHBOARD);
            ClientRun.navigateScene(ClientRun.SceneName.PLAY_OPTION);
        });

        add(mainPanel);
    }

    private JButton createStyledButton(String text, Color color) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setForeground(Color.WHITE);
        button.setBackground(color);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createLineBorder(color.darker()));
        button.setPreferredSize(new Dimension(120, 50));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.setOpaque(true);
        button.setBorderPainted(false);

        button.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(color.darker(), 1),
                BorderFactory.createEmptyBorder(5, 15, 5, 15)
        ));

        return button;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Dashboard().setVisible(true));
    }
}
