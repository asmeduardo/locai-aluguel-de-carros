package br.com.locaialugueldecarros.car_rental.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CarOwnerDAO {

    private final Connection connection;

    public CarOwnerDAO(Connection connection) {
        this.connection = connection;
    }

    public void addCarOwnersDetails(int carId, int userId) {
        String query = "INSERT INTO car_owners (car_id, user_id) values(?,?)";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, carId);
            statement.setInt(2, userId);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
