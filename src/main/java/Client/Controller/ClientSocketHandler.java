package Client.Controller;

import Client.ClientRun;

import java.io.*;
import java.net.Socket;

public class ClientSocketHandler {
    private Socket socket;
    private BufferedReader is;
    private PrintWriter os;
    private boolean isRunning = true;

    public ClientSocketHandler(Socket socket) {
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
                String[] splitedMessage = message.split(" ");
                if (splitedMessage.length > 1) {
                    switch (splitedMessage[0]) {
                        case "LOGIN":
                            loginHandler(message);
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
//        String formatMessage = createMessage(message);
        System.out.println("Client SENT: " + message);
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

    public void loginHandler(String message) {
        if (!message.equals("LOGIN LOGIN_SUCCESS")) {
            ClientRun.navigateScene(ClientRun.SceneName.LOGIN);
            return;
        }

        ClientRun.closeScene(ClientRun.SceneName.LOGIN);
        ClientRun.navigateScene(ClientRun.SceneName.DASHBOARD);
    }

    public static void main(String[] args) {
    }
}
