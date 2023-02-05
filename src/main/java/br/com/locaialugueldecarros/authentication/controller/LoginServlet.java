package br.com.locaialugueldecarros.authentication.controller;

import br.com.locaialugueldecarros.authentication.service.LoginService;
import br.com.locaialugueldecarros.util.ApplicationContext;
import br.com.locaialugueldecarros.util.RecaptchaUtil;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.io.Serial;
import java.util.Objects;

@WebServlet("/entrar")
public class LoginServlet extends HttpServlet {
    @Serial
    private static final long serialVersionUID = 1L;

    private final LoginService loginService;

    public LoginServlet() {
        this.loginService = ApplicationContext.getInstance().getLoginService();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String remember = request.getParameter("remember");
        String recaptchaResponse = request.getParameter("g-recaptcha-response");

        if (Objects.equals(email, "") || Objects.equals(password, "") || Objects.equals(recaptchaResponse, "")) {
            request.setAttribute("errorMessage", "Entrada inválida. Preencha corretamente os campos.");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        } else {
            boolean recaptchaValid = RecaptchaUtil.validateRecaptcha(recaptchaResponse);

            if (recaptchaValid) {
                boolean authenticate = loginService.authenticate(email, password, remember, request, response);

                if (authenticate) {
                    response.sendRedirect("home.jsp");
                } else {
                    request.setAttribute("errorMessage", "Email ou senha inválidos.");
                    request.getRequestDispatcher("login.jsp").forward(request, response);
                }
            }
        }
    }
}
