package Client.Controller.Login;

import Client.ClientRun;
import Client.Model.User;

import javax.swing.*;

public class LoginController {
    public static final int MESSAGE_PARTS_LENGTH = 4;
    public LoginController() {}

    public void loginHandler(String message) {
        if (!message.startsWith("LOGIN|LOGIN_SUCCESS")) {
            JOptionPane.showMessageDialog(null, "Wrong information. Please try again.",
                    "Login Failed", JOptionPane.ERROR_MESSAGE);
            ClientRun.navigateScene(ClientRun.SceneName.LOGIN);
            return;
        }

        String[] messageParts = message.split("\\|");
        if (messageParts.length != MESSAGE_PARTS_LENGTH) {
            JOptionPane.showMessageDialog(null, "Server error with login.",
                    "Login Failed", JOptionPane.ERROR_MESSAGE);
            ClientRun.navigateScene(ClientRun.SceneName.LOGIN);
            return;
        }

        ClientRun.setCurrentUser(new User(messageParts[2], messageParts[3]));
        ClientRun.closeScene(ClientRun.SceneName.LOGIN);
        ClientRun.navigateScene(ClientRun.SceneName.DASHBOARD);
    }
}
