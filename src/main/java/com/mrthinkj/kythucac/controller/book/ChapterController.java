package com.mrthinkj.kythucac.controller.book;

import com.mrthinkj.kythucac.model.book.Chapter;
import com.mrthinkj.kythucac.service.book.ChapterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ChapterController {
    @Autowired
    ChapterService service;
    @GetMapping("/truyen/{bookName}/{chapterIndex}")
    public Chapter getChapter(@PathVariable String bookName,
                              @PathVariable String chapterIndex){
        return service.getChapterByBookIdAndChapterIndex(bookName, chapterIndex);
    }
}
