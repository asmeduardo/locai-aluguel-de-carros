package br.com.locaialugueldecarros.util;

import br.com.locaialugueldecarros.authentication.dao.AddressDAO;
import br.com.locaialugueldecarros.authentication.dao.UserDAO;
import br.com.locaialugueldecarros.authentication.service.*;
import br.com.locaialugueldecarros.car_rental.dao.*;
import br.com.locaialugueldecarros.car_rental.service.*;

import java.sql.Connection;

public class ApplicationContext {
    private final ConcreteFactory concreteFactory;
    private static ApplicationContext instance;
    private final AddAddressService addressService;
    private final ChangePasswordService changePasswordService;
    private final LoginService loginService;
    private final PasswordRecoveryService passwordRecoveryService;
    private final RegistrationService registrationService;
    private final CommonQuestionsService commonQuestionsService;
    private final ContactService contactService;
    public final AddNewCarService addNewCarService;
    public final CarRentalService carRentalService;
    public final CarDetailsService carDetailsService;
    public final CarInsurerService carInsurerService;
    public final CarOwnersService carOwnersService;
    public final SearchCarService searchCarService;


    private ApplicationContext() {
        Connection conn = DBConnectionFactory.getInstance().getConnection();
        this.concreteFactory = new ConcreteFactory();
        this.addressService = new AddAddressService(new AddressDAO(conn));
        this.changePasswordService = new ChangePasswordService(new UserDAO(conn));
        this.loginService = new LoginService(new UserDAO(conn));
        this.passwordRecoveryService = new PasswordRecoveryService(new UserDAO(conn));
        this.registrationService = new RegistrationService(new UserDAO(conn));
        this.commonQuestionsService = new CommonQuestionsService(new CommonQuestionsDAO(conn));
        this.contactService = new ContactService(new ContactDAO(conn));
        this.addNewCarService = new AddNewCarService(new CarDAO(conn));
        this.carRentalService = new CarRentalService(new CarRentalDAO(conn));
        this.carDetailsService = new CarDetailsService(new CarDetailsDAO(conn));
        this.carInsurerService = new CarInsurerService(new CarInsurerDAO(conn));
        this.carOwnersService = new CarOwnersService(new CarOwnerDAO(conn));
        this.searchCarService = new SearchCarService(new CarDAO(conn));
    }

    public static ApplicationContext getInstance() {
        if (instance == null) {
            instance = new ApplicationContext();
        }
        return instance;
    }

    public ConcreteFactory getConcreteFactory() {
        return concreteFactory;
    }

    public AddAddressService getAddressService() {
        return addressService;
    }

    public ChangePasswordService getChangePasswordService() {
        return changePasswordService;
    }

    public LoginService getLoginService() {
        return loginService;
    }

    public PasswordRecoveryService getPasswordRecoveryService() {
        return passwordRecoveryService;
    }

    public RegistrationService getRegistrationService() {
        return registrationService;
    }

    public CommonQuestionsService getCommonQuestionsService() {
        return commonQuestionsService;
    }

    public ContactService getContactService() {
        return contactService;
    }

    public AddNewCarService getAddNewCarService() {
        return addNewCarService;
    }

    public CarRentalService getCarRentalService() {
        return carRentalService;
    }

    public CarDetailsService getCarDetailsService() {
        return carDetailsService;
    }

    public CarInsurerService getCarInsurerService() {
        return carInsurerService;
    }

    public CarOwnersService getCarOwnersService() {
        return carOwnersService;
    }

    public SearchCarService getSearchCarService() {
        return searchCarService;
    }
}
