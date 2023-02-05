package br.com.locaialugueldecarros.car_rental.model.entities;

import br.com.locaialugueldecarros.car_rental.model.enums.RentalType;

import java.time.LocalDateTime;

public class CarRental {

    private int rentalId;
    private int carId;
    private int pickup_address_id;
    private LocalDateTime pickup_datetime;
    private int return_address_id;
    private LocalDateTime return_datetime;
    private RentalType rentalType;

    public CarRental() {
    }

    public CarRental(int rentalId, int carId, int pickup_address_id, LocalDateTime pickup_datetime, int return_address_id,
                     LocalDateTime return_datetime, String rentalType) {
        this.rentalId = rentalId;
        this.carId = carId;
        this.pickup_address_id = pickup_address_id;
        this.pickup_datetime = pickup_datetime;
        this.return_address_id = return_address_id;
        this.return_datetime = return_datetime;
        this.rentalType = RentalType.valueOf(rentalType);
    }

    public int getRentalId() {
        return rentalId;
    }

    public void setRentalId(int rentalId) {
        this.rentalId = rentalId;
    }

    public int getCarId() {
        return carId;
    }

    public void setCarId(int carId) {
        this.carId = carId;
    }

    public int getPickup_address_id() {
        return pickup_address_id;
    }

    public void setPickup_address_id(int pickup_address_id) {
        this.pickup_address_id = pickup_address_id;
    }

    public LocalDateTime getPickup_datetime() {
        return pickup_datetime;
    }

    public void setPickup_datetime(LocalDateTime pickup_datetime) {
        this.pickup_datetime = pickup_datetime;
    }

    public int getReturn_address_id() {
        return return_address_id;
    }

    public void setReturn_address_id(int return_address_id) {
        this.return_address_id = return_address_id;
    }

    public LocalDateTime getReturn_datetime() {
        return return_datetime;
    }

    public void setReturn_datetime(LocalDateTime return_datetime) {
        this.return_datetime = return_datetime;
    }

    public RentalType getRentalType() {
        return rentalType;
    }

    public void setRentalType(RentalType rentalType) {
        this.rentalType = rentalType;
    }
}
