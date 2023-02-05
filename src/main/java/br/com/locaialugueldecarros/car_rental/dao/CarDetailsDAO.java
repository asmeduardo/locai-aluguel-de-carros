package br.com.locaialugueldecarros.car_rental.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CarDetailsDAO {
    private final Connection connection;

    public CarDetailsDAO(Connection connection) {
        this.connection = connection;
    }

    public void saveCarDetails(int carId, double rentalPrice, String paymentMethod) {
        String query = "INSERT INTO car_details (car_id, rental_price, payment_method) VALUES (?, ?, ?)";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, carId);
            statement.setDouble(2, rentalPrice);
            statement.setString(3, paymentMethod);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
