package com.mrthinkj.kythucac.controller.book;

import com.mrthinkj.kythucac.model.book.Book;
import com.mrthinkj.kythucac.service.book.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

@RestController
@RequestMapping(method = RequestMethod.GET, path = "/bang-xep-hang")
public class RankingController {
    @Autowired
    BookService bookService;

    @GetMapping()
    public List<Book> getBookListHasHighestView() {
        return bookService.get10BookHighestView();
    }

    @GetMapping("/{type}")
    public List<Book> getBookListByType(@PathVariable String type) {
        if (type.equals("doc-nhieu"))
            return bookService.get10BookHighestView();
        if (type.equals("yeu-thich"))
            return bookService.get10BookHighestLike();
        return null;
    }

    @ExceptionHandler(Exception.class)
    public RedirectView handleException(Exception ex) {
        return new RedirectView("/truyen");
    }
}
