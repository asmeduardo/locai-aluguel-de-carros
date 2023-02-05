package br.com.locaialugueldecarros.car_rental.service;

import br.com.locaialugueldecarros.car_rental.dao.CarDetailsDAO;

import java.sql.SQLException;

public class CarDetailsService {

    private final CarDetailsDAO carDetailsDAO;

    public CarDetailsService(CarDetailsDAO carDetailsDAO) {
        this.carDetailsDAO = carDetailsDAO;
    }

    public void saveCarDetails(int carId, double rentalPrice, String paymentMethod) throws SQLException {
        carDetailsDAO.saveCarDetails(carId, rentalPrice, paymentMethod);
    }
}
