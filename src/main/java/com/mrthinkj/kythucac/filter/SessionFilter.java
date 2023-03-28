package com.mrthinkj.kythucac.filter;

import com.mrthinkj.kythucac.model.user.Account;
import com.mrthinkj.kythucac.repository.user.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Component
public class SessionFilter implements Filter {
    @Autowired
    AccountRepository accountRepository;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;

        String sessionId = null;
        for (Cookie cookie : httpRequest.getCookies()) {
            if ("sessionId".equals(cookie.getName())) {
                sessionId = cookie.getValue();
                break;
            }
        }

        HttpSession session = httpRequest.getSession(false);
        if (session == null && sessionId != null) {
            session = httpRequest.getSession();
            String username = httpRequest.getUserPrincipal().getName();
            // Retrieve the user's account data from the session and set it as a session attribute
            // You can use the same code as in your authentication success handler to retrieve the account data
            Account account = accountRepository.findByUsername(username);
            session.setAttribute("userAccount", account);
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
    }
}
