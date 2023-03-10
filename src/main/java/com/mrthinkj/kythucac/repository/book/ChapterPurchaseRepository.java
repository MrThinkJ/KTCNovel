package com.mrthinkj.kythucac.repository.book;

import com.mrthinkj.kythucac.model.book.ChapterPurchase;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.time.LocalDate;

@Repository
public interface ChapterPurchaseRepository extends CrudRepository<ChapterPurchase, Integer> {
    @Query(value = "select account_id from chapter_purchase where account_id = ?1 and chapter_id=?2", nativeQuery = true)
    Integer findAccountIdByAccountIdAndChapterId(int accountId, int chapterId);

    @Modifying
    @Transactional(rollbackOn = Exception.class)
    @Query(value = "insert into chapter_purchase(account_id, chapter_id, book_id, purchase_date) value(?1, ?2, ?3, ?4)", nativeQuery = true)
    void addChapterPurchase(int accountId, int chapterId, int bookId, LocalDate purchaseDate);
}
