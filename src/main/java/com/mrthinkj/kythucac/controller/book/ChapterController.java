package com.mrthinkj.kythucac.controller.book;

import com.mrthinkj.kythucac.model.book.Book;
import com.mrthinkj.kythucac.model.book.Chapter;
import com.mrthinkj.kythucac.model.user.Account;
import com.mrthinkj.kythucac.modelDTO.book.ChapterSimple;
import com.mrthinkj.kythucac.service.convert.Convert;
import com.mrthinkj.kythucac.service.book.BookReadService;
import com.mrthinkj.kythucac.service.book.ChapterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(method = RequestMethod.GET, path = "/truyen")
public class ChapterController {
    @Autowired
    ChapterService chapterService;
    @Autowired
    BookReadService bookReadService;
    @Autowired
    Convert convert;

    @GetMapping("/{bookName}/{chapterIndex}")
    public String showChapterPage(@PathVariable String bookName,
                                  @PathVariable String chapterIndex,
                                  @ModelAttribute("userAccount") Account account) {
        if (bookName.equals("icon")){
            return null;
        }
        Book book = convert.findBookByName(bookName);
        Chapter chapter = chapterService.getChapterByBookIdAndChapterIndex(book, chapterIndex);
        if (chapter == null)
            return "exception/not-found";
        if (account == null && chapter.isVipStatus())
            return "page/login";
        if (account != null)
            bookReadService.addToBookRead(account, chapterIndex, book);
        return "book/chapter-detail";
    }

    @GetMapping("/api/{bookName}/{chapterIndex}")
    public @ResponseBody List<Object> getChapter(@PathVariable String bookName,
                                                 @PathVariable String chapterIndex,
                                                 @ModelAttribute("userAccount") Account account) {
        Book book = convert.findBookByName(bookName);
        List<Object> objectList = new ArrayList<>();
        String firstLine = chapterService.getFirstLineOfChapter(book, chapterIndex);
        Boolean isLastChapter = chapterService.isLastChapter(book, chapterIndex);
        Chapter chapter = chapterService.getChapterByBookIdAndChapterIndex(book, chapterIndex, account);
        objectList.add(chapter);
        objectList.add(isLastChapter);
        if (chapter.getContent() != null)
            return objectList;
        objectList.add(firstLine);
        return objectList;
    }

    @PostMapping("/{bookName}/buy/{chapterIndex}")
    public @ResponseBody String buyChapter(@PathVariable String bookName,
                           @PathVariable String chapterIndex,
                           @ModelAttribute("userAccount") Account account) throws Exception {
        String status = chapterService.buyChapter(bookName, chapterIndex, account);
        if (status == null)
            return null;
        return status;
    }

    @ExceptionHandler(NullPointerException.class)
    public RedirectView handleException(Exception ex) {
        return new RedirectView("/404");
    }


    @ModelAttribute("userAccount")
    public Account getAccount(HttpSession session) {
        return (Account) session.getAttribute("userAccount");
    }
}
