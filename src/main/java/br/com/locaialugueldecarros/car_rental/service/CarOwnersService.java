package br.com.locaialugueldecarros.car_rental.service;

import br.com.locaialugueldecarros.car_rental.dao.CarOwnerDAO;

public class CarOwnersService {

    private final CarOwnerDAO carOwnerDAO;

    public CarOwnersService(CarOwnerDAO carOwnerDAO) {
        this.carOwnerDAO = carOwnerDAO;
    }

    public void addCarOwnersDetails(int carId, int userId) {
        carOwnerDAO.addCarOwnersDetails(carId, userId);
    }
}
