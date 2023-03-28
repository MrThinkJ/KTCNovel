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
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping(method = RequestMethod.GET, path = "/truyen")
public class EvaluateBookController {
    @Autowired
    BookEvaluateService bookEvaluateService;
    @PostMapping("/{bookName}/like")
    @CheckAccount
    public void addLikeToBook(@ModelAttribute("userAccount") Account account,
                              @PathVariable String bookName){
        bookEvaluateService.toggleLikeToBook(bookName, account);
    }

    @PostMapping("/{bookName}/view")
    public void addViewToBook(@PathVariable String bookName){
        bookEvaluateService.addViewToBook(bookName);
    }

    @PostMapping("/{bookName}/process-rate")
    @CheckAccount
    public String addRateToBook(@ModelAttribute("userAccount") Account account,
                              @PathVariable String bookName,
                              @ModelAttribute("rate") Rate rate){
        bookEvaluateService.addRateToBook(account, bookName, rate);
        return "success";
    }

    @PostMapping("/{bookName}/process-comment")
    @CheckAccount
    public String addCommentToBook(@ModelAttribute("userAccount") Account account,
                                 @PathVariable String bookName,
                                 @ModelAttribute("rate") Comment comment){
        bookEvaluateService.addCommentToBook(account, bookName, comment);
        return "success";
    }

    @ExceptionHandler(NullPointerException.class)
    public String handleException(Exception ex) {
        return "404";
    }

    @ExceptionHandler(ForbiddenException.class)
    public String handleForbidden(Exception ex) {
        return "403";
    }

    @ModelAttribute("userAccount")
    public Account getAccount(HttpSession session){
        return (Account) session.getAttribute("userAccount");
    }
}
