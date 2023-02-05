package br.com.locaialugueldecarros.car_rental.model.entities;

import br.com.locaialugueldecarros.car_rental.model.enums.PaymentMethod;

public class CarDetails {

    private int carId;
    private double rentalPrice;
    private PaymentMethod paymentMethod;

    public CarDetails() {
    }

    public CarDetails(int carId, double rentalPrice, PaymentMethod paymentMethod) {
        this.carId = carId;
        this.rentalPrice = rentalPrice;
        this.paymentMethod = paymentMethod;
    }

    public int getCarId() {
        return carId;
    }

    public void setCarId(int carId) {
        this.carId = carId;
    }

    public double getRentalPrice() {
        return rentalPrice;
    }

    public void setRentalPrice(double rentalPrice) {
        this.rentalPrice = rentalPrice;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }
}
