package br.com.locaialugueldecarros.filter;


import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebFilter(urlPatterns = {"/myAccount.jsp", "/login.jsp", "/registration.jsp", "/alugarCarro"})
public class AuthenticationFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        HttpSession session = req.getSession(false);

        // check for session timeout
        if (session != null) {
            long currentTime = System.currentTimeMillis();
            long lastAccessTime = session.getLastAccessedTime();
            long sessionTimeout = session.getMaxInactiveInterval() * 1000L; // convert to milliseconds

            if (currentTime - lastAccessTime > sessionTimeout) {
                session.invalidate();
                res.sendRedirect("login.jsp");
                return;
            }
        }

        String loginURI = req.getContextPath() + "/login.jsp";
        String registerURI = req.getContextPath() + "/registration.jsp";

        boolean loggedIn = session != null && session.getAttribute("user") != null;
        boolean loginRequest = req.getRequestURI().equals(loginURI);
        boolean registerRequest = req.getRequestURI().equals(registerURI);

        if (loggedIn && (loginRequest || registerRequest)) {
            res.sendRedirect(req.getContextPath());
        } else if (loggedIn || loginRequest || registerRequest) {
            chain.doFilter(request, response);
        } else {
            String requestedPage = req.getRequestURI();
            session = req.getSession();
            session.setAttribute("previousUrl", requestedPage);
            res.sendRedirect(loginURI);
        }
    }

    @Override
    public void init(FilterConfig filterConfig) {
    }

    @Override
    public void destroy() {
    }
}
