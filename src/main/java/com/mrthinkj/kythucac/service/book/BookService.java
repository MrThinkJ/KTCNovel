package com.mrthinkj.kythucac.service.book;

import com.mrthinkj.kythucac.model.book.Book;
import com.mrthinkj.kythucac.model.book.Chapter;
import com.mrthinkj.kythucac.modelDTO.book.BookSimple;
import com.mrthinkj.kythucac.repository.book.BookRepository;
import com.mrthinkj.kythucac.repository.book.LikeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookService {
    @Autowired
    BookRepository bookRepository;
    @Autowired
    LikeRepository likeRepository;

    public List<BookSimple> getBookList() {
        return bookRepository.findAllBookConvert();
    }

    public List<Book> get10BookHighestView() {
        return bookRepository.findTop10ByOrderByViewDesc();
    }

    public List<Book> get10BookHighestLike() {
        List<Integer> tenBookIdHighestView = likeRepository.get10BookIdHighestLike();
        List<Book> tenBookHighestView = new ArrayList<>();
        for (Integer bookId : tenBookIdHighestView)
            tenBookHighestView.add(bookRepository.findById(bookId.intValue()));
        return tenBookHighestView;
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
