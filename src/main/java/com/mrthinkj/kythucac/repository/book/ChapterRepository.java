package com.mrthinkj.kythucac.repository.book;

import com.mrthinkj.kythucac.service.mapper.ChapterSimpleMapper;
import com.mrthinkj.kythucac.model.book.Chapter;
import com.mrthinkj.kythucac.modelDTO.book.ChapterSimple;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChapterRepository extends CrudRepository<Chapter, Integer> {
    @Query(value = "select * from chapter where book_id = ?1 and index_in_book = ?2", nativeQuery = true)
    Chapter findByBookIdAndIndex(int bookId, int index);

    @Query(value = "select index_in_book, chapter_name, vip_status, chapter_post_date from chapter where book_id = ?1", nativeQuery = true)
    List<Object[]> findListChapterSimpleByBookId(Integer bookId);

    @Query(value = "", nativeQuery = true)


    default List<ChapterSimple> findListChapterSimpleByBookIdConvert(Integer bookId) {
        List<Object[]> result = findListChapterSimpleByBookId(bookId);
        return ChapterSimpleMapper.map(result);
    }
}
