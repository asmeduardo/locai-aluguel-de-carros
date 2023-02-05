package br.com.locaialugueldecarros.car_rental.model.entities;

public class ParkingSpace {

    private int addressId;
    private int parkingSpaces;

    public ParkingSpace() {
    }

    public ParkingSpace(int addressId, int parkingSpaces) {
        this.addressId = addressId;
        this.parkingSpaces = parkingSpaces;
    }

    public int getAddressId() {
        return addressId;
    }

    public void setAddressId(int addressId) {
        this.addressId = addressId;
    }

    public int getParkingSpaces() {
        return parkingSpaces;
    }

    public void setParkingSpaces(int parkingSpaces) {
        this.parkingSpaces = parkingSpaces;
    }
}
