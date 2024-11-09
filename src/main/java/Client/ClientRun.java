package Client;

import Client.Controller.ClientSocketHandler;
import Client.View.*;
import Client.Model.*;
import Client.View.Login;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class ClientRun {
    public static ClientSocketHandler clientHandler;
    public static User currentUser;
    public static User competitor;
    public static Room currentRoom;
    public static Login login;
    public static Register register;
    public static Profile profile;
    public static Dashboard dashboard;
    public static PlayOption playOption;
    public static RoomView room;

    public enum SceneName {
        LOGIN,
        DASHBOARD,
        PROFILE,
        REGISTER,
        ROOM,
        GAME,
        PLAY_OPTION,
    }

    public ClientRun() {
        initScences();
    }

    public void initScences() {
        login = new Login();
        dashboard = new Dashboard();
        register = new Register();
        profile = new Profile();
        playOption = new PlayOption();
    }

    private void closeAllScences() {
        login.dispose();
        dashboard.dispose();
        register.dispose();
        profile.dispose();
        playOption.dispose();
    }

    public static void navigateScene(ClientRun.SceneName sceneName) {
        switch (sceneName) {
            case LOGIN:
                login.setVisible(true);
                break;
            case DASHBOARD:
                dashboard.setVisible(true);
                break;
            case REGISTER:
                register.setVisible(true);
                break;
            case PROFILE:
                profile.updateProfileInfo();
                profile.setVisible(true);
                break;
            case ROOM:
                room = new RoomView();
                room.setVisible(true);
                break;
            case GAME:
                break;
            case PLAY_OPTION:
                playOption.setVisible(true);
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
            case REGISTER:
                register.setVisible(false);
                break;
            case PROFILE:
                profile.setVisible(false);
                break;
            case ROOM:
                room.setVisible(false);
                break;
            case PLAY_OPTION:
                playOption.setVisible(false);
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

    public static void setCurrentUser(User user) {
        currentUser = user;
    }

    public static void main(String[] args) {
        ClientRun clientRun = new ClientRun();
        clientRun.StartClient();
    }
}
