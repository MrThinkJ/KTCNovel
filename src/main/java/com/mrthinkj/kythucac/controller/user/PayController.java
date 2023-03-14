package com.mrthinkj.kythucac.controller.user;

import com.mrthinkj.kythucac.model.user.Account;
import com.mrthinkj.kythucac.service.user.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/thanh-toan")
public class PayController {
    @Autowired
    AccountService accountService;
    @PostMapping("/{amount}")
    public String recharge(@PathVariable Integer amount,
                           @ModelAttribute("userAccount") Account account) throws Exception {
        accountService.recharge(account, amount);
        return "success";
    }

    @ModelAttribute("userAccount")
    public Account getAccount(HttpSession session){
        return (Account) session.getAttribute("userAccount");
    }
}
