package com.mrthinkj.kythucac.controller.authentication;

import com.mrthinkj.kythucac.model.user.Account;
import com.mrthinkj.kythucac.service.user.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
public class LoginController {
    @Autowired
    private AccountService accountService;

    @GetMapping("/login")
    public String login() {
        return "page/login";
    }

    @PostMapping("/login")
    public String authLogin() {
        return "redirect:/";
    }

    @GetMapping("/login/{status}")
    public @ResponseBody String failedLogin(@PathVariable String status){
        if (status.equals("failed"))
            return "failed";
        return "success";
    }
}
