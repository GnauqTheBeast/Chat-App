package Server;

import DAO.DAO;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) {
        try (ServerSocket myServer = new ServerSocket(2003)) {
            System.out.println("Server started");

            DAO dao = new DAO();

            while (true) {
                try {
                    Socket clientSocket = myServer.accept();
                    System.out.println("Server accepted & client connected");

                    DataInputStream is = new DataInputStream(clientSocket.getInputStream());
                    DataOutputStream os = new DataOutputStream(clientSocket.getOutputStream());

                    new Thread(() -> {
                        try {
                            while (true) {
                                String input = is.readUTF();
                                System.out.println("Received from client: " + input);

                                os.writeUTF(input);
                                os.writeUTF("TEST");
                                os.flush(); 
                            }
                        } catch (IOException e) {
                            System.out.println("Client disconnected: " + e);
                        } finally {
                            try {
                                clientSocket.close();
                            } catch (IOException e) {
                                System.out.println("Error closing client socket: " + e);
                            }
                        }
                    }).start(); // Start new thread for handling this client
                } catch (IOException e) {
                    System.out.println("Client connection error: " + e);
                }
            }
        } catch (IOException e) {
            System.out.println("Server error: " + e);
        }
    }
}
