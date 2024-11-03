package Server;

import Server.Controller.Controller;
import java.io.*;
import java.net.Socket;

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
                System.out.println("Received: " + message);
                String response = processMessage(message);
                os.println(response);
            }
        } catch (IOException e) {
            System.out.println("Connection error: " + e.getMessage());
        } finally {
            closeResources();
            System.out.println("Client disconnected.");
        }
    }

    public String processMessage(String message) {
        if (message == null) {
            return "Message command is null.";
        }

        if (message.startsWith("LOGIN")) {
            String[] parts = message.split(" ");
            if (parts.length == 3) { // message format is "LOGIN <username> <password>"
                String username = parts[1];
                String password = parts[2];
                return controller.handleLogin(username, password);
            }
            return "INVALID_FORMAT";
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
