package com.mrthinkj.kythucac.service.book;

import com.mrthinkj.kythucac.model.book.Book;
import com.mrthinkj.kythucac.model.book.BookRead;
import com.mrthinkj.kythucac.model.user.Account;
import com.mrthinkj.kythucac.modelDTO.book.BookReadDTO;
import com.mrthinkj.kythucac.modelDTO.book.BookSimple;
import com.mrthinkj.kythucac.repository.book.BookReadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.view.RedirectView;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class BookReadService {
    @Autowired
    BookReadRepository bookReadRepository;

    public List<BookReadDTO> getAllBookRead(Account account){
        int accountId = account.getId();
        return bookReadRepository.findAllBookReadConvert(accountId);
    }

    public void addToBookRead(Account account, String chapterIndex, Book book) {
        int index = Integer.parseInt(chapterIndex.substring(7));
        BookRead bookRead = bookReadRepository.findByBookAndAccount(book, account);
        if (bookRead == null) {
            bookRead = new BookRead();
            bookRead.setBook(book);
            bookRead.setAccount(account);
        }
        bookRead.setReadDate(LocalDate.now());
        bookRead.setChapterMark(index);
        bookReadRepository.save(bookRead);
    }

    public void removeFromBookRead(Account account, int bookId){
        bookReadRepository.removeBookReadByBookIdAndAccountId(bookId, account.getId());
    }

    public Integer getChapterMarkOfBookReadByAccount(Account account, Book book) {
        return bookReadRepository.findChapterMarkOfBookReadByAccountId(book.getId(), account.getId());
    }

    public List<BookSimple> get3BookReadRecentByAccount(int accountId){
        return bookReadRepository.find3BookReadRecentByAccountIdConvert(accountId);
    }

    public List<List<Object>> getBookReadByAccount(Account account, int size, int page){
        Page<Book> bookPage = getBookReadPageByAccount(account, size, page);
        List<Book> bookList = bookPage.getContent();
        List<Integer> chapterMarkList = bookReadRepository.findChapterMarkByAccountId(account.getId());
        List<List<Object>> bookRead = new ArrayList<>();
        for (int i =0;i<bookList.size();i++){
            List<Object> bookReadObject = new ArrayList<>();
            bookReadObject.add(bookList.get(i));
            bookReadObject.add(chapterMarkList.get(i));
            bookRead.add(bookReadObject);
        }
        return bookRead;
    }

    public Page<Book> getBookReadPageByAccount(Account account, int size, int page){
        Pageable pageable = PageRequest.of(page, size);
        Page<Book> bookPage = bookReadRepository.findBookReadByAccount(account.getId(), pageable);
        return bookPage;
    }
}
