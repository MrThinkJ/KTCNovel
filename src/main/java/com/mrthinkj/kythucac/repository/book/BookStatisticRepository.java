package com.mrthinkj.kythucac.repository.book;

import com.mrthinkj.kythucac.model.book.Book;
import com.mrthinkj.kythucac.model.book.BookStatistic;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookStatisticRepository extends CrudRepository<BookStatistic, Integer> {
    BookStatistic findByBook(Book book);
}
