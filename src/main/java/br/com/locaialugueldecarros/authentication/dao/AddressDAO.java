package br.com.locaialugueldecarros.authentication.dao;

import br.com.locaialugueldecarros.util.model.Address;

import java.sql.*;

public class AddressDAO {
    private final Connection connection;

    public AddressDAO(Connection conn) {
        this.connection = conn;
    }

    public int saveAddress(Address address) {
        String sql = "INSERT INTO addresses (street, number, neighborhood, city, state) VALUES (?,?,?,?,?)";

        try (PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, address.getStreet());
            statement.setString(2, address.getNumber());
            statement.setString(3, address.getNeighborhood());
            statement.setString(4, address.getCity());
            statement.setString(5, address.getState());

            statement.executeUpdate();

            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                return resultSet.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return -1;
    }

    public void addAddressToUser(int userId, int addressId) {
        String sql = "INSERT INTO user_addresses (user_id, address_id) VALUES (?,?)";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, userId);
            statement.setInt(2, addressId);

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
