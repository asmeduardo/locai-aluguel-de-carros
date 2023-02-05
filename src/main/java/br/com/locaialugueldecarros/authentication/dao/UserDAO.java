package br.com.locaialugueldecarros.authentication.dao;

import br.com.locaialugueldecarros.authentication.model.User;
import br.com.locaialugueldecarros.util.AbstractFactory;
import br.com.locaialugueldecarros.util.ApplicationContext;

import java.sql.*;

public class UserDAO {

    private final Connection connection;

    public UserDAO(Connection conn) {
        this.connection = conn;
    }

    public boolean createUser(User user) {
        try {
            String sql = "INSERT INTO users (first_name, last_name, email, password) VALUES (?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, user.getFirstName());
            statement.setString(2, user.getLastName());
            statement.setString(3, user.getEmail());
            statement.setString(4, user.getPassword());
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                user.setId(resultSet.getInt(1));
            }
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public User getUserByEmail(String email) {
        try {
            String sql = "SELECT * FROM users WHERE email = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, email);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                int id = resultSet.getInt("user_id");
                String firstName = resultSet.getString("first_name");
                String lastName = resultSet.getString("last_name");
                String email1 = resultSet.getString("email");
                String password = resultSet.getString("password");
                AbstractFactory factory = ApplicationContext.getInstance().getConcreteFactory();
                User user = (User) factory.createObject("User");
                user.setId(id);
                user.setFirstName(firstName);
                user.setLastName(lastName);
                user.setEmail(email1);
                user.setPassword(password);
                return user;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean updateUserPassword(User user) {
        try {
            String query = "UPDATE users SET password=? WHERE user_id=?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, user.getPassword());
            statement.setInt(2, user.getId());
            int rowsUpdated = statement.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}