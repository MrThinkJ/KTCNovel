package com.mrthinkj.kythucac.service.book;

import com.mrthinkj.kythucac.model.book.Book;
import com.mrthinkj.kythucac.model.book.Comment;
import com.mrthinkj.kythucac.model.book.Like;
import com.mrthinkj.kythucac.model.book.Rate;
import com.mrthinkj.kythucac.model.user.Account;
import com.mrthinkj.kythucac.repository.book.BookRepository;
import com.mrthinkj.kythucac.repository.book.CommentRepository;
import com.mrthinkj.kythucac.repository.book.LikeRepository;
import com.mrthinkj.kythucac.repository.book.RateRepository;
import com.mrthinkj.kythucac.service.convert.Convert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class BookEvaluateService {
    @Autowired
    BookRepository bookRepository;
    @Autowired
    RateRepository rateRepository;
    @Autowired
    CommentRepository commentRepository;
    @Autowired
    LikeRepository likeRepository;
    @Autowired
    Convert convert;

    public void toggleLikeToBook(String bookName, Account account){
        Book book = convert.findBookByName(bookName);
        int accountId = account.getId();
//        List<Integer> bookIds = likeRepository.findBookLikeByAccountId(accountId);
//        for (Integer bookId : bookIds){
//            if (bookId == book.getId()){
//                likeRepository.deleteByBookIdAndAccountId(bookId, accountId);
//                return;
//            }
//        }
//        likeRepository.saveByBookIdAndAccountId(book.getId(), accountId);
        Like bookLike = likeRepository.findByBookAndAccount(book, account);
        if (bookLike == null){
            Like like = new Like();
            like.setBook(book);
            like.setAccount(account);
            likeRepository.save(like);
            return;
        }
        likeRepository.delete(bookLike);
    }

    public void addViewToBook(String bookName){
        Book book = convert.findBookByName(bookName);
        book.setView(book.getView()+1);
        bookRepository.save(book);
    }

    public void addRateToBook(String bookName, Rate rate){
        LocalDate date = LocalDate.now();
        rate.setRateDate(date);
        Book book = convert.findBookByName(bookName);
        rate.setBook(book);
        rateRepository.save(rate);
    }

    public List<Rate> getRateOfBook(String bookName){
        Book book = convert.findBookByName(bookName);
        return rateRepository.findByBook(book);
    }

    public void deleteRateFromBook(String bookName, int rateId, int accountId){
        Book book = convert.findBookByName(bookName);
        Rate rate = rateRepository.findById(rateId);
        if (rate.getAccount().getId() == accountId){
            rateRepository.delete(rate);
        }
    }

    public void addCommentToBook(String bookName, Comment comment){
        LocalDate date = LocalDate.now();
        comment.setCommentDate(date);
        Book book = convert.findBookByName(bookName);
        comment.setBook(book);
        commentRepository.save(comment);
    }

    public void deleteCommentFromBook(String bookName, int commentId, int accountId){
        Book book = convert.findBookByName(bookName);
        Comment comment = commentRepository.findById(commentId);
        if (comment.getAccount().getId() == accountId){
            commentRepository.delete(comment);
        }
    }

    public Integer getNumberOfLikeByBookName(String bookName){
        Book book = convert.findBookByName(bookName);
        return likeRepository.getNumberOfLikeByBookId(book.getId());
    }
}
