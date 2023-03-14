package com.mrthinkj.kythucac.controller.authentication;

import com.mrthinkj.kythucac.model.user.Account;
import com.mrthinkj.kythucac.service.user.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;

@Controller
public class LoginController {
    @Autowired
    private AccountService accountService;

    @GetMapping("/login")
    public void login(HttpSession session){
        String username = "thinhtest2";
        String password = "test";
        Account account = accountService.getAccountByUsernameAndPassword(username, password);
        session.setAttribute("userAccount", account);
    }
}
