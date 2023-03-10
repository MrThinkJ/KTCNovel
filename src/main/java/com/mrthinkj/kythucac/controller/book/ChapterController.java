package com.mrthinkj.kythucac.controller.book;

import com.mrthinkj.kythucac.model.book.Chapter;
import com.mrthinkj.kythucac.modelDTO.book.ChapterSimple;
import com.mrthinkj.kythucac.service.book.ChapterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

@RestController
@RequestMapping(method = RequestMethod.GET, path = "/truyen")
public class ChapterController {
    @Autowired
    ChapterService chapterService;

    @GetMapping("/{bookName}/{chapterIndex}")
    public Chapter getChapter(@PathVariable String bookName,
                              @PathVariable String chapterIndex) {
        int accountId = 1;
        return chapterService.getChapterByBookIdAndChapterIndex(bookName, chapterIndex, accountId);
    }

    @PostMapping("/{bookName}/buy/{chapterIndex}")
    public void buyChapter(@PathVariable String bookName,
                           @PathVariable String chapterIndex) throws Exception {
        int accountId = 1;
        String status = chapterService.buyChapter(bookName, chapterIndex, accountId);
        if (status == null){
            return;
        }
        System.out.println(status);
    }
    @GetMapping("/{bookName}/danh-sach-chuong")
    public List<ChapterSimple> showChapterList(@PathVariable String bookName){
        return chapterService.getChapterSimpleListByBookName(bookName);
    }
    @ExceptionHandler(Exception.class)
    public RedirectView handleException(Exception ex) {
        return new RedirectView("/truyen");
    }
}
