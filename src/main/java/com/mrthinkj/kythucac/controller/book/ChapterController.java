package com.mrthinkj.kythucac.controller.book;

import com.mrthinkj.kythucac.model.book.Chapter;
import com.mrthinkj.kythucac.modelDTO.ChapterSimple;
import com.mrthinkj.kythucac.service.book.ChapterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(method = RequestMethod.GET, path = "/truyen")
public class ChapterController {
    @Autowired
    ChapterService chapterService;

    @GetMapping("/{bookName}/{chapterIndex}")
    public Chapter getChapter(@PathVariable String bookName,
                              @PathVariable String chapterIndex){
        return chapterService.getChapterByBookIdAndChapterIndex(bookName, chapterIndex);
    }

    @GetMapping("/{bookName}/danh-sach-chuong")
    public List<ChapterSimple> showChapterList(@PathVariable String bookName){
        return chapterService.getChapterSimpleListByBookName(bookName);
    }
}
