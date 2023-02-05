package br.com.locaialugueldecarros.authentication.service;

import br.com.locaialugueldecarros.authentication.dao.UserDAO;
import br.com.locaialugueldecarros.authentication.model.User;
import br.com.locaialugueldecarros.util.EmailUtil;
import br.com.locaialugueldecarros.util.PasswordUtil;

public class RegistrationService {

    private final UserDAO userDAO;

    public RegistrationService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public boolean createUser(User user) {
        PasswordUtil.setKey("mysecretkey");
        user.setPassword(PasswordUtil.encryptPassword(user.getPassword()));
        if (userDAO.createUser(user)) {
            return EmailUtil.sendConfirmationEmail(user.getEmail());
        }
        return false;
    }

}
