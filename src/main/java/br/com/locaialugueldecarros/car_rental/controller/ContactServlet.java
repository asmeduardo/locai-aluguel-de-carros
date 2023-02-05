package br.com.locaialugueldecarros.car_rental.controller;

import br.com.locaialugueldecarros.car_rental.model.entities.Messages;
import br.com.locaialugueldecarros.car_rental.service.ContactService;
import br.com.locaialugueldecarros.util.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.Serial;

@WebServlet("/contato")
public class ContactServlet extends HttpServlet {
    @Serial
    private static final long serialVersionUID = 1L;

    private final ContactService contactService;

    public ContactServlet() {
        this.contactService = ApplicationContext.getInstance().getContactService();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String firstName = request.getParameter("name");
        String lastName = request.getParameter("lastname");
        String email = request.getParameter("email");
        String message = request.getParameter("message");
        String recaptchaResponse = request.getParameter("g-recaptcha-response");

        String[] inputs = new String[5];
        inputs[0] = firstName;
        inputs[1] = lastName;
        inputs[2] = email;
        inputs[3] = message;
        inputs[4] = recaptchaResponse;

        if (!InputValidationUtil.validateInputs(inputs)) {
            request.setAttribute("message", "Entrada inválida. Preencha corretamente os campos.");
            request.getRequestDispatcher("contact.jsp").forward(request, response);
        } else {
            boolean recaptchaValid = RecaptchaUtil.validateRecaptcha(recaptchaResponse);
            if (recaptchaValid) {

                AbstractFactory factory = ApplicationContext.getInstance().getConcreteFactory();
                Messages msg = (Messages) factory.createObject("Messages");
                msg.setFirstName(firstName);
                msg.setLastName(lastName);
                msg.setEmail(email);
                msg.setMessage(message);

                if (contactService.saveMessage(msg)) {
                    request.setAttribute("message", "Sua mensagem foi enviada.");
                    request.getRequestDispatcher("contact.jsp").forward(request, response);
                } else {
                    request.setAttribute("message", "Houve um erro ao registrar sua mensagem. Tente novamente.");
                    request.getRequestDispatcher("contact.jsp").forward(request, response);
                }
            }
        }
    }
}
