package com.mrthinkj.kythucac.repository.book;

import com.mrthinkj.kythucac.model.book.Book;
import com.mrthinkj.kythucac.model.book.BookShelf;
import com.mrthinkj.kythucac.model.user.Account;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;

@Repository
public interface BookshelfRepository extends PagingAndSortingRepository<BookShelf, Integer> {
    BookShelf findByBookAndAccount(Book book, Account account);

    @Query(value = "SELECT book_id FROM bookshelf group by book_id order by COUNT(*) desc limit 3", nativeQuery = true)
    List<Integer> get3BookIdHighestBookShelf();

    @Query(value = "SELECT bs.book FROM BookShelf bs WHERE bs.account.id = :accountId")
    Page<Book> findBookByAccount(int accountId, Pageable pageable);

    @Query(value = "select count(*) from book_read where book_id = ?1", nativeQuery = true)
    Integer countNumberOfBookShelf(int bookId);
}
