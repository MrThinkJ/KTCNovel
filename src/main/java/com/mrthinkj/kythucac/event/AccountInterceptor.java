package com.mrthinkj.kythucac.event;

import com.mrthinkj.kythucac.model.user.Account;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
@Component
public class AccountInterceptor {
//    @Override
//    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//        HttpSession session = request.getSession();
//        Account account = (Account) session.getAttribute("userAccount");
//
////        if (account != null && !account.isEnabled()){
////            response.sendRedirect(request.getContextPath()+"/not-active");
////            return false;
////        }
//        return true;
//    }
}
