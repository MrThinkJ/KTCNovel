package com.mrthinkj.kythucac.controller.book;

import com.mrthinkj.kythucac.model.book.Book;
import com.mrthinkj.kythucac.model.book.Chapter;
import com.mrthinkj.kythucac.service.book.BookEvaluateService;
import com.mrthinkj.kythucac.service.book.BookService;
import com.mrthinkj.kythucac.service.book.ChapterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
    private List<Book> showBookList(){
        return bookService.getBookList();
    }
    @GetMapping("/{bookName}")
    public List<Object> showBook(@PathVariable String bookName){
        List<Object> objectList = new ArrayList<>();
        objectList.add(bookService.getBook(bookName));
        objectList.add(bookEvaluateService.getNumberOfLikeByBookName(bookName));
        return objectList;
    }

}
