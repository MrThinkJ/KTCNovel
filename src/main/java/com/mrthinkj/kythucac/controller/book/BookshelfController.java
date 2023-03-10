package com.mrthinkj.kythucac.controller.book;

import com.mrthinkj.kythucac.service.book.BookshelfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(method = RequestMethod.GET, path = "/nguoi-dung")
public class BookshelfController {
    @Autowired
    BookshelfService bookshelfService;

    @GetMapping("/tu-truyen")
    public List<Map<String, String>> getBookshelf(){
        int accountId =1;
        return bookshelfService.getBookShelf(accountId);
    }

    @PostMapping("/them-truyen/{bookName}")
    public void addBookToBookshelf(@PathVariable String bookName){
        int accountId = 1;
        bookshelfService.toggleBookToBookShelf(1, bookName);
    }
}
