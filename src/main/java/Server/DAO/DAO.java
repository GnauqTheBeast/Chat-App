package Server.DAO;

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

}
