package Client.View;

import Client.ClientRun;
import Client.Model.User;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RoomView extends JFrame {
    private JTextArea chatArea;
    private JTextField messageField;
    private JButton sendButton;
    private static JButton playButton;
    private JButton exitRoomButton;
    private static JLabel player1Info;
    private static JLabel player2Info;
    private JLabel roomIdLabel;

    private static User player1;
    private static User player2;

    public RoomView() {
        setTitle("Game Lobby - " + ClientRun.currentUser.getUsername());
        setSize(800, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JLabel titleLabel = new JLabel("Game Lobby", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setBorder(new EmptyBorder(10, 0, 10, 0));

        roomIdLabel = new JLabel("Room ID: " + ClientRun.currentRoom.getID(), SwingConstants.CENTER);
        roomIdLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        roomIdLabel.setBorder(new EmptyBorder(0, 0, 10, 0));

        JPanel titlePanel = new JPanel(new BorderLayout());
        titlePanel.add(titleLabel, BorderLayout.NORTH);
        titlePanel.add(roomIdLabel, BorderLayout.SOUTH);

        JPanel playerInfoPanel = new JPanel(new GridLayout(3, 1, 10, 10));
        playerInfoPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

        player1Info = new JLabel("Player 1: Waiting...", SwingConstants.CENTER);
        player1Info.setFont(new Font("Arial", Font.PLAIN, 14));
        player1Info.setOpaque(true);
        player1Info.setBackground(new Color(173, 216, 230));
        player1Info.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));

        player2Info = new JLabel("Player 2: Waiting...", SwingConstants.CENTER);
        player2Info.setFont(new Font("Arial", Font.PLAIN, 14));
        player2Info.setOpaque(true);
        player2Info.setBackground(new Color(255, 182, 193));
        player2Info.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));

        playButton = new JButton("Start Game");
        playButton.setEnabled(false);
        playButton.setBackground(new Color(34, 139, 34));
        playButton.setForeground(Color.WHITE);
        playButton.setFont(new Font("Arial", Font.BOLD, 14));
        playButton.setFocusPainted(false);

        playerInfoPanel.add(player1Info);
        playerInfoPanel.add(player2Info);
        playerInfoPanel.add(playButton);

        chatArea = new JTextArea();
        chatArea.setEditable(false);
        chatArea.setLineWrap(true);
        chatArea.setFont(new Font("Arial", Font.PLAIN, 14));
        chatArea.setBorder(new EmptyBorder(10, 10, 10, 10));
        JScrollPane chatScrollPane = new JScrollPane(chatArea);

        messageField = new JTextField();
        messageField.setFont(new Font("Arial", Font.PLAIN, 14));
        sendButton = new JButton("Send");
        sendButton.setBackground(new Color(30, 144, 255));
        sendButton.setForeground(Color.WHITE);
        sendButton.setFont(new Font("Arial", Font.BOLD, 14));
        sendButton.setFocusPainted(false);

        JPanel inputPanel = new JPanel(new BorderLayout(5, 0));
        inputPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        inputPanel.add(messageField, BorderLayout.CENTER);
        inputPanel.add(sendButton, BorderLayout.EAST);

        JPanel chatPanel = new JPanel(new BorderLayout(10, 10));
        chatPanel.add(chatScrollPane, BorderLayout.CENTER);
        chatPanel.add(inputPanel, BorderLayout.SOUTH);

        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.add(playerInfoPanel, BorderLayout.WEST);
        mainPanel.add(chatPanel, BorderLayout.CENTER);

        setLayout(new BorderLayout(10, 10));
        add(titlePanel, BorderLayout.NORTH);
        add(mainPanel, BorderLayout.CENTER);

        exitRoomButton = new JButton("Exit Room");
        exitRoomButton.setBackground(new Color(255, 99, 71));
        exitRoomButton.setForeground(Color.WHITE);
        exitRoomButton.setFont(new Font("Arial", Font.BOLD, 14));
        exitRoomButton.setFocusPainted(false);
        mainPanel.add(exitRoomButton, BorderLayout.SOUTH);

        exitRoomButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ClientRun.closeScene(ClientRun.SceneName.ROOM);
                ClientRun.navigateScene(ClientRun.SceneName.DASHBOARD);
            }
        });

        sendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sendMessage();
            }
        });

        messageField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sendMessage();
            }
        });

        playButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startGame();
            }
        });
    }

    private void sendMessage() {
        String message = messageField.getText().trim();
        if (!message.isEmpty()) {
            chatArea.append(ClientRun.currentUser.getUsername() + ": " + message + "\n");
            messageField.setText("");
        }
    }

    private void startGame() {
        JOptionPane.showMessageDialog(this, "Starting the game!");
    }

    public static void setPlayerInfo(User player) {
        if (player1 == null) {
            player1 = player;
            player1Info.setText("Player 1: " + player.getUsername());
            return;
        }
        player2 = player;
        player2Info.setText("Player 2: " + player.getUsername());
        playButton.setEnabled(true);
    }
}
