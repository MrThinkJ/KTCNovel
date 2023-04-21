package com.mrthinkj.kythucac.service.book;

import com.mrthinkj.kythucac.model.book.Book;
import com.mrthinkj.kythucac.model.book.BookShelf;
import com.mrthinkj.kythucac.model.user.Account;
import com.mrthinkj.kythucac.repository.book.BookshelfRepository;
import com.mrthinkj.kythucac.service.convert.Convert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class BookshelfService {
    @Autowired
    BookshelfRepository bookshelfRepository;
    @Autowired
    Convert convert;

    public Page<Book> getBookByAccount(Account account, int size, int page){
        Pageable pageable = PageRequest.of(page, size);
        Page<Book> bookPage = bookshelfRepository.findBookByAccount(account.getId(), pageable);
        return bookPage;
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

    public Integer getTotalBookShelfByBook(Book book){
        return bookshelfRepository.countNumberOfBookShelf(book.getId());
    }

    public Boolean isBookshelf(Book book, Account account) {
        if (bookshelfRepository.findBookshelfByAccount(account.getId(), book) == null)
            return false;
        return true;
    }
}
