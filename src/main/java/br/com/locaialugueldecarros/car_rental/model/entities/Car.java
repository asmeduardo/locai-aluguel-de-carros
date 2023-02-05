package br.com.locaialugueldecarros.car_rental.model.entities;

import br.com.locaialugueldecarros.car_rental.model.enums.FuelType;
import br.com.locaialugueldecarros.car_rental.model.enums.Steering;

public class Car {

    private int carId;
    private String manufacturer;
    private String model;
    private int year;
    private FuelType fuel;
    private Steering steering;
    private String licensePlate;
    private String color;
    private int seats;
    private int ports;

    public Car() {
    }

    public Car(String manufacturer, String model, int year, String fuel, String steering, String licensePlate,
               String color, int seats, int ports) {
        this.manufacturer = manufacturer;
        this.model = model;
        this.year = year;
        this.fuel = FuelType.valueOf(fuel);
        this.steering = Steering.valueOf(steering);
        this.licensePlate = licensePlate;
        this.color = color;
        this.seats = seats;
        this.ports = ports;
    }

    public Car(int carId, String manufacturer, String model, int year, String fuel, String steering,
               String licensePlate, String color, int seats, int ports) {
        this.carId = carId;
        this.manufacturer = manufacturer;
        this.model = model;
        this.year = year;
        this.fuel = FuelType.valueOf(fuel);
        this.steering = Steering.valueOf(steering);
        this.licensePlate = licensePlate;
        this.color = color;
        this.seats = seats;
        this.ports = ports;
    }

    public int getCarId() {
        return carId;
    }

    public void setCarId(int carId) {
        this.carId = carId;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public FuelType getFuel() {
        return fuel;
    }

    public void setFuel(FuelType fuel) {
        this.fuel = fuel;
    }

    public Steering getSteering() {
        return steering;
    }

    public void setSteering(Steering steering) {
        this.steering = steering;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getSeats() {
        return seats;
    }

    public void setSeats(int seats) {
        this.seats = seats;
    }

    public int getPorts() {
        return ports;
    }

    public void setPorts(int ports) {
        this.ports = ports;
    }
}
