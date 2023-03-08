package com.mrthinkj.kythucac.service.book;

import com.mrthinkj.kythucac.model.book.Book;
import com.mrthinkj.kythucac.model.book.Chapter;
import com.mrthinkj.kythucac.model.book.Type;
import com.mrthinkj.kythucac.repository.book.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {
    @Autowired
    BookRepository bookRepository;

    public List<Book> getBookList() {
        return bookRepository.findAll();
    }

    public List<Book> get10BookHighestView() {
        return bookRepository.findTop10ByOrderByViewDesc();
    }

    public List<Book> get10BookHighestLike() {
        return bookRepository.findTop10ByOrderByLikeDesc();
    }

    public List<Book> get10BookHighestComment(){
        return bookRepository.findTop10ByCommentCount();
    }
    public List<Chapter> getBookChapterByBookId(int bookId) {
        return bookRepository.findChapterListById(bookId);
    }

    public void addBook(Book book) {
        bookRepository.save(book);
    }

    public Book getBook(String bookName) {
        String bookNameRemove = bookName.replace("-", " ");
        return bookRepository.findByNameEquals(bookNameRemove);
    }
}
