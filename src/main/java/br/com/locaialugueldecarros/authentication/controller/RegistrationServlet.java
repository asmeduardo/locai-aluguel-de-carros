package br.com.locaialugueldecarros.authentication.controller;

import br.com.locaialugueldecarros.authentication.model.User;
import br.com.locaialugueldecarros.authentication.service.RegistrationService;
import br.com.locaialugueldecarros.util.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.io.Serial;

@WebServlet("/cadastrar")
public class RegistrationServlet extends HttpServlet {
    @Serial
    private static final long serialVersionUID = 1L;
    private final RegistrationService registrationService;

    public RegistrationServlet() {
        this.registrationService = ApplicationContext.getInstance().getRegistrationService();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String recaptchaResponse = request.getParameter("g-recaptcha-response");

        String[] inputs = new String[5];
        inputs[0] = firstName;
        inputs[1] = lastName;
        inputs[2] = email;
        inputs[3] = password;
        inputs[4] = recaptchaResponse;

        if (!InputValidationUtil.validateInputs(inputs)) {
            request.setAttribute("errorMessage", "Entrada inválida. Preencha corretamente os campos.");
            request.getRequestDispatcher("registration.jsp").forward(request, response);
        } else {
            boolean recaptchaValid = RecaptchaUtil.validateRecaptcha(recaptchaResponse);
            if (recaptchaValid) {
                AbstractFactory factory = ApplicationContext.getInstance().getConcreteFactory();
                User user = (User) factory.createObject("User");
                user.setFirstName(firstName);
                user.setLastName(lastName);
                user.setEmail(email);
                user.setPassword(password);
                boolean createUser = registrationService.createUser(user);

                if (createUser) {
                    response.sendRedirect("login.jsp");
                } else {
                    request.setAttribute("errorMessage", "Houve um erro ao registrar o usuario ou o email de " +
                            "cofirmação não foi enviado.");
                    request.getRequestDispatcher("registration.jsp").forward(request, response);
                }
            }
        }
    }
}
