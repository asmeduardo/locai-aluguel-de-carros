package br.com.locaialugueldecarros.car_rental.dao;

import br.com.locaialugueldecarros.car_rental.model.entities.Messages;

import java.sql.*;

public class ContactDAO {

    private final Connection connection;

    public ContactDAO(Connection connection) {
        this.connection = connection;
    }

    public boolean saveMessage(Messages msg) {
        try {
            String query = "INSERT INTO contact (first_name, last_name, email, message) VALUES (?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, msg.getFirstName());
            statement.setString(2, msg.getLastName());
            statement.setString(3, msg.getEmail());
            statement.setString(4, msg.getMessage());
            statement.execute();

            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                int messageId = generatedKeys.getInt(1);
                msg.setId(messageId);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }
}
