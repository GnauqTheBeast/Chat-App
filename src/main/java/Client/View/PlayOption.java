package Client.View;

import Client.ClientRun;

import javax.swing.*;
import java.awt.*;

import Client.Controller.ClientSocketHandler;
import Client.Model.Room;
import com.mysql.cj.xdevapi.Client;

import static Client.View.RoomView.setPlayerInfo;


public class PlayOption extends JFrame {

    private JButton createRoomButton;
    private JButton joinRoomButton;
    private JButton backButton;

    public PlayOption() {
        setTitle("Play Options");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Set up the layout for the window
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout(10, 10));
        mainPanel.setBackground(new Color(245, 245, 245));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Adds padding around the whole panel

        // Title label
        JLabel titleLabel = new JLabel("Choose Your Option", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(new Color(0, 102, 204));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
        mainPanel.add(titleLabel, BorderLayout.NORTH);

        // Content panel with buttons
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new GridLayout(3, 1, 10, 10));
        contentPanel.setBackground(new Color(245, 245, 245));

        createRoomButton = createStyledButton("Create Room", new Color(34, 139, 34));
        joinRoomButton = createStyledButton("Join Room", new Color(30, 144, 255));
        backButton = createStyledButton("Back", new Color(255, 99, 71));

        contentPanel.add(createRoomButton);
        contentPanel.add(joinRoomButton);
        contentPanel.add(backButton);

        mainPanel.add(contentPanel, BorderLayout.CENTER);

        createRoomButton.addActionListener(e -> createRoom());
        joinRoomButton.addActionListener(e -> showJoinRoomField());
        backButton.addActionListener(e -> goBack());

        getContentPane().add(mainPanel);
    }

    private void createRoom() {
        ClientRun.currentRoom = new Room();
        ClientRun.currentRoom.createRoom(ClientRun.currentUser.getUsername());
        ClientRun.clientHandler.sendMessage(String.format("CREATE_ROOM|%s|%s", ClientRun.currentRoom.getID(), ClientRun.currentRoom.getOwner())) ;
        ClientRun.closeScene(ClientRun.SceneName.PLAY_OPTION);
        ClientRun.navigateScene(ClientRun.SceneName.ROOM);
        setPlayerInfo(ClientRun.currentUser);
    }

    private void showJoinRoomField() {
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new GridBagLayout());
        contentPanel.setBackground(new Color(245, 245, 245));
        contentPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Add padding around the panel

        JLabel roomIdLabel = new JLabel("Enter Room ID:");
        roomIdLabel.setFont(new Font("Arial", Font.PLAIN, 14));

        JTextField roomIdField = new JTextField(15);
        JButton submitRoomIdButton = createStyledButton("Join", new Color(30, 144, 255));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 0, 10, 0); // Margin between components

        gbc.gridx = 0;
        gbc.gridy = 0;
        contentPanel.add(roomIdLabel, gbc);

        gbc.gridy = 1;
        contentPanel.add(roomIdField, gbc);

        gbc.gridy = 2;
        contentPanel.add(submitRoomIdButton, gbc);

        getContentPane().removeAll();
        getContentPane().add(contentPanel);
        revalidate();
        repaint();

        submitRoomIdButton.addActionListener(e -> {
            String roomId = roomIdField.getText();
            if (roomId.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please enter a valid Room ID.");
                return;
            }

            if (roomId.length() != 6) {
                JOptionPane.showMessageDialog(this, "Length of Room ID is invalid.");
            }

            ClientRun.clientHandler.sendMessage(String.format("JOIN_ROOM|%s|%s|%s", roomId, ClientRun.currentUser.getUsername(), ClientRun.currentUser.getEmail()));
            ClientRun.closeScene(ClientRun.SceneName.PLAY_OPTION);
        });
    }

    private void goBack() {
        ClientRun.closeScene(ClientRun.SceneName.PLAY_OPTION);
        ClientRun.navigateScene(ClientRun.SceneName.DASHBOARD);
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
                BorderFactory.createEmptyBorder(5, 15, 5, 15) // Adds padding inside the button
        ));

        return button;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new PlayOption().setVisible(true));
    }
}
