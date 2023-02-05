package br.com.locaialugueldecarros.car_rental.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

public class CarRentalDAO {

    private final Connection connection;

    public CarRentalDAO(Connection connection) {
        this.connection = connection;
    }

    public void saveRental(int carId, int pickupAddressId, int returnAddressId, LocalDateTime pickupDatetime,
                           LocalDateTime returnDatetime, String rentalType) throws SQLException {
        String query = "INSERT INTO car_rentals (car_id, pickup_address_id, pickup_datetime, return_address_id, " +
                "return_datetime, rental_type) VALUES(?, ?, ?, ?, ?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, carId);
            statement.setInt(2, pickupAddressId);
            statement.setTimestamp(3, Timestamp.valueOf(pickupDatetime));
            statement.setInt(4, returnAddressId);
            statement.setTimestamp(5, Timestamp.valueOf(returnDatetime));
            statement.setString(6, rentalType);

            statement.executeUpdate();
        } catch (SQLException e) {
            throw e;
        }
    }
}
