package Client.Controller;

import Client.ClientRun;
import Client.Controller.Login.LoginController;
import Client.Controller.Register.RegisterController;

import java.io.*;
import java.net.Socket;

public class ClientSocketHandler {
    private Socket socket;
    private BufferedReader is;
    private PrintWriter os;
    private boolean isRunning = true;
    private final LoginController loginController;
    private final RegisterController registerController;

    public ClientSocketHandler(Socket socket) {
        this.loginController = new LoginController();
        this.registerController = new RegisterController();
        this.socket = socket;
        try {
            os = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true);
            is = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }

        ClientRun.navigateScene(ClientRun.SceneName.LOGIN);
    }

    public void listen() {
        String message;
        while (isRunning) {
            try  {
                message = is.readLine();
                System.out.println("Server sent: " + message);
                String[] splitedMessage = message.split("\\|");
                if (splitedMessage.length > 1) {
                    switch (splitedMessage[0]) {
                        case "LOGIN":
                            loginController.loginHandler(message);
                            break;
                        case "REGISTER":
                            registerController.registerHandler(message);
                        case "INVALID_FORMAT":
                            break;
                    }
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void messageReceivedHandler(String message) {
    }

    public void sendMessage(String message) {
        System.out.println("Client sent: " + message);
        os.println(message);
    }

//    public String createMessage(String message) {
//        String formatMessage = "";
//
//        switch (message) {
//            case "LOGIN":
//                formatMessage = String.format("%s %s", "LOGIN", message);
//        }
//
//        return formatMessage;
//    }

    public static void main(String[] args) {
    }
}
