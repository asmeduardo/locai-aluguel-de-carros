package br.com.locaialugueldecarros.authentication.service;

import br.com.locaialugueldecarros.util.CookieUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.Objects;

public class RememberMeService {

    public static void saveRememberMeCookie(HttpServletResponse response, String email) {
        CookieUtil.saveCookie(response, email);
    }

    public static String getRememberedEmail(HttpServletRequest request) {
        if (!Objects.equals(CookieUtil.getCookieValue(request), "")) {
            return CookieUtil.getCookieValue(request);
        } else {
            return null;
        }
    }

    public static void deleteCookie(HttpServletResponse response) {
        CookieUtil.deleteCookie(response);
    }

    public static boolean isRememberMe(HttpServletRequest request) {
        return (getRememberedEmail(request) != null);
    }

}