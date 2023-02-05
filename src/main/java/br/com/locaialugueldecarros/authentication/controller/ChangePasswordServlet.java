package br.com.locaialugueldecarros.authentication.controller;

import br.com.locaialugueldecarros.authentication.model.User;
import br.com.locaialugueldecarros.authentication.service.LogoutService;
import br.com.locaialugueldecarros.authentication.service.ChangePasswordService;
import br.com.locaialugueldecarros.util.ApplicationContext;
import br.com.locaialugueldecarros.util.PasswordUtil;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.io.Serial;

@WebServlet(name = "ChangePasswordServlet", value = "/mudarSenha")
public class ChangePasswordServlet extends HttpServlet {
    @Serial
    private static final long serialVersionUID = 1L;

    private final ChangePasswordService changePasswordService;

    public ChangePasswordServlet() {
        this.changePasswordService = ApplicationContext.getInstance().getChangePasswordService();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String currentPassword = request.getParameter("currentPassword");
        String newPassword = request.getParameter("newPassword");
        String confirmPassword = request.getParameter("confirmPassword");

        User user = (User) request.getSession().getAttribute("user");

        if (PasswordUtil.compareDecryptedPassword(currentPassword, user.getPassword())) {
            if (newPassword.equals(confirmPassword)) {
                user.setPassword(PasswordUtil.encryptPassword(newPassword));
                boolean success = changePasswordService.changePassword(user);
                if (success) {
                    LogoutService.logout(request);
                    response.sendRedirect("login.jsp");
                } else {
                    request.setAttribute("message", "Ocorreu um erro ao alterar a senha");
                    request.getRequestDispatcher("myAccount.jsp").forward(request, response);
                }
            } else {
                request.setAttribute("message", "Nova senha e confirmação não correspondem");
                request.getRequestDispatcher("myAccount.jsp").forward(request, response);
            }
        } else {
            request.setAttribute("message", "A senha atual está incorreta");
            request.getRequestDispatcher("myAccount.jsp").forward(request, response);
        }
    }
}