package com.mrthinkj.kythucac.repository.book;

import com.mrthinkj.kythucac.model.book.BookShelf;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;

@Repository
public interface BookshelfRepository extends CrudRepository<BookShelf, Integer> {
    @Query(value = "select b.book_name, book_image from bookshelf as bs join book b on bs.book_id = b.id where b.account_id = ?1", nativeQuery = true)
    List<Map<String, String>> findBookShelfByAccountId(int accountId);
    @Query(value = "select id from bookshelf where account_id = ?1 and book_id = ?2", nativeQuery = true)
    Integer findBookshelfIdMatchAccountAndBook(int accountId, int bookId);

    @Modifying
    @Query(value = "insert into bookshelf(account_id, book_id) value (?1, ?2)", nativeQuery = true)
    @Transactional(rollbackOn = Exception.class)
    void addBookToBookShelf(int accountId, int bookId);

    @Modifying
    @Query(value = "delete from bookshelf where account_id=?1 and book_id = ?2", nativeQuery = true)
    @Transactional(rollbackOn = Exception.class)
    void deleteBookFromBookShelf(int accountId, int bookId);
}
