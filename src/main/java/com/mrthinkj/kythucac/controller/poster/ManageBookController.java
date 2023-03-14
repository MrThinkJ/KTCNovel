package com.mrthinkj.kythucac.controller.poster;

import com.mrthinkj.kythucac.annotation.CheckAccount;
import com.mrthinkj.kythucac.annotation.CheckBookAccount;
import com.mrthinkj.kythucac.exception.ForbiddenException;
import com.mrthinkj.kythucac.model.book.Book;
import com.mrthinkj.kythucac.model.book.Type;
import com.mrthinkj.kythucac.model.user.Account;
import com.mrthinkj.kythucac.modelDTO.book.BookSimple;
import com.mrthinkj.kythucac.service.book.BookService;
import com.mrthinkj.kythucac.service.filter.TypeService;
import com.mrthinkj.kythucac.service.poster.PosterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping(method = RequestMethod.GET, path = "/nguoi-dang")
public class ManageBookController {
    @Autowired
    TypeService typeService;
    @Autowired
    PosterService posterService;
    @Autowired
    BookService bookService;

    @GetMapping()
    @CheckAccount
    public List<BookSimple> getListBookByPoster(@ModelAttribute("userAccount") Account account) {
        return posterService.getListBookByPoster(account);
    }

    @GetMapping("/novel/add")
    @CheckAccount
    public String showFormAddBook(@ModelAttribute("userAccount") Account account,
                                  Model model) {
        model.addAttribute("book", new Book());
        if (!model.containsAttribute("type"))
            model.addAttribute("type", new Type());
        List<Type> typeList = typeService.findAllType();
        model.addAttribute("typeList", typeList);
        return "post-novel";
    }

    @PostMapping("/novel/add/process")
    public @ResponseBody String addNewBook(@ModelAttribute("book") Book book,
                                           @ModelAttribute("userAccount") Account account) {
        List<Type> typeList = book.getTypeList();
        bookService.addNewBook(book, typeList, account);
        return "success";
    }

    @GetMapping("/novel/update/{bookName}")
    @CheckBookAccount
    public String showFormUpdateBook(@ModelAttribute("userAccount") Account account,
                                 @PathVariable String bookName,
                                 Model model) {
        Book book = bookService.getBook(bookName);
        model.addAttribute(book);
        if (!model.containsAttribute("type"))
            model.addAttribute("type", new Type());
        List<Type> typeList = typeService.findAllType();
        model.addAttribute("typeList", typeList);
        return "update-novel";
    }

    @PostMapping("/novel/update/{bookName}/process")
    @CheckBookAccount
    public @ResponseBody String updateBook(@ModelAttribute("userAccount") Account account,
                                           @PathVariable String bookName,
                                           @ModelAttribute("book") Book book){
        List<Type> typeList = book.getTypeList();
        bookService.updateBook(book, typeList, account);
        return "success";
    }

    @ModelAttribute("userAccount")
    public Account getAccount(HttpSession session) {
        return (Account) session.getAttribute("userAccount");
    }

    @ExceptionHandler(ForbiddenException.class)
    public String handleForbiddenException() {
        return "403";
    }

    @ExceptionHandler(NullPointerException.class)
    public String handleNullPointerException(){
        return "404";
    }
}
