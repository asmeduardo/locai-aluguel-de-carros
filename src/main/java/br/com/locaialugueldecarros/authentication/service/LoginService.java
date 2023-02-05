package br.com.locaialugueldecarros.authentication.service;


import br.com.locaialugueldecarros.authentication.dao.UserDAO;
import br.com.locaialugueldecarros.authentication.model.User;
import br.com.locaialugueldecarros.util.PasswordUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class LoginService {
    private final UserDAO userDAO;

    public LoginService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public boolean authenticate(String email, String password, String remember, HttpServletRequest request, HttpServletResponse response) {
        User user = userDAO.getUserByEmail(email);
        if (user != null) {
            PasswordUtil.setKey("mysecretkey");
            if (PasswordUtil.compareDecryptedPassword(password, user.getPassword())) {
                HttpSession session = request.getSession(true);
                session.setAttribute("isActive", true);
                session.setAttribute("user", user);
                session.setMaxInactiveInterval(1800);
                if (remember != null && !remember.equals("")) {
                    RememberMeService.saveRememberMeCookie(response, email);
                } else {
                    RememberMeService.deleteCookie(response);
                }
                return true;
            }
        }
        return false;
    }
}
