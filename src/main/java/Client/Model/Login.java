package Client.Model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class Login {
    private String username;
    private String password;
    private boolean loginSuccess;

    public Login(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isLoginSuccess() {
        return loginSuccess;
    }

    public void setLoginSuccess(boolean loginSuccess) {
        boolean oldStatus = this.loginSuccess;
        this.loginSuccess = loginSuccess;
    }
}
