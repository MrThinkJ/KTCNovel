package com.mrthinkj.kythucac.security;

import com.mrthinkj.kythucac.model.user.Account;
import com.mrthinkj.kythucac.repository.user.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler, AuthenticationFailureHandler {
    @Autowired
    AccountRepository accountRepository;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        Account account = ((CustomUserDetails) authentication.getPrincipal()).getAccount();
        HttpSession session = httpServletRequest.getSession();
        session.setAttribute("userAccount", account);
        Cookie cookie = new Cookie("sessionId", session.getId());
        cookie.setMaxAge(3600); // Cookie expires in 1 hour
        httpServletResponse.addCookie(cookie);
        httpServletResponse.sendRedirect(httpServletRequest.getContextPath() + "/login/success");
    }

    @Override
    public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        String failureUrl = httpServletRequest.getContextPath() +"/login/failed";
        httpServletResponse.sendRedirect(failureUrl);
    }
}
