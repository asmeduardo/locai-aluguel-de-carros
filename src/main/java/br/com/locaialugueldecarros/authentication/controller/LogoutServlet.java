package br.com.locaialugueldecarros.authentication.controller;

import br.com.locaialugueldecarros.authentication.service.LogoutService;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.io.Serial;

@WebServlet("/sair")
public class LogoutServlet extends HttpServlet {
    @Serial
    private static final long serialVersionUID = 1L;
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        LogoutService.logout(request);
        response.sendRedirect("login.jsp");

    }
}
