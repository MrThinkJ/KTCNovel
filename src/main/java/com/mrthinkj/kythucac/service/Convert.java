package com.mrthinkj.kythucac.service;

import com.mrthinkj.kythucac.model.book.Book;
import com.mrthinkj.kythucac.repository.book.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.Entity;
import java.util.List;
@Component
public class Convert {
    @Autowired
    BookRepository bookRepository;

    public Book findBookByName(String bookName){
        return bookRepository.findByNameEquals(bookName.replace("-", " "));
    }
}
