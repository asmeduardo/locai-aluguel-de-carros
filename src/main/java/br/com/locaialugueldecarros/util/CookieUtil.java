package br.com.locaialugueldecarros.util;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class CookieUtil {
    public static final int COOKIE_AGE = 365 * 24 * 60 * 60; // 1 year in seconds
    public static final String COOKIE_NAME = "rememberMe";

    public static void saveCookie(HttpServletResponse response, String email) {
        Cookie cookie = new Cookie(COOKIE_NAME, email);
        cookie.setMaxAge(COOKIE_AGE);
        response.addCookie(cookie);
    }

    public static String getCookieValue(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (COOKIE_NAME.equals(cookie.getName())) {
                    return cookie.getValue();
                }
            }
        }
        return null;
    }

    public static void deleteCookie(HttpServletResponse response) {
        Cookie cookie = new Cookie(COOKIE_NAME, null);
        cookie.setMaxAge(-1); // set max age to negative value
        response.addCookie(cookie);
    }
}