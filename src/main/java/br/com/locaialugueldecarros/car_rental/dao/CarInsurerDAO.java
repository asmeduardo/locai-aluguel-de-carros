package br.com.locaialugueldecarros.car_rental.dao;

import java.sql.*;

public class CarInsurerDAO {

    private final Connection connection;

    public CarInsurerDAO(Connection connection) {
        this.connection = connection;
    }

    public void addCarInsurer(int carId, int insurerId) {
        try (PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO car_insurers (car_id, " +
                "insurer_id) VALUES (?, ?)")) {
            preparedStatement.setInt(1, carId);
            preparedStatement.setInt(2, insurerId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
