package DAO;

import java.sql.*;

public class DAO {
    private static final String connectionString = "jdbc:mysql://localhost:3306/chat";

    private Connection _connection;

    public DAO() {
        _connection = initDBConnection();
    }

    private Connection initDBConnection() {
        try {
            String password = "mysql";
            Connection connection = DriverManager.getConnection(connectionString, "root", password);
            System.out.println("Connected to database");
            return connection;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean checkLogin(String username, String password) {
        try {
            String query = "SELECT * FROM users WHERE username = ? AND password = ?";
            PreparedStatement statement = _connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            return resultSet.next(); // if there is at least one row, login successfully
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
