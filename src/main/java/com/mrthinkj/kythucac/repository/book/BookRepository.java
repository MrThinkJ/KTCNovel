package com.mrthinkj.kythucac.repository.book;

import com.mrthinkj.kythucac.model.book.Book;
import com.mrthinkj.kythucac.model.book.Chapter;
import com.mrthinkj.kythucac.modelDTO.book.BookSimple;
import com.mrthinkj.kythucac.modelDTO.book.ChapterSimple;
import com.mrthinkj.kythucac.service.mapper.BookSimpleMapper;
import com.mrthinkj.kythucac.service.mapper.ChapterSimpleMapper;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends CrudRepository<Book, Integer> {
    @Query(value = "select book_name, book_image, book_author, book_description, type_name from book join book_type bt on book.id = bt.book_id join type t on bt.type_id = t.id group by book_name", nativeQuery = true)
    List<Object[]> findAllBook();
    default List<BookSimple> findAllBookConvert() {
        List<Object[]> result = findAllBook();
        return BookSimpleMapper.map(result);
    }
    List<Chapter> findChapterListById(int id);
    List<Book> findTop10ByOrderByViewDesc();
    @Query(value = "select * from book as b join comment as cm on b.id = cm.book_id group by cm.id order by count(cm.id) desc", nativeQuery = true)
    List<Book> findTop10ByCommentCount();
    Book findById(int id);
    Book findByNameEquals(String bookName);
}
