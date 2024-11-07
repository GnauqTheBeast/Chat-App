package Client.Controller.Login;

import Client.ClientRun;

import javax.swing.*;

public class LoginController {
    public LoginController() {}

    public void loginHandler(String message) {
        if (!message.equals("LOGIN|LOGIN_SUCCESS")) {
            JOptionPane.showMessageDialog(null, "Wrong information. Please try again.",
                    "Login Failed", JOptionPane.ERROR_MESSAGE);
            ClientRun.navigateScene(ClientRun.SceneName.LOGIN);
            return;
        }
        ClientRun.closeScene(ClientRun.SceneName.LOGIN);
        ClientRun.navigateScene(ClientRun.SceneName.DASHBOARD);
    }
}
