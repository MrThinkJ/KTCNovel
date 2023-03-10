package com.mrthinkj.kythucac.controller.book;

import com.mrthinkj.kythucac.modelDTO.book.BookSimple;
import com.mrthinkj.kythucac.service.book.BookEvaluateService;
import com.mrthinkj.kythucac.service.book.BookService;
import com.mrthinkj.kythucac.service.book.ChapterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

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
    @GetMapping()
    private List<BookSimple> showBookList(){
        return bookService.getBookList();
    }
    @GetMapping("/{bookName}")
    public List<Object> showBook(@PathVariable String bookName){
        List<Object> objectList = new ArrayList<>();
        objectList.add(bookService.getBook(bookName));
        objectList.add(bookEvaluateService.getNumberOfLikeByBookName(bookName));
        return objectList;
    }
    @ExceptionHandler(Exception.class)
    public RedirectView handleException(Exception ex) {
        return new RedirectView("/truyen");
    }
}
