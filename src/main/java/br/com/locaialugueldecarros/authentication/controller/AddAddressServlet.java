package br.com.locaialugueldecarros.authentication.controller;

import br.com.locaialugueldecarros.authentication.model.User;
import br.com.locaialugueldecarros.authentication.service.AddAddressService;
import br.com.locaialugueldecarros.util.AbstractFactory;
import br.com.locaialugueldecarros.util.ApplicationContext;
import br.com.locaialugueldecarros.util.model.Address;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "AddAddressServlet", value = "/adicionarEndereco")
public class AddAddressServlet extends HttpServlet {
    private final AddAddressService addressService;

    public AddAddressServlet() {
        this.addressService = ApplicationContext.getInstance().getAddressService();
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String street = request.getParameter("street");
        String number = request.getParameter("number");
        String neighborhood = request.getParameter("neighborhood");
        String city = request.getParameter("city");
        String state = request.getParameter("state");

        User user = (User) request.getSession().getAttribute("user");

        AbstractFactory factory = ApplicationContext.getInstance().getConcreteFactory();
        Address address = (Address) factory.createObject("Address");
        address.setStreet(street);
        address.setNumber(number);
        address.setNeighborhood(neighborhood);
        address.setCity(city);
        address.setState(state);
        int userId = user.getId();

        addressService.addAddress(userId, address);

        response.sendRedirect("profile.jsp");
    }
}
