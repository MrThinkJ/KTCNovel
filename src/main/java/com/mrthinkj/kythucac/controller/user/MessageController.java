package com.mrthinkj.kythucac.controller.user;

import com.mrthinkj.kythucac.model.system.Message;
import com.mrthinkj.kythucac.model.user.Account;
import com.mrthinkj.kythucac.service.system.MessageService;
import com.mrthinkj.kythucac.service.user.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.sql.SQLDataException;
import java.util.HashSet;
import java.util.Set;

@Controller
@RequestMapping("/tin-nhan")
public class MessageController {
    @Autowired
    MessageService messageService;
    @Autowired
    AccountService accountService;

    @GetMapping()
    public String showAllNewMessage(@ModelAttribute("userAccount") Account account,
                                    Model model){
        model.addAttribute("messageList", messageService.getNewestMessageListByAccount(account));
        model.addAttribute("account", account);
        return "user/total-message";
    }

    @GetMapping("/{accountId}")
    public String showMessageBetweenTwoAccount(@ModelAttribute("userAccount") Account account,
                                               @PathVariable Integer accountId,
                                               Model model){
        if (accountId == account.getId()){
            return "exception/not-found";
        }
        Account toAccount = accountService.getAccountById(accountId);
        model.addAttribute("messageList", messageService.getMessageListBetweenTwoAccount(account, toAccount));
        model.addAttribute("toAccount", toAccount);
        model.addAttribute("message", new Message());
        return "user/message";
    }

    @PostMapping("/send/{accountId}")
    public @ResponseBody String sendMessage(@ModelAttribute("userAccount") Account account,
                                            @PathVariable Integer accountId,
                                            @ModelAttribute("message") Message message,
                                            BindingResult result){
        if (result.hasErrors()){
            return result.getFieldError().getDefaultMessage();
        }
        String status = messageService.sendMessage(account, accountId, message);
        return status;
    }

    @GetMapping("/api/unseen")
    public @ResponseBody Boolean hasUnseenMessage(@ModelAttribute("userAccount") Account account){
        return messageService.hasUnseenMessage(account);
    }

    @ModelAttribute("userAccount")
    public Account getAccount(HttpSession session){
        return (Account) session.getAttribute("userAccount");
    }
}
