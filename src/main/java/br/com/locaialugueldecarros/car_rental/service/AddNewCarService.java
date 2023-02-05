package br.com.locaialugueldecarros.car_rental.service;

import br.com.locaialugueldecarros.car_rental.dao.CarDAO;
import br.com.locaialugueldecarros.car_rental.model.entities.Car;

public class AddNewCarService {
    private final CarDAO carDAO;

    public AddNewCarService(CarDAO carDAO) {
        this.carDAO = carDAO;
    }

    public void addNewCar(Car car) {
        carDAO.saveCar(car);
    }
}

