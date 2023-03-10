package com.mrthinkj.kythucac.service.book;

import com.mrthinkj.kythucac.model.book.Book;
import com.mrthinkj.kythucac.model.book.BookShelf;
import com.mrthinkj.kythucac.repository.book.BookshelfRepository;
import com.mrthinkj.kythucac.service.Convert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class BookshelfService {
    @Autowired
    BookshelfRepository bookshelfRepository;
    @Autowired
    Convert convert;
    public List<Map<String, String>> getBookShelf(int accountId){
        return bookshelfRepository.findBookShelfByAccountId(accountId);
    }

    public void toggleBookToBookShelf(int accountId, String bookName){
        Book book = convert.findBookByName(bookName);
        Integer bookshelfId = bookshelfRepository.findBookshelfIdMatchAccountAndBook(accountId, book.getId());
        if (bookshelfId == null){
            bookshelfRepository.addBookToBookShelf(accountId, book.getId());
            return;
        }
        bookshelfRepository.deleteBookFromBookShelf(accountId, book.getId());
    }
}
