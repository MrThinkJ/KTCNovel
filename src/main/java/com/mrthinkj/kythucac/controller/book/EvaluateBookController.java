package com.mrthinkj.kythucac.controller.book;

import com.mrthinkj.kythucac.annotation.CheckAccount;
import com.mrthinkj.kythucac.annotation.CheckBookAccount;
import com.mrthinkj.kythucac.exception.ForbiddenException;
import com.mrthinkj.kythucac.model.book.Comment;
import com.mrthinkj.kythucac.model.book.Rate;
import com.mrthinkj.kythucac.model.user.Account;
import com.mrthinkj.kythucac.service.book.BookEvaluateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;
@Controller
@RequestMapping(method = RequestMethod.GET, path = "/truyen")
public class EvaluateBookController {
    @Autowired
    BookEvaluateService bookEvaluateService;

    @PostMapping("/{bookName}/like")
    @CheckAccount
    public String addLikeToBook(@ModelAttribute("userAccount") Account account,
                              @PathVariable String bookName) {
        bookEvaluateService.toggleLikeToBook(bookName, account);
        return "redirect:/truyen/"+bookName;
    }

    @PostMapping("/{bookName}/view")
    public @ResponseBody void addViewToBook(@PathVariable String bookName) {
        bookEvaluateService.addViewToBook(bookName);
    }

    @PostMapping("/{bookName}/process-rate")
    @CheckAccount
    public @ResponseBody String addRateToBook(@ModelAttribute("userAccount") Account account,
                                @PathVariable String bookName,
                                @Valid @ModelAttribute("rate") Rate rate,
                                BindingResult result) {
        if (account == null)
            return "null";
        if (result.hasErrors()){
            return "error";
        }
        bookEvaluateService.addRateToBook(account, bookName, rate);
        return "success";
    }

    @PostMapping("/{bookName}/process-comment")
    @CheckAccount
    public @ResponseBody String addCommentToBook(@ModelAttribute("userAccount") Account account,
                                   @PathVariable String bookName,
                                   @Valid @ModelAttribute("rate") Comment comment,
                                   BindingResult result) {
        if (account == null)
            return "null";
        if (result.hasErrors()){
            return "error";
        }
        bookEvaluateService.addCommentToBook(account, bookName, comment);
        return "success";
    }

    @ExceptionHandler(NullPointerException.class)
    public String handleException(Exception ex) {
        return "exception/not-found";
    }

    @ExceptionHandler(ForbiddenException.class)
    public String handleForbidden(Exception ex) {
        return "exception/access-denied";
    }

    @ModelAttribute("userAccount")
    public Account getAccount(HttpSession session) {
        return (Account) session.getAttribute("userAccount");
    }
}
