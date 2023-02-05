package br.com.locaialugueldecarros.authentication.service;

import br.com.locaialugueldecarros.authentication.dao.UserDAO;
import br.com.locaialugueldecarros.authentication.model.User;
import br.com.locaialugueldecarros.util.EmailUtil;
import br.com.locaialugueldecarros.util.PasswordUtil;

public class PasswordRecoveryService {

    private final UserDAO userDAO;

    public PasswordRecoveryService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public boolean sendPasswordRecoveryEmail(String email) {
        User user = userDAO.getUserByEmail(email);
        if (user != null) {
            PasswordUtil.setKey("mysecretkey");
            return EmailUtil.sendPasswordRecoveryEmail(email, PasswordUtil.decryptPassword(user.getPassword()));
        }
        return false;
    }
}
