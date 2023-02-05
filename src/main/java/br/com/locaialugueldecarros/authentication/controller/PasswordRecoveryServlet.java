package br.com.locaialugueldecarros.authentication.controller;

import br.com.locaialugueldecarros.authentication.service.PasswordRecoveryService;
import br.com.locaialugueldecarros.util.ApplicationContext;
import br.com.locaialugueldecarros.util.InputValidationUtil;
import br.com.locaialugueldecarros.util.RecaptchaUtil;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.io.Serial;

@WebServlet("/recuperarSenha")
public class PasswordRecoveryServlet extends HttpServlet {
    @Serial
    private static final long serialVersionUID = 1L;

    private final PasswordRecoveryService passwordRecoveryService;

    public PasswordRecoveryServlet() {
        this.passwordRecoveryService = ApplicationContext.getInstance().getPasswordRecoveryService();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String email = request.getParameter("email");
        String recaptchaResponse = request.getParameter("g-recaptcha-response");

        String[] inputs = new String[2];
        inputs[0] = email;
        inputs[1] = recaptchaResponse;

        if (!InputValidationUtil.validateInputs(inputs)) {
            request.setAttribute("errorMessage", "Entrada inválida. Preencha corretamente os campos.");
            request.getRequestDispatcher("password-recovery.jsp").forward(request, response);
        } else {
            boolean recaptchaValid = RecaptchaUtil.validateRecaptcha(recaptchaResponse);

            if (recaptchaValid) {
                boolean isRecovery = passwordRecoveryService.sendPasswordRecoveryEmail(email);

                if (isRecovery) {
                    response.sendRedirect("login.jsp");
                } else {
                    request.setAttribute("errorMessage", "Email inválido.");
                    request.getRequestDispatcher("password-recovery.jsp").forward(request, response);
                }
            }
        }
    }
}
