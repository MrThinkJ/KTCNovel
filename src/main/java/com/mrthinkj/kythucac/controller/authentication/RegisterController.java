package com.mrthinkj.kythucac.controller.authentication;

import com.mrthinkj.kythucac.event.OnRegistrationCompleteEvent;
import com.mrthinkj.kythucac.exception.UserAlreadyExistException;
import com.mrthinkj.kythucac.model.user.Account;
import com.mrthinkj.kythucac.model.user.Role;
import com.mrthinkj.kythucac.model.user.User;
import com.mrthinkj.kythucac.model.user.VerificationToken;
import com.mrthinkj.kythucac.modelDTO.user.AccountRegisterDTO;
import com.mrthinkj.kythucac.repository.user.RoleRepository;
import com.mrthinkj.kythucac.service.user.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Controller
public class RegisterController {
    @Autowired
    ApplicationEventPublisher publisher;
    @Autowired
    AccountService accountService;
    @Autowired
    RoleRepository roleRepository;

    @PostMapping("/register")
    public @ResponseBody String addNewAccount(@ModelAttribute("accountRegister") @Valid AccountRegisterDTO accountRegisterDTO,
                                              BindingResult result){
        try{
            if (result.hasErrors()){
                List<ObjectError> errors = result.getAllErrors();
                return errors.get(0).getDefaultMessage();
            }
            Account accountRegistered = accountService.registerNewAccount(accountRegisterDTO);
            String url = "http://localhost:8080";
            publisher.publishEvent(new OnRegistrationCompleteEvent(accountRegistered, url));
        } catch (UserAlreadyExistException userAlreadyExistException){
            return userAlreadyExistException.getMessage();
        }
        catch (RuntimeException runtimeException){
            return runtimeException.getMessage();
        }
        return "success";
    }

    @GetMapping("/confirmRegistration")
    public String confirmRegistration(@RequestParam("token") String token,
                                      Model model){
        VerificationToken verificationToken = accountService.getVerificationToken(token);
        if (verificationToken == null){
            model.addAttribute("message", "Đường dẫn xác nhận email không hợp lệ");
            return "page/confirm-registration";
        }
        Account account = verificationToken.getAccount();
        Calendar calendar = Calendar.getInstance();
        if (verificationToken.getExpiryDate().getTime() - calendar.getTime().getTime() <=0){
            model.addAttribute("message", "Đường dẫn đã hết hạn");
            return "page/confirm-registration";
        }
        account.setEnabled(true);
        accountService.saveAccount(account);
        model.addAttribute("message", "Đã xác nhận email thành công");
        return "page/confirm-registration";
    }
}
