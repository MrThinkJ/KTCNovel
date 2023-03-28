package com.mrthinkj.kythucac.repository.book;

import com.mrthinkj.kythucac.model.book.Book;
import com.mrthinkj.kythucac.model.book.Chapter;
import com.mrthinkj.kythucac.model.book.ChapterPurchase;
import com.mrthinkj.kythucac.model.user.Account;
import com.mrthinkj.kythucac.modelDTO.book.ChapterPurchaseDTO;
import com.mrthinkj.kythucac.service.mapper.ChapterPurchaseMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Repository
public interface ChapterPurchaseRepository extends PagingAndSortingRepository<ChapterPurchase, Integer> {
    @Query(value = "select account_id from chapter_purchase where account_id = ?1 and chapter_id=?2", nativeQuery = true)
    Integer findAccountIdByAccountIdAndChapterId(int accountId, int chapterId);

    @Query(value = "select sum(chapter_price), purchase_date from chapter_purchase cp join chapter c on c.id = cp.chapter_id where c.book_id = ?1 group by purchase_date;", nativeQuery = true)
    List<List<Object>> findRevenueLastTenDaysByBook(int bookId);

    @Query(value = "select chapter_name, purchase_date, chapter_price, user_name, u.id from chapter_purchase as cp join chapter c on c.id = cp.chapter_id join account a on a.id = cp.account_id join user u on u.id = a.id where c.book_id = ?1 order by purchase_date desc limit 10;", nativeQuery = true)
    List<Object[]> findLast10ChapterPurchase(int bookId);

    default List<ChapterPurchaseDTO> findLast10ChapterPurchaseConvert(int bookId){
        List<Object[]> result = findLast10ChapterPurchase(bookId);
        return ChapterPurchaseMapper.map(result);
    }

    @Query(value = "select chapter_name, purchase_date, chapter_price, user_name, u.id from chapter_purchase as cp join chapter c on c.id = cp.chapter_id join account a on a.id = cp.account_id join user u on u.id = a.id where c.book_id = ?1 order by purchase_date desc;", nativeQuery = true)
    List<Object[]> findAllChapterPurchase(int bookId);

    default List<ChapterPurchaseDTO> findAllChapterPurchaseConvert(int bookId){
        List<Object[]> result = findAllChapterPurchase(bookId);
        return ChapterPurchaseMapper.map(result);
    }

    Page<ChapterPurchase> findByAccount(Account account, Pageable pageable);
}
