package com.mrthinkj.kythucac.repository.book;

import com.mrthinkj.kythucac.model.book.Book;
import com.mrthinkj.kythucac.model.book.BookStatistic;
import com.mrthinkj.kythucac.modelDTO.book.BookStatisticDTO;
import com.mrthinkj.kythucac.service.mapper.BookStatisticDTOMapper;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookStatisticRepository extends CrudRepository<BookStatistic, Integer> {
    BookStatistic findByBook(Book book);
    @Query(value = "select book_name, book_image, book_author, book_description, type_name, book_view,book_post_date, (SELECT COUNT(*) AS num_likes FROM book_like WHERE book_id = book.id), total_revenue\n" +
            "from book join book_type bt on book.id = bt.book_id\n" +
            "    join type t on bt.type_id = t.id\n" +
            "join book_statistic bs on book.id = bs.book_id\n" +
            "where account_id = ?1\n" +
            "group by book_name", nativeQuery = true)
    List<Object[]> findListBookStatisticByAccountId(int accountId);
    default List<BookStatisticDTO> findListBookStatisticByAccountIdConvert(int accountId){
        return BookStatisticDTOMapper.map(findListBookStatisticByAccountId(accountId));
    }

}
