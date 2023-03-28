package com.mrthinkj.kythucac.configuration;

import com.mrthinkj.kythucac.filter.SessionFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public FilterRegistrationBean<SessionFilter> sessionFilterFilterRegistrationBean() {
        FilterRegistrationBean<SessionFilter> registration = new FilterRegistrationBean<>();
        registration.setFilter(new SessionFilter());
        registration.addUrlPatterns("/*");
        return registration;
    }
}
