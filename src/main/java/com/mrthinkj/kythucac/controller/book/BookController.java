package com.mrthinkj.kythucac.controller.book;

import com.mrthinkj.kythucac.model.book.Book;
import com.mrthinkj.kythucac.model.book.Status;
import com.mrthinkj.kythucac.model.user.Account;
import com.mrthinkj.kythucac.modelDTO.book.BookSimple;
import com.mrthinkj.kythucac.service.book.*;
import com.mrthinkj.kythucac.service.convert.Convert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
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
    @Autowired
    BookshelfService bookshelfService;
    @Autowired
    Convert convert;

    @GetMapping("/{bookName}")
    public String showBook(@PathVariable String bookName,
                           @ModelAttribute("userAccount") Account account,
                           Model model) {
        Book book = bookService.getBook(bookName);
        model.addAttribute("book", book);
        addAttribute(model, book);
        model.addAttribute("bookDescription", convert.splitByLine(book.getDescription()));
        if (account != null) {
            Integer mark = bookReadService.getChapterMarkOfBookReadByAccount(account, book);
            model.addAttribute("chapterMark", mark);
        }
        return "book/detail";
    }

    @GetMapping("/{bookName}/danh-gia")
    public String showRate(@PathVariable String bookName,
                           @ModelAttribute("userAccount") Account account,
                           Model model) {
        Book book = bookService.getBook(bookName);
        model.addAttribute("book", book);
        model.addAttribute("rateList", bookEvaluateService.getRateOfBook(book));
        addAttribute(model, book);
        return "book/rate";
    }

    @GetMapping("/{bookName}/binh-luan")
    public String showComment(@PathVariable String bookName,
                              @ModelAttribute("userAccount") Account account,
                              Model model) {
        Book book = bookService.getBook(bookName);
        model.addAttribute("book", book);
        model.addAttribute("commentList", bookEvaluateService.getCommentOfBook(book));
        addAttribute(model, book);
        return "book/comment";
    }

    @GetMapping("/{bookName}/danh-sach-chuong")
    public String showChapterList(@PathVariable String bookName,
                                  @ModelAttribute("userAccount") Account account,
                                  Model model) {
        Book book = bookService.getBook(bookName);
        model.addAttribute("book", book);
        model.addAttribute("chapterList", chapterService.getChapterSimpleListByBookName(bookName));
        addAttribute(model, book);
        return "book/chapter";
    }

    private void addAttribute(Model model, Book book) {
        model.addAttribute("bookLike", bookEvaluateService.getNumberOfLikeByBookName(book));
        model.addAttribute("status", Status.getStatusFromString(book.getStatus().toString()));
        model.addAttribute("totalChapter", chapterService.getTotalChapterByBook(book));
        model.addAttribute("bookShelf", bookshelfService.getTotalBookShelfByBook(book));
        model.addAttribute("averageRate", bookEvaluateService.getAverageRateByBookId(book));
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
