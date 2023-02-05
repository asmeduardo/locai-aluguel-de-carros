package br.com.locaialugueldecarros.car_rental.service;

import br.com.locaialugueldecarros.car_rental.dao.CarDAO;
import br.com.locaialugueldecarros.car_rental.model.entities.Car;

import java.util.List;

public class SearchCarService {

    private final CarDAO carDAO;

    public SearchCarService(CarDAO carDAO) {
        this.carDAO = carDAO;
    }

    public List<Car> getAvailableCars() {
        return carDAO.getAvailableCars();
    }
}
