package com.mrthinkj.kythucac.controller.book;

import com.mrthinkj.kythucac.model.book.Book;
import com.mrthinkj.kythucac.service.book.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

@Controller
@RequestMapping(method = RequestMethod.GET, path = "/bang-xep-hang")
public class RankingController {
    @Autowired
    BookService bookService;

    @GetMapping()
    public List<Book> getBookListHasHighestView() {
        return bookService.get10BookHighestView();
    }

    @GetMapping("/{type}")
    public String getBookListByType(@PathVariable String type,
                                        Model model) {
        if (type.equals("doc-nhieu")){
            model.addAttribute("bookList", bookService.get10BookHighestView());
            model.addAttribute("active", 1);
        }
        if (type.equals("yeu-thich")){
            model.addAttribute("bookList", bookService.get10BookHighestLike());
            model.addAttribute("active", 0);
        }
        return "page/ranking";
    }

    @ExceptionHandler(Exception.class)
    public RedirectView handleException(Exception ex) {
        return new RedirectView("/404");
    }
}
