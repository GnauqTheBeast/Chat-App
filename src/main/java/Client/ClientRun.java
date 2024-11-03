package Client;

import Client.Controller.ClientSocketHandler;
import Client.View.Dashboard;
import Client.View.Login;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class ClientRun {
    public static ClientSocketHandler clientHandler;
    public static Login login;
    public static Dashboard dashboard;

    public enum SceneName {
        LOGIN,
        DASHBOARD,
    }

    public ClientRun() {
        initScences();
    }

    public void initScences() {
        login = new Login();
        dashboard = new Dashboard();
    }

    private void closeAllScences() {
        login.dispose();
        dashboard.dispose();
    }

    public static void navigateScene(ClientRun.SceneName sceneName) {
        switch (sceneName) {
            case LOGIN:
                login.setVisible(true);
                break;
            case DASHBOARD:
                dashboard.setVisible(true);
                break;
        }
    }

    public static void closeScene(ClientRun.SceneName sceneName) {
        switch (sceneName) {
            case LOGIN:
                login.setVisible(false);
                break;
            case DASHBOARD:
                dashboard.setVisible(false);
                break;
        }
    }

    public void StartClient() {
        try (Socket socket = new Socket("localhost", 2003)) {
            initScences();
            clientHandler = new ClientSocketHandler(socket);
            clientHandler.listen();
        } catch (UnknownHostException e) {
            throw new RuntimeException("Unknown host: " + e.getMessage(), e);
        } catch (IOException e) {
            throw new RuntimeException("I/O error occurred: " + e.getMessage(), e);
        }
    }

    public static void main(String[] args) {
        ClientRun clientRun = new ClientRun();
        clientRun.StartClient();
    }
}
