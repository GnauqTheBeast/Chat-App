package Client;

import View.LoginFrame;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        try (Socket mySocket = new Socket("localhost", 2003)) {
            DataOutputStream os = new DataOutputStream(mySocket.getOutputStream());
            DataInputStream is = new DataInputStream(mySocket.getInputStream());

            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter messages to send to the server (type 'exit' to quit):");

            new Thread(() -> {
                try {
                    LoginFrame login = new LoginFrame();
                    login.setVisible(true);

                    while (true) {
                        String response = is.readUTF();
                        System.out.println("Server response: " + response);
                    }
                } catch (IOException e) {
                    System.out.println("Connection to server lost: " + e);
                }
            }).start();

            String userInput;
            while (!(userInput = scanner.nextLine()).equalsIgnoreCase("exit")) {
                os.writeUTF(userInput);
                os.flush();
            }

            scanner.close();

        } catch (UnknownHostException e) {
            System.out.println("Unknown host: " + e);
        } catch (IOException e) {
            System.out.println("I/O error: " + e);
        }
    }
}
