package com.mrthinkj.kythucac.security;

import com.mrthinkj.kythucac.model.user.Account;
import com.mrthinkj.kythucac.repository.user.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributesModelMap;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Collection;
import java.util.Map;

@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler, AuthenticationFailureHandler {
    @Autowired
    AccountRepository accountRepository;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        Account account = ((CustomUserDetails) authentication.getPrincipal()).getAccount();
        HttpSession session = httpServletRequest.getSession();
        session.setAttribute("userAccount", account);
        httpServletResponse.sendRedirect("/login/success");
    }

    @Override
    public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        if (e instanceof DisabledException) {
            String username = httpServletRequest.getParameter("username");
            httpServletResponse.sendRedirect("/resendConfirmRegistration?username="+username);
            return;
        }
        String failureUrl = httpServletRequest.getContextPath() +"/login/failed";
        httpServletResponse.sendRedirect(failureUrl);
    }
}
