package br.com.locaialugueldecarros.authentication.service;

import br.com.locaialugueldecarros.authentication.dao.AddressDAO;
import br.com.locaialugueldecarros.util.model.Address;

public class AddAddressService {
    private final AddressDAO addressDAO;

    public AddAddressService(AddressDAO addressDAO) {
        this.addressDAO = addressDAO;
    }

    public void addAddress(int userId, Address address) {
        int addressId = addressDAO.saveAddress(address);
        addressDAO.addAddressToUser(userId, addressId);
    }
}

