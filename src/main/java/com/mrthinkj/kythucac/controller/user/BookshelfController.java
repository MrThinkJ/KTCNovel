package com.mrthinkj.kythucac.controller.user;

import com.mrthinkj.kythucac.model.book.Book;
import com.mrthinkj.kythucac.model.user.Account;
import com.mrthinkj.kythucac.model.user.Gender;
import com.mrthinkj.kythucac.service.book.BookReadService;
import com.mrthinkj.kythucac.service.book.BookService;
import com.mrthinkj.kythucac.service.book.BookshelfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(method = RequestMethod.GET, path = "/tai-khoan")
public class BookshelfController {
    @Autowired
    BookshelfService bookshelfService;
    @Autowired
    BookReadService bookReadService;
    @Autowired
    BookService bookService;

    @GetMapping("/tu-truyen")
    public String showBookShelf(@ModelAttribute("userAccount") Account account,
                                @RequestParam(value = "page", required=false) Integer page,
                                Model model){
        if (page == null)
            page = 0;
        if (page>=1)
            page-=1;
        int size = 10;
        Page<Book> bookPage = bookshelfService.getBookByAccount(account, size, page);
        model.addAttribute("bookShelfList", bookPage);
        model.addAttribute("user", account.getUser());
        model.addAttribute("gender", Gender.getGenderFromString(account.getUser().getGender().toString()));
        return "user/bookshelf";
    }

    @PostMapping("/tu-truyen/add/{bookName}")
    public String addBookToBookshelf(@PathVariable String bookName,
                                   @ModelAttribute("userAccount") Account account){
        bookshelfService.toggleBookToBookShelf(account, bookName);
        return "redirect:/truyen/"+bookName;
    }

    @GetMapping("/da-doc")
    public String showBookRead(@ModelAttribute("userAccount") Account account,
                                @RequestParam(value = "page", required=false) Integer page,
                                Model model){
        if (page == null)
            page = 0;
        if (page>=1)
            page-=1;
        int size = 10;
        List<List<Object>> bookReadList = bookReadService.getBookReadByAccount(account, size, page);
        Page<Book> bookPage = bookReadService.getBookReadPageByAccount(account, size, page);
        model.addAttribute("bookReadList", bookReadList);
        model.addAttribute("bookPage", bookPage);
        model.addAttribute("user", account.getUser());
        model.addAttribute("gender", Gender.getGenderFromString(account.getUser().getGender().toString()));
        return "user/bookread";
    }

    @PostMapping("/da-doc/remove/{bookName}")
    public String removeBookRead(@ModelAttribute("userAccount") Account account,
                                 @PathVariable String bookName){
        bookReadService.removeFromBookRead(account, bookService.getBook(bookName).getId());
        return "redirect:/tai-khoan/da-doc";
    }

    @ExceptionHandler(Exception.class)
    public String handleException(Exception ex) {
        return "exception/not-found";
    }

    @ModelAttribute("userAccount")
    public Account getAccount(HttpSession session) {
        return (Account) session.getAttribute("userAccount");
    }
}
