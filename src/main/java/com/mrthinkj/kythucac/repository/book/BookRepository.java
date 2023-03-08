package com.mrthinkj.kythucac.repository.book;

import com.mrthinkj.kythucac.model.book.Book;
import com.mrthinkj.kythucac.model.book.Chapter;
import com.mrthinkj.kythucac.model.book.Type;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends CrudRepository<Book, Integer> {
    List<Book> findAll();
    List<Book> findAllByOrderByViewDesc();
    List<Chapter> findChapterListById(int id);
    List<Book> findTop10ByOrderByViewDesc();
    List<Book> findTop10ByOrderByLikeDesc();
    @Query(value = "select * from book as b join comment as cm on b.id = cm.book_id group by cm.id order by count(cm.id) desc", nativeQuery = true)
    List<Book> findTop10ByCommentCount();

    Book findById(int id);
    Book findByNameEquals(String bookName);
}
