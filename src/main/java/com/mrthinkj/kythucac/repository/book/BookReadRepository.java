package com.mrthinkj.kythucac.repository.book;

import com.mrthinkj.kythucac.model.book.Book;
import com.mrthinkj.kythucac.model.book.BookRead;
import com.mrthinkj.kythucac.model.user.Account;
import com.mrthinkj.kythucac.modelDTO.book.BookReadDTO;
import com.mrthinkj.kythucac.service.mapper.BookReadDTOMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface BookReadRepository extends CrudRepository<BookRead, Integer> {
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
    default List<BookReadDTO> findAllBookReadConvert(int accountId){
        List<Object[]> result = findAllBookRead(accountId);
        return BookReadDTOMapper.map(result);
    }
}
