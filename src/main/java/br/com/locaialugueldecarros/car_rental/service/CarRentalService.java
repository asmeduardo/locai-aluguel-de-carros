package br.com.locaialugueldecarros.car_rental.service;

import br.com.locaialugueldecarros.car_rental.dao.CarRentalDAO;

import java.sql.SQLException;
import java.time.LocalDateTime;

public class CarRentalService {
    private final CarRentalDAO rentCarDAO;

    public CarRentalService(CarRentalDAO rentCarDAO) {
        this.rentCarDAO = rentCarDAO;
    }

    public void rentCar(int carId, int pickupAddressId, int returnAddressId, LocalDateTime pickupDatetime,
                        LocalDateTime returnDatetime, String rentalType) throws SQLException {
        // Call the rentCarDao to save the rental
        rentCarDAO.saveRental(carId, pickupAddressId, returnAddressId, pickupDatetime, returnDatetime, rentalType);
    }
}
