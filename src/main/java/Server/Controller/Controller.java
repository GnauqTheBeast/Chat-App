package Server.Controller;

import Server.DAO.DAO;
import Server.Model.User;

public class Controller {
    private final DAO dao;

    public Controller() {
        this.dao = new DAO();
    }

    public String handleLogin(String username, String password) {
        if (isValidUser(username, password)) {
            User user = dao.getUser(username);
            return String.format("LOGIN|LOGIN_SUCCESS|%s", user.toString());
        }
        return "LOGIN|LOGIN_FAILED";
    }

    public String handleRegister(String username, String password, String email, String full_name) {
        if (isUserExist(username)) {
            return "REGISTER|USERNAME_EXIST";
        }
        dao.createUser(username, password, email, full_name);
        return "REGISTER|REGISTER_SUCCESS";
    }

    private boolean isValidUser(String username, String password) {
        return dao.checkLogin(username, password);
    }

    private boolean isUserExist(String username) {
        String usernameRegister = dao.getUsername(username);
        if (usernameRegister == null) {
            return false;
        }
        return true;
    }
}
