package com.mrthinkj.kythucac.service.book;

import com.mrthinkj.kythucac.model.book.Book;
import com.mrthinkj.kythucac.model.book.Chapter;
import com.mrthinkj.kythucac.model.book.Status;
import com.mrthinkj.kythucac.model.book.Type;
import com.mrthinkj.kythucac.model.user.Account;
import com.mrthinkj.kythucac.modelDTO.book.BookSimple;
import com.mrthinkj.kythucac.modelDTO.book.ChapterSimple;
import com.mrthinkj.kythucac.repository.book.BookRepository;
import com.mrthinkj.kythucac.repository.book.BookshelfRepository;
import com.mrthinkj.kythucac.repository.book.ChapterRepository;
import com.mrthinkj.kythucac.repository.book.LikeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class BookService {
    @Autowired
    BookRepository bookRepository;
    @Autowired
    LikeRepository likeRepository;
    @Autowired
    BookshelfRepository bookshelfRepository;
    @Autowired
    ChapterRepository chapterRepository;

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

    public List<BookSimple> get3BookHighestView() {
        return bookRepository.find3BookHighestViewConvert();
    }

    public List<BookSimple> get3BookHighestLike() {
        List<Integer> highestLikeBookId = likeRepository.get3BookIdHighestLike();
        List<BookSimple> bookSimpleList = new ArrayList<>();
        for (Integer bookId : highestLikeBookId) {
            bookSimpleList.add(bookRepository.findBookSimpleByIdConvert(bookId));
        }
        return bookSimpleList;
    }

    public List<BookSimple> getBookRecent() {
        return bookRepository.findBookRecentConvert();
    }

    public List<List<Object>> getBookRecentHome() {
        List<List<Object>> bookRecentList = new ArrayList<>();
        List<Book> bookList = bookRepository.findFirst10ByChapter();
        bookList.stream().forEach(item -> {
            List<Object> objectList = new ArrayList<>();
            objectList.add(item);
            objectList.add(chapterRepository.findLastChapterSimpleByBookIdConvert(item.getId()));
            bookRecentList.add(objectList);
        });
        return bookRecentList;
    }

    public List<BookSimple> get3BookHighestBookShelf() {
        List<Integer> highestBookShelfBookId = bookshelfRepository.get3BookIdHighestBookShelf();
        List<BookSimple> bookSimpleList = new ArrayList<>();
        for (Integer bookId : highestBookShelfBookId) {
            bookSimpleList.add(bookRepository.findBookSimpleByIdConvert(bookId));
        }
        return bookSimpleList;
    }

    public List<Book> get10BookHighestComment() {
        return bookRepository.findTop10ByCommentCount();
    }

    public Book getBook(String bookName) {
        String bookNameRemove = bookName.replace("-", " ");
        return bookRepository.findByNameEquals(bookNameRemove);
    }

    public String addNewBook(Book book, List<Type> typeList, Account account) {
        if (book.getId() != 0)
            return "failed";
        book.setAccount(account);
        book.setTypeList(typeList);
        book.setPostDate(LocalDate.now());
        book.setImage("/images/default/book_default.jpg");
        book.setStatus(Status.comingOut);
        bookRepository.save(book);
        return "success";
    }

    public void updateBook(Book book, List<Type> typeList, Account account, Book oldBook) {
        book.setId(oldBook.getId());
        book.setImage(oldBook.getImage());
        book.setPostDate(LocalDate.now());
        book.setAccount(account);
        book.setStatus(Status.comingOut);
        book.setTypeList(typeList);
        bookRepository.save(book);
    }

    public void deleteBook(Book book){
        bookRepository.deleteBookTypeByBookId(book.getId());
        bookRepository.deleteBookReadByBookId(book.getId());
        bookRepository.deleteChapterByBookId(book.getId());
        bookRepository.deleteRateByBookId(book.getId());
        bookRepository.deleteBookLikeByBookId(book.getId());
        bookRepository.deleteCommentByBookId(book.getId());
        bookRepository.deleteBookStatisticByBookId(book.getId());
        bookRepository.deleteChapterPurchaseByBookId(book.getId());
        bookRepository.deleteBook(book.getId());
    }

    public List<Book> getListBookByName(String keyword){
        return bookRepository.findByNameLike("%"+keyword+"%");
    }

    public List<Book> getListBookByType(int typeId){
        return bookRepository.findBookByType(typeId);
    }

    public List<Book> getListBookBySortByNewCreated(){
        return bookRepository.findByOrderByPostDateDesc();
    }

    public List<Book> getListBookByTypeAndSortByNewCreated(int typeId){
        return bookRepository.findByTypeAndSortByNewCreate(typeId);
    }
}
