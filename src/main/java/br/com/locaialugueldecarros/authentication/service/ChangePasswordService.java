package br.com.locaialugueldecarros.authentication.service;

import br.com.locaialugueldecarros.authentication.dao.UserDAO;
import br.com.locaialugueldecarros.authentication.model.User;

public class ChangePasswordService {

    private final UserDAO userDAO;

    public ChangePasswordService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public boolean changePassword(User user) {
        return userDAO.updateUserPassword(user);
    }
}
