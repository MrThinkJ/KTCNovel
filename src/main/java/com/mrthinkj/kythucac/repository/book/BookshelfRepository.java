package com.mrthinkj.kythucac.repository.book;

import com.mrthinkj.kythucac.model.book.Book;
import com.mrthinkj.kythucac.model.book.BookShelf;
import com.mrthinkj.kythucac.model.user.Account;
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

    BookShelf findByBookAndAccount(Book book, Account account);
}
