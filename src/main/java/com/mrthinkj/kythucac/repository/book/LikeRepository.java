package com.mrthinkj.kythucac.repository.book;

import com.mrthinkj.kythucac.model.book.Book;
import com.mrthinkj.kythucac.model.book.Like;
import com.mrthinkj.kythucac.model.user.Account;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;

@Repository
public interface LikeRepository extends CrudRepository<Like, Integer> {
    Like findByBookAndAccount(Book book, Account account);

    @Query(value = "SELECT COUNT(*) AS num_likes FROM book_like WHERE book_id = ?1", nativeQuery = true)
    Integer getNumberOfLikeByBookId(int bookId);

    @Query(value = "SELECT book_id FROM book_like group by book_id order by COUNT(*) desc limit 10", nativeQuery = true)
    List<Integer> get10BookIdHighestLike();
}
