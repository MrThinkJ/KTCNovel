package com.mrthinkj.kythucac.repository.book;

import com.mrthinkj.kythucac.model.book.Book;
import com.mrthinkj.kythucac.model.book.Rate;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RateRepository extends CrudRepository<Rate, Integer> {
    Rate findById(int rateId);
//    @Query(value = "select * from rate where book_id = ?1", nativeQuery = true)
    List<Rate> findByBook(Book book);

    @Query(value = "SELECT AVG(rate_star) as avg_rate FROM rate WHERE book_id = ?1", nativeQuery = true)
    Integer getAverageRateByBookId(int bookId);
}
