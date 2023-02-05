package br.com.locaialugueldecarros.car_rental.service;

import br.com.locaialugueldecarros.car_rental.dao.ContactDAO;
import br.com.locaialugueldecarros.car_rental.model.entities.Messages;
import br.com.locaialugueldecarros.util.EmailUtil;

public class ContactService {

    private final ContactDAO contactDAO;

    public ContactService(ContactDAO contactDAO) {
        this.contactDAO = contactDAO;
    }

    public boolean saveMessage(Messages msg) {
        if(contactDAO.saveMessage(msg)) {
            EmailUtil.sendContactMessage(msg);
            return true;
        }
        return false;
    }
}
