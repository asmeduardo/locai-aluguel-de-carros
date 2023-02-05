package br.com.locaialugueldecarros.car_rental.controller;

import br.com.locaialugueldecarros.car_rental.model.entities.Car;
import br.com.locaialugueldecarros.car_rental.service.SearchCarService;
import br.com.locaialugueldecarros.util.ApplicationContext;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "SearchCarsServlet", value = "/buscarCarros")
public class SearchCarsServlet extends HttpServlet {
    private final SearchCarService searchCarService;

    public SearchCarsServlet() {
        this.searchCarService = ApplicationContext.getInstance().getSearchCarService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Car> availableCars = searchCarService.getAvailableCars();
        request.setAttribute("availableCars", availableCars);
        request.getRequestDispatcher("cars.jsp").forward(request, response);
    }
}