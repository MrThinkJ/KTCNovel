package com.mrthinkj.kythucac.controller.user;

import com.mrthinkj.kythucac.model.user.Account;
import com.mrthinkj.kythucac.service.book.BookshelfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(method = RequestMethod.GET, path = "/tai-khoan")
public class BookshelfController {
    @Autowired
    BookshelfService bookshelfService;
    @GetMapping("/tu-truyen/bookshelf")
    public List<Map<String, String>> getBookshelf(@ModelAttribute("userAccount") Account account){
        return bookshelfService.getBookShelf(account.getId()); // bookName, bookImage
    }

    @PostMapping("/tu-truyen/add/{bookName}")
    public void addBookToBookshelf(@PathVariable String bookName,
                                   @ModelAttribute("userAccount") Account account){
        bookshelfService.toggleBookToBookShelf(account, bookName);
    }

    @ExceptionHandler(Exception.class)
    public RedirectView handleException(Exception ex) {
        return new RedirectView("/truyen");
    }

    @ModelAttribute("userAccount")
    public Account getAccount(HttpSession session) {
        return (Account) session.getAttribute("userAccount");
    }
}
