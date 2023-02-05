package br.com.locaialugueldecarros.authentication.service;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

public class LogoutService {

    public static void logout(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.invalidate();
    }
}