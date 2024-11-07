package Server.DAO;

import Server.Model.User;

import java.sql.*;

public class DAO {
    private static final String connectionString = "jdbc:mysql://localhost:3306/chat";
    private final String PASSWORD_DB = "mysql";
    private final String USER = "root";
    private final Connection _connection;

    public DAO() {
        _connection = initDBConnection();
    }

    private Connection initDBConnection() {
        try {
            Connection connection = DriverManager.getConnection(connectionString, USER, PASSWORD_DB);
            System.out.println("Connected to database");
            return connection;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean checkLogin(String username, String password) {
        String query = "SELECT * FROM users WHERE username = ? AND password = ?";
        try (PreparedStatement statement = _connection.prepareStatement(query)) {
            statement.setString(1, username);
            statement.setString(2, password);

            try (ResultSet resultSet = statement.executeQuery()) {
                return resultSet.next();
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error checking login credentials", e);
        }
    }

    public User getUser(String username) {
        String query = "SELECT username, email FROM users WHERE username = ?";

        try (PreparedStatement statement = _connection.prepareStatement(query)) {
            statement.setString(1, username);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return new User(resultSet.getString("username"), resultSet.getString("email"));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error checking login credentials", e);
        }
        return null;
    }

    public String getUsername(String username) {
        String query = "SELECT * FROM users WHERE username = ?";
        try (PreparedStatement statement = _connection.prepareStatement(query)) {
            statement.setString(1, username);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getString("username");
                }
                return null;
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error retrieving username", e);
        }
    }


    public void createUser(String username, String password, String email, String full_name) {
        String query = "INSERT INTO users (username, password, email, full_name) VALUES (?, ?, ?, ?)";
        try (PreparedStatement statement = _connection.prepareStatement(query)) {
            statement.setString(1, username);
            statement.setString(2, password);
            statement.setString(3, email);
            statement.setString(4, full_name);
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("error");
            throw new RuntimeException(e);
        }
    }

}
