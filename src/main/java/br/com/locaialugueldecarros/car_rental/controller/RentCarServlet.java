package br.com.locaialugueldecarros.car_rental.controller;

import br.com.locaialugueldecarros.car_rental.model.entities.Car;
import br.com.locaialugueldecarros.car_rental.service.*;
import br.com.locaialugueldecarros.util.ApplicationContext;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.sql.SQLException;
import java.time.LocalDateTime;

@WebServlet(name = "RentCarServlet", value = "/alugarCarro")
public class RentCarServlet extends HttpServlet {

    private final AddNewCarService addNewCarService;
    private final CarRentalService rentCarService;
    private final CarDetailsService carDetailsService;
    private final CarInsurerService carInsurerService;
    private final CarOwnersService carOwnersService;

    public RentCarServlet() {
        this.addNewCarService = ApplicationContext.getInstance().getAddNewCarService();
        this.rentCarService = ApplicationContext.getInstance().getCarRentalService();
        this.carDetailsService = ApplicationContext.getInstance().getCarDetailsService();
        this.carInsurerService = ApplicationContext.getInstance().getCarInsurerService();
        this.carOwnersService = ApplicationContext.getInstance().getCarOwnersService();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        String manufacturer = request.getParameter("manufacturer");
        String model = request.getParameter("model");
        int year = Integer.parseInt(request.getParameter("year"));
        String fuel = request.getParameter("fuel");
        String steering = request.getParameter("steering");
        String licensePlate = request.getParameter("licensePlate");
        String color = request.getParameter("color");
        int seats = Integer.parseInt(request.getParameter("seats"));
        int ports = Integer.parseInt(request.getParameter("ports"));
        int pickupAddressId = Integer.parseInt(request.getParameter("pickupAddressId"));
        int returnAddressId = Integer.parseInt(request.getParameter("returnAddressId"));
        LocalDateTime pickupDatetime = LocalDateTime.parse(request.getParameter("pickupDatetime"));
        LocalDateTime returnDatetime = LocalDateTime.parse(request.getParameter("returnDatetime"));
        int insurerId = Integer.parseInt(request.getParameter("insurer"));
        String rentalType = request.getParameter("rentalType");
        double amount = Double.parseDouble(request.getParameter("amount"));
        String paymentMethod = request.getParameter("paymentMethod");

        int userId = (int) request.getSession().getAttribute("userId");

        Car car = new Car(manufacturer, model, year, fuel, steering, licensePlate, color, seats, ports);

        addNewCarService.addNewCar(car);

        try {
            carDetailsService.saveCarDetails(car.getCarId(), amount, paymentMethod);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        carInsurerService.addCarInsurer(car.getCarId(), insurerId);

        carOwnersService.addCarOwnersDetails(car.getCarId(), userId);

        try {
            rentCarService.rentCar(car.getCarId(), pickupAddressId, returnAddressId, pickupDatetime, returnDatetime, rentalType);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}