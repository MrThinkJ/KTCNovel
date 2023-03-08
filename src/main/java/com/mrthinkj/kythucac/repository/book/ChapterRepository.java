package com.mrthinkj.kythucac.repository.book;

import com.mrthinkj.kythucac.model.book.Chapter;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChapterRepository extends CrudRepository<Chapter, Integer> {
    @Query(value = "select * from chapter where book_id = ?1 and index_in_book = ?2", nativeQuery = true)
    Chapter findByBookIdAndIndex(int bookId, int index);
    @Query(value = "select * from chapter where book_id = ?1", nativeQuery = true)
    List<Chapter> findByBookId(int bookId);
}
