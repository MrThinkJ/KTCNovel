package com.mrthinkj.kythucac.service.book;

import com.mrthinkj.kythucac.model.book.Book;
import com.mrthinkj.kythucac.model.book.BookStatistic;
import com.mrthinkj.kythucac.model.book.ChapterPurchase;
import com.mrthinkj.kythucac.model.user.Account;
import com.mrthinkj.kythucac.modelDTO.book.BookStatisticDTO;
import com.mrthinkj.kythucac.modelDTO.book.ChapterPurchaseDTO;
import com.mrthinkj.kythucac.repository.book.BookStatisticRepository;
import com.mrthinkj.kythucac.repository.book.ChapterPurchaseRepository;
import com.mrthinkj.kythucac.service.convert.Convert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Service
public class BookStatisticService {
    @Autowired
    BookStatisticRepository bookStatisticRepository;
    @Autowired
    ChapterPurchaseRepository chapterPurchaseRepository;
    @Autowired
    Convert convert;

    public List<BookStatisticDTO> getBookStatisticList(Account account){
        return bookStatisticRepository.findListBookStatisticByAccountIdConvert(account.getId());
    }

    public List<List<Object>> getRevenueLastTenDays(String bookName){
        return chapterPurchaseRepository.findRevenueLastTenDaysByBook(convert.findBookByName(bookName).getId());
    }

    public List<ChapterPurchaseDTO> getLast10ChapterPurchase(String bookName){
        Book book = convert.findBookByName(bookName);
        return chapterPurchaseRepository.findLast10ChapterPurchaseConvert(book.getId());
    }

    public void addRevenue(Book book, int amount){
        BookStatistic bookStatistic = bookStatisticRepository.findByBook(book);
        if (bookStatistic == null){
            bookStatistic = new BookStatistic();
            bookStatistic.setBook(book);
            bookStatistic.setTotalRevenue(0);
        }
        bookStatistic.setTotalRevenue(bookStatistic.getTotalRevenue()+amount);
        bookStatisticRepository.save(bookStatistic);
    }

    public String cashOut(Book book, int amount){
        BookStatistic bookStatistic = bookStatisticRepository.findByBook(book);
        int totalRevenue = bookStatistic.getTotalRevenue();
        bookStatistic.setTotalRevenue(totalRevenue-amount);
        bookStatisticRepository.save(bookStatistic);
        return "succeed";
    }

    public Integer getRevenue(Book book){
        BookStatistic bookStatistic = bookStatisticRepository.findByBook(book);
        return bookStatistic.getTotalRevenue();
    }
}
