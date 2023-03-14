package com.mrthinkj.kythucac.controller.user;

import com.mrthinkj.kythucac.model.user.Account;
import com.mrthinkj.kythucac.modelDTO.book.BookReadDTO;
import com.mrthinkj.kythucac.service.book.BookReadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping(method = RequestMethod.GET, path = "/tai-khoan")
public class BookReadController {
    @Autowired
    BookReadService bookReadService;
    @GetMapping("/tu-truyen/book-read")
    public List<BookReadDTO> getBookReadList(@ModelAttribute("userAccount") Account account){
        return bookReadService.getAllBookRead(account);
    }

    @ModelAttribute("userAccount")
    public Account getAccount(HttpSession session){
        return (Account) session.getAttribute("userAccount");
    }

    @ExceptionHandler(Exception.class)
    public RedirectView handleException(Exception ex) {
        return new RedirectView("/truyen");
    }
}
