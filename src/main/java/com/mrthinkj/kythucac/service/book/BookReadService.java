package com.mrthinkj.kythucac.service.book;

import com.mrthinkj.kythucac.model.book.Book;
import com.mrthinkj.kythucac.model.book.BookRead;
import com.mrthinkj.kythucac.model.user.Account;
import com.mrthinkj.kythucac.modelDTO.book.BookReadDTO;
import com.mrthinkj.kythucac.repository.book.BookReadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.view.RedirectView;

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
        bookRead.setChapterMark(index);
        bookReadRepository.save(bookRead);
    }

    public void removeFromBookRead(Account account, int bookId){
        bookReadRepository.removeBookReadByBookIdAndAccountId(bookId, account.getId());
    }

    public int getChapterMarkOfBookReadByAccount(Account account, Book book) {
        return bookReadRepository.findChapterMarkOfBookReadByAccountId(book.getId(), account.getId());
    }
}
