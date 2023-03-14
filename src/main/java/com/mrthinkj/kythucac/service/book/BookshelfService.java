package com.mrthinkj.kythucac.service.book;

import com.mrthinkj.kythucac.model.book.Book;
import com.mrthinkj.kythucac.model.book.BookShelf;
import com.mrthinkj.kythucac.model.user.Account;
import com.mrthinkj.kythucac.repository.book.BookshelfRepository;
import com.mrthinkj.kythucac.service.convert.Convert;
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

    public void toggleBookToBookShelf(Account account, String bookName){
        Book book = convert.findBookByName(bookName);
        BookShelf bookShelf = bookshelfRepository.findByBookAndAccount(book, account);
        if (bookShelf == null){
            bookShelf = new BookShelf(account, book);
            bookshelfRepository.save(bookShelf);
            return;
        }
        bookshelfRepository.delete(bookShelf);
    }
}
