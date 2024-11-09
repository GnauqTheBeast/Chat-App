package Server;

import Server.Controller.Controller;
import Server.Model.Room;

import java.io.*;
import java.net.Socket;

import static Server.Server.clientThreads;
import static Server.Server.roomsList;

public class ClientThread extends Thread {
    private final Socket clientSocket;
    private final Controller controller;
    private BufferedReader in;
    private PrintWriter os;

    public ClientThread(Socket clientSocket) {
        this.clientSocket = clientSocket;
        this.controller = new Controller();

        try {
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            os = new PrintWriter(new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream())), true);     } catch (IOException e) {
        }
    }

    @Override
    public void run() {
        try {
            os.println("Hello user"); // Welcome message

            String message;

            while ((message = in.readLine()) != null) {
                System.out.println("Received from client: " + message);
                String response = processMessage(message);
                sendMessage(response);
            }
        } catch (IOException e) {
            System.out.println("Connection error: " + e.getMessage());
        } finally {
            closeResources();
            System.out.println("Client disconnected.");
        }
    }

    public void sendMessage(String message) {
        os.println(message);
    }

    public String processMessage(String message) {
        if (message == null) {
            return "Message command is null.";
        }

        // message format is "LOGIN|<username>|<password>"
        if (message.startsWith("LOGIN")) {
            String[] parts = message.split("\\|");
            if (parts.length != 3) {
                return "INVALID_FORMAT";
            }

            String username = parts[1];
            String password = parts[2];

            String loginResponse = controller.handleLogin(username, password);

            if (loginResponse.startsWith("LOGIN|LOGIN_SUCCESS")) {
                clientThreads.putIfAbsent(username, this);
                System.out.println(clientThreads.get(username));
            }

            return loginResponse;
        }

        // message format is "REGISTER|<username>|<password>|<email>|<full_name>"
        if (message.startsWith("REGISTER")) {
            String[] parts = message.split("\\|");
            if (parts.length != 5) {
                return "INVALID_FORMAT";
            }
            String username = parts[1];
            String password = parts[2];
            String email = parts[3];
            String full_name = parts[4];
            String registerResponse = controller.handleRegister(username, password, email, full_name);

            if ("REGISTER|REGISTER_SUCCESS".equals(registerResponse)) {
                clientThreads.put(username, this); // 'this' refers to the current ClientSocketHandler instance
            }
            return registerResponse;
        }

        // message format is "CREATE_ROOM|<room_ID>|<room_owner_username>"
        if (message.startsWith("CREATE_ROOM")) {
            String[] parts = message.split("\\|");
            if (parts.length != 3) {
                return "INVALID_FORMAT";
            }
            String room_ID = parts[1];
            String room_owner_username = parts[2];
            roomsList.add(new Room(room_ID, room_owner_username));

            return "CREATE_ROOM|SUCCESS";
        }

        // message format is "JOIN_ROOM|<room_ID>|<attendance_username>|<attendance_user_email>"
        if (message.startsWith("JOIN_ROOM")) {
            String[] parts = message.split("\\|");
            if (parts.length != 4) {
                return "INVALID_FORMAT";
            }
            String room_ID = parts[1];
            String attendance_username = parts[2];
            String attendance_email = parts[3];

            for (Room room : roomsList) {
                if (room.getRoomId().equals(room_ID)) {
                    room.addPlayer(attendance_username);

                    String room_owner = room.getOwner();
                    ClientThread ownerThreadHandler = clientThreads.get(room_owner);
                    if (ownerThreadHandler != null) {
                        String notificationMessage = String.format("NOTIFY_OWNER_JOIN_ROOM|%s|%s|%s", attendance_username, attendance_email, room_ID);
                        ownerThreadHandler.sendMessage(notificationMessage);
                    }
                    return String.format("JOIN_ROOM|SUCCESS|%s|%s", room.getOwner(), room_ID);
                }
            }
            return "JOIN_ROOM|FAILURE";
        }

        return "Unknown command.";
    }

    private void closeResources() {
        try {
            if (in != null) in.close();
            if (os != null) os.close();
            if (clientSocket != null && !clientSocket.isClosed()) clientSocket.close();
        } catch (IOException e) {
            System.err.println("Error closing resources: " + e.getMessage());
        }
    }
}
