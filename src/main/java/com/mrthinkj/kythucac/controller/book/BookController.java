package com.mrthinkj.kythucac.controller.book;

import com.mrthinkj.kythucac.model.book.Book;
import com.mrthinkj.kythucac.model.user.Account;
import com.mrthinkj.kythucac.modelDTO.book.BookSimple;
import com.mrthinkj.kythucac.service.book.BookEvaluateService;
import com.mrthinkj.kythucac.service.book.BookReadService;
import com.mrthinkj.kythucac.service.book.BookService;
import com.mrthinkj.kythucac.service.book.ChapterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(method = RequestMethod.GET, path = "/truyen")
@ComponentScan("service")
public class BookController {
    @Autowired
    BookService bookService;
    @Autowired
    ChapterService chapterService;
    @Autowired
    BookEvaluateService bookEvaluateService;
    @Autowired
    BookReadService bookReadService;

    @GetMapping()
    private List<BookSimple> showBookList() {
        return bookService.getBookList();
    }

    @GetMapping("/{bookName}")
    public List<Object> showBook(@PathVariable String bookName,
                                 @ModelAttribute("userAccount") Account account) {
        List<Object> objectList = new ArrayList<>();
        Book book = bookService.getBook(bookName);
        objectList.add(book);
        objectList.add(bookEvaluateService.getNumberOfLikeByBookName(bookName));
        if (account != null)
            objectList.add(bookReadService.getChapterMarkOfBookReadByAccount(account, book));
        return objectList;
    }

    @ExceptionHandler(Exception.class)
    public RedirectView handleException(Exception ex) {
        return new RedirectView("/truyen");
    }

    @ModelAttribute("userAccount")
    public Account getAccount(HttpSession session){
        return (Account) session.getAttribute("userAccount");
    }
}
