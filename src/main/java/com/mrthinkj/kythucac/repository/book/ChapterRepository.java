package com.mrthinkj.kythucac.repository.book;

import com.mrthinkj.kythucac.model.book.Book;
import com.mrthinkj.kythucac.model.book.Chapter;
import com.mrthinkj.kythucac.modelDTO.ChapterSimple;
import org.hibernate.transform.Transformers;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
public interface ChapterRepository extends CrudRepository<Chapter, Integer> {
    @Query(value = "select * from chapter where book_id = ?1 and index_in_book = ?2", nativeQuery = true)
    Chapter findByBookIdAndIndex(int bookId, int index);
    @Query(value = "select index_in_book, chapter_name, vip_status from chapter where book_id = ?1", nativeQuery = true)
    List<Object[]> findListChapterSimpleByBookId(Integer bookId);

    default List<ChapterSimple> findListChapterSimpleByBookIdConvert(Integer bookId) {
        List<Object[]> result = findListChapterSimpleByBookId(bookId);
        return mapResultSetToChapterSimpleList(result);
    }
    private List<ChapterSimple> mapResultSetToChapterSimpleList(List<Object[]> result) {
        List<ChapterSimple> chapterSimpleList = new ArrayList<>();
        for (Object[] row : result) {
            ChapterSimple chapterSimple = new ChapterSimple();
            chapterSimple.setIndexInBook((Integer) row[0]);
            chapterSimple.setChapterName((String) row[1]);
            chapterSimple.setVipStatus((Integer) row[2]);
            chapterSimpleList.add(chapterSimple);
        }
        return chapterSimpleList;
    }


}
