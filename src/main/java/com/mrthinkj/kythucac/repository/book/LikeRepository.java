package com.mrthinkj.kythucac.repository.book;

import com.mrthinkj.kythucac.model.book.Like;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;

@Repository
public interface LikeRepository extends CrudRepository<Like, Integer> {
    @Query(value = "select book_id from book_like where account_id = ?1", nativeQuery = true)

    List<Integer> findBookLikeByAccountId(int accountId);

    @Modifying
    @Query(value = "delete from book_like where book_id = ?1 and account_id = ?2", nativeQuery = true)
    @Transactional(rollbackOn = Exception.class)
    void deleteByBookIdAndAccountId(int bookId, int accountId);

    @Modifying
    @Query(value = "insert into book_like(book_id, account_id) value(?1, ?2)", nativeQuery = true)
    @Transactional(rollbackOn = Exception.class)
    void saveByBookIdAndAccountId(int bookId, int accountId);

    @Query(value = "SELECT COUNT(*) AS num_likes FROM book_like WHERE book_id = ?1", nativeQuery = true)
    Integer getNumberOfLikeByBookId(int bookId);

    @Query(value = "SELECT book_id FROM book_like group by book_id order by COUNT(*) desc limit 10", nativeQuery = true)
    List<Integer> get10BookIdHighestLike();
}
