package Server.Controller;

import Server.DAO.DAO;

public class Controller {
    private final DAO dao;

    public Controller() {
        this.dao = new DAO();
    }

    public String handleLogin(String username, String password) {
        if (isValidUser(username, password)) {
            return "LOGIN LOGIN_SUCCESS";
        }
        return "LOGIN LOGIN_FAILED";
    }

    private boolean isValidUser(String username, String password) {
        return dao.checkLogin(username, password);
    }
}
