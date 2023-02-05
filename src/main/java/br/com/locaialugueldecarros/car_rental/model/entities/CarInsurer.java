package br.com.locaialugueldecarros.car_rental.model.entities;

public class CarInsurer {

    private int carId;
    private int insurerId;

    public CarInsurer() {
    }

    public CarInsurer(int carId, int insurerId) {
        this.carId = carId;
        this.insurerId = insurerId;
    }

    public int getCarId() {
        return carId;
    }

    public void setCarId(int carId) {
        this.carId = carId;
    }

    public int getInsurerId() {
        return insurerId;
    }

    public void setInsurerId(int insurerId) {
        this.insurerId = insurerId;
    }
}
