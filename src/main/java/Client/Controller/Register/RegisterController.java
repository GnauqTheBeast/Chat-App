package Client.Controller.Register;

import Client.ClientRun;

import javax.swing.*;

public class RegisterController {
    public RegisterController() {}

    public void registerHandler(String message) {
        if (message.equals("INVALID_FORMAT")) {
            JOptionPane.showMessageDialog(null, "Invalid Format",
                    "Register Failed", JOptionPane.ERROR_MESSAGE);
            ClientRun.navigateScene(ClientRun.SceneName.REGISTER);
            return;
        }

        if (message.equals("REGISTER|USERNAME_EXIST")) {
            JOptionPane.showMessageDialog(null, "Username is existed, please try different one",
                    "Register Failed", JOptionPane.ERROR_MESSAGE);
            ClientRun.navigateScene(ClientRun.SceneName.REGISTER);
            return;
        }

        JOptionPane.showMessageDialog(null, "Registration successful!");
        ClientRun.closeScene(ClientRun.SceneName.REGISTER);
        ClientRun.navigateScene(ClientRun.SceneName.LOGIN);
    }
}
