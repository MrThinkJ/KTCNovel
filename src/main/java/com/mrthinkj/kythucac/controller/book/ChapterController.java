package com.mrthinkj.kythucac.controller.book;

import com.mrthinkj.kythucac.model.book.Book;
import com.mrthinkj.kythucac.model.book.Chapter;
import com.mrthinkj.kythucac.model.user.Account;
import com.mrthinkj.kythucac.modelDTO.book.ChapterSimple;
import com.mrthinkj.kythucac.service.convert.Convert;
import com.mrthinkj.kythucac.service.book.BookReadService;
import com.mrthinkj.kythucac.service.book.ChapterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping(method = RequestMethod.GET, path = "/truyen")
public class ChapterController {
    @Autowired
    ChapterService chapterService;
    @Autowired
    BookReadService bookReadService;
    @Autowired
    Convert convert;

    @GetMapping("/{bookName}/{chapterIndex}")
    public Chapter getChapter(@PathVariable String bookName,
                              @PathVariable String chapterIndex,
                              @ModelAttribute("userAccount") Account account) {
        Book book = convert.findBookByName(bookName);
        if (account != null)
            bookReadService.addToBookRead(account, chapterIndex, book);
        return chapterService.getChapterByBookIdAndChapterIndex(book, chapterIndex, account);
    }

    @PostMapping("/{bookName}/buy/{chapterIndex}")
    public void buyChapter(@PathVariable String bookName,
                           @PathVariable String chapterIndex,
                           @ModelAttribute("userAccount") Account account) throws Exception {
        String status = chapterService.buyChapter(bookName, chapterIndex, account);
        if (status == null) {
            return;
        }
        System.out.println(status);
    }

    @GetMapping("/{bookName}/danh-sach-chuong")
    public List<ChapterSimple> showChapterList(@PathVariable String bookName) {
        return chapterService.getChapterSimpleListByBookName(bookName);
    }

    @ExceptionHandler(NullPointerException.class)
    public RedirectView handleException(Exception ex) {
        return new RedirectView("/truyen");
    }

    @ModelAttribute("userAccount")
    public Account getAccount(HttpSession session) {
        return (Account) session.getAttribute("userAccount");
    }
}
