package com.mrthinkj.kythucac.repository.book;

import com.mrthinkj.kythucac.model.book.Book;
import com.mrthinkj.kythucac.model.book.BookRead;
import com.mrthinkj.kythucac.model.user.Account;
import com.mrthinkj.kythucac.modelDTO.book.BookReadDTO;
import com.mrthinkj.kythucac.modelDTO.book.BookSimple;
import com.mrthinkj.kythucac.service.mapper.BookReadDTOMapper;
import com.mrthinkj.kythucac.service.mapper.BookSimpleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface BookReadRepository extends PagingAndSortingRepository<BookRead, Integer> {
    BookRead findByBookAndAccount(Book book, Account account);

    @Query(value = "select chapter_mark from book_read where book_id = ?1 and account_id = ?2", nativeQuery = true)
    Integer findChapterMarkOfBookReadByAccountId(int bookId, int accountId);

    @Modifying
    @Transactional(rollbackOn = Exception.class)
    @Query(value = "delete from book_read where book_id = ?1 and account_id = ?2", nativeQuery = true)
    void removeBookReadByBookIdAndAccountId(int bookId, int accountId);

    @Query(value = "select b.id, b.book_name, b.book_image, br.chapter_mark, CAST(count(*) AS UNSIGNED)" +
            " from book_read br join book b on br.book_id = b.id join chapter c on b.id = c.book_id" +
            " where br.account_id = ?1 GROUP BY b.id, b.book_name, b.book_image, br.chapter_mark;", nativeQuery = true)
    List<Object[]> findAllBookRead(int accountId);

    default List<BookReadDTO> findAllBookReadConvert(int accountId) {
        List<Object[]> result = findAllBookRead(accountId);
        return BookReadDTOMapper.map(result);
    }

    @Query(value = "SELECT b.book_name, b.book_image, b.book_author, b.book_description,\n" +
            "       (select type_name from type\n" +
            "           join book_type bt on type.id = bt.type_id\n" +
            "           join book b2 on b2.id = bt.book_id\n" +
            "                         join book_read r on b2.id = r.book_id where r.book_id = b.id limit 1), b.book_view\n" +
            "FROM book b\n" +
            "         JOIN book_read br ON b.id = br.book_id\n" +
            "WHERE br.account_id = ?1", nativeQuery = true)
    List<Object[]> find3BookReadRecentByAccountId(int accountId);

    default List<BookSimple> find3BookReadRecentByAccountIdConvert(int accountId) {
        return BookSimpleMapper.map(find3BookReadRecentByAccountId(accountId));
    }

    @Query(value = "SELECT br.book FROM BookRead br WHERE br.account.id = :accountId")
    Page<Book> findBookReadByAccount(int accountId, Pageable pageable);

    @Query(value = "select chapter_mark from book_read where account_id = ?1", nativeQuery = true)
    List<Integer> findChapterMarkByAccountId(int accountId);
}
