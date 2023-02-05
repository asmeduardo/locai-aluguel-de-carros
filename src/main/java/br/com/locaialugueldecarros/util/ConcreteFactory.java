package br.com.locaialugueldecarros.util;

import br.com.locaialugueldecarros.authentication.model.User;
import br.com.locaialugueldecarros.car_rental.model.entities.*;
import br.com.locaialugueldecarros.util.model.Address;

public class ConcreteFactory extends AbstractFactory {
    @Override
    public Object createObject(String type) {
        switch (type) {
            case "User":
                return new User();
            case "Address":
                return new Address();
            case "CarDetails":
                return new CarDetails();
            case "CarRental":
                return new CarRental();
            case "CarInsurer":
                return new CarInsurer();
            case "CommonQuestions":
                return new CommonQuestions();
            case "Messages":
                return new Messages();
            case "ParkingSpace":
                return new ParkingSpace();
            case "Payment":
                return new Payment();
            default:
                throw new IllegalArgumentException("Tipo inválido");
        }
    }
}
