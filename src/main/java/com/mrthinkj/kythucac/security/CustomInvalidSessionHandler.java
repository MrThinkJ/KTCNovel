package com.mrthinkj.kythucac.security;

import org.springframework.security.web.session.InvalidSessionStrategy;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CustomInvalidSessionHandler implements InvalidSessionStrategy {
    private final String destinationUrl;

    public CustomInvalidSessionHandler(String destinationUrl) {
        this.destinationUrl = destinationUrl;
    }

    @Override
    public void onInvalidSessionDetected(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws IOException, ServletException {
        httpServletResponse.sendRedirect(destinationUrl);
    }
}
