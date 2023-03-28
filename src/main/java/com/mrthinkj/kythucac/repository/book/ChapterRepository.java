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

    @Query(value = "select * from chapter where book_id = ?1 order by id desc limit 1", nativeQuery = true)
    Chapter findLastChapterByBookId(int bookId);

    @Query(value = "select index_in_book, chapter_name, vip_status, chapter_post_date from chapter where book_id = ?1", nativeQuery = true)
    List<Object[]> findListChapterSimpleByBookId(Integer bookId);

    @Query(value = "select count(*) from chapter where book_id = ?1", nativeQuery = true)
    Integer countNumberOfChapterByBookId(int bookId);

    default List<ChapterSimple> findListChapterSimpleByBookIdConvert(Integer bookId) {
        List<Object[]> result = findListChapterSimpleByBookId(bookId);
        return ChapterSimpleMapper.map(result);
    }
    @Query(value = "select index_in_book, chapter_name, vip_status, chapter_post_date from chapter where book_id = ?1 order by index_in_book desc limit 1", nativeQuery = true)
    Object[] findLastChapterSimpleByBookId(Integer bookId);
    default ChapterSimple findLastChapterSimpleByBookIdConvert(int bookId){
        return ChapterSimpleMapper.mapOne(findLastChapterSimpleByBookId(bookId));
    }

    @Query(value = "select id from chapter where index_in_book = ?1 and book_id = ?2", nativeQuery = true)
    Integer findIdByIndexAndBook(int index, int bookId);

    Chapter findById(int chapterId);
}
