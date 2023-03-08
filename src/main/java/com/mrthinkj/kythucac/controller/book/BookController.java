package com.mrthinkj.kythucac.controller.book;

import com.mrthinkj.kythucac.model.book.Book;
import com.mrthinkj.kythucac.model.book.Chapter;
import com.mrthinkj.kythucac.service.book.BookService;
import com.mrthinkj.kythucac.service.book.ChapterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(method = RequestMethod.GET, path = "/truyen")
@ComponentScan("service")
public class BookController {
    @Autowired
    BookService bookService;
    @Autowired
    ChapterService chapterService;
    @GetMapping()
    private List<Book> showBookList(){
        return bookService.getBookList();
    }
    @GetMapping("/{bookName}")
    public Book showBook(@PathVariable String bookName){
        return bookService.getBook(bookName);
    }
    @GetMapping("/{bookName}/danh-sach-chuong")
    public List<Chapter> showChapterList(@PathVariable String bookName){
        return chapterService.getChapterListByBookName(bookName);
    }

}
