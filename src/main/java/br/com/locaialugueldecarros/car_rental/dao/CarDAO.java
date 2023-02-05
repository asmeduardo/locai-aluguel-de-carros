package br.com.locaialugueldecarros.car_rental.dao;

import br.com.locaialugueldecarros.car_rental.model.entities.Car;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CarDAO {

    private final Connection connection;

    public CarDAO(Connection conn) {
        this.connection = conn;
    }

    public void saveCar(Car car) {
        try {
            String query = "INSERT INTO cars (manufacturer, model, year, fuel, steering, license_plate, color, seats, " +
                    "ports) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, car.getManufacturer());
            statement.setString(2, car.getModel());
            statement.setInt(3, car.getYear());
            statement.setString(4, car.getFuel().toString());
            statement.setString(5, car.getSteering().toString());
            statement.setString(6, car.getLicensePlate());
            statement.setString(7, car.getColor());
            statement.setInt(8, car.getSeats());
            statement.setInt(9, car.getPorts());
            statement.execute();

            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                int carId = generatedKeys.getInt(1);
                car.setCarId(carId);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Car> getAvailableCars() {
        List<Car> availableCars = new ArrayList<>();
        try {
            String query = "SELECT cars.* FROM cars WHERE cars.car_id NOT IN (SELECT car_rentals.car_id FROM " +
                    "car_rentals WHERE car_rentals.pickup_datetime <= NOW() AND car_rentals.return_datetime >= NOW())";
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int carId = resultSet.getInt("car_id");
                String manufacturer = resultSet.getString("manufacturer");
                String model = resultSet.getString("model");
                int year = resultSet.getInt("year");
                String fuel = resultSet.getString("fuel");
                String steering = resultSet.getString("steering");
                String licensePlate = resultSet.getString("license_plate");
                String color = resultSet.getString("color");
                int seats = resultSet.getInt("seats");
                int ports = resultSet.getInt("ports");
                Car car = new Car(carId, manufacturer, model, year, fuel, steering, licensePlate, color, seats, ports);
                availableCars.add(car);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return availableCars;
    }
}