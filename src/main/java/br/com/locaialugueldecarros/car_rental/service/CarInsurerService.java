package br.com.locaialugueldecarros.car_rental.service;

import br.com.locaialugueldecarros.car_rental.dao.CarInsurerDAO;

public class CarInsurerService {

    private final CarInsurerDAO carInsurerDAO;

    public CarInsurerService(CarInsurerDAO carInsurerDAO) {
        this.carInsurerDAO = carInsurerDAO;
    }

    public void addCarInsurer(int carId, int insurerId) {
        carInsurerDAO.addCarInsurer(carId, insurerId);
    }
}
