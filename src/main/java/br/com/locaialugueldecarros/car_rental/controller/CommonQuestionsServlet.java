package br.com.locaialugueldecarros.car_rental.controller;

import br.com.locaialugueldecarros.car_rental.model.entities.CommonQuestions;
import br.com.locaialugueldecarros.car_rental.service.CommonQuestionsService;
import br.com.locaialugueldecarros.util.ApplicationContext;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.io.Serial;
import java.util.List;

@WebServlet(name = "CommonQuestionsServlet", value = "/faq")
public class CommonQuestionsServlet extends HttpServlet {
    @Serial
    private static final long serialVersionUID = 1L;

    private final CommonQuestionsService commonQuestionsService;

    public CommonQuestionsServlet() {
        this.commonQuestionsService = ApplicationContext.getInstance().getCommonQuestionsService();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<CommonQuestions> faqs = commonQuestionsService.getAllFAQs();
        request.setAttribute("faqs", faqs);
        RequestDispatcher dispatcher = request.getRequestDispatcher("faq.jsp");
        dispatcher.forward(request, response);
    }
}