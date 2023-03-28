package com.mrthinkj.kythucac.controller.admin;

import com.mrthinkj.kythucac.model.user.Account;
import com.mrthinkj.kythucac.service.system.TransactionService;
import com.mrthinkj.kythucac.service.user.AccountService;
import com.mrthinkj.kythucac.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping(method = RequestMethod.GET, path = "/admin")
public class AdminController {
    @Autowired
    TransactionService transactionService;
    @Autowired
    UserService userService;
    @Autowired
    AccountService accountService;

    @GetMapping("")
    public String showTransactionManagePage(Model model){
        model.addAttribute("transactionList", transactionService.getListUnprocessedTransaction());
        return "admin/manage-transaction";
    }

    @PostMapping("/manage-transaction/{choose}/{transactionId}")
    public String processTransaction(@PathVariable String choose,
                                     @PathVariable Integer transactionId){
        if (choose.equals("confirm")){
            transactionService.confirmTransaction(transactionId);
        }
        if (choose.equals("cancel")){
            transactionService.cancelTransaction(transactionId);
        }
        return "admin/manage-transaction";
    }

    @GetMapping("/recharge-account")
    public String showRechargePage(Model model){
        model.addAttribute("userList", userService.getAllUser());
        return "admin/recharge-account";
    }

    @PostMapping("/recharge-account/{id}")
    public String rechargeAccount(@PathVariable int id,
                                  @RequestParam int amount) throws Exception {
        accountService.recharge(accountService.getAccountById(id), amount);
        return "redirect:/admin";
    }

    @ExceptionHandler(NullPointerException.class)
    public RedirectView handleException(Exception ex) {
        return new RedirectView("/404");
    }


    @ModelAttribute("userAccount")
    public Account getAccount(HttpSession session) {
        return (Account) session.getAttribute("userAccount");
    }
}
