package com.mrthinkj.kythucac.repository.book;

import com.mrthinkj.kythucac.model.book.Book;
import com.mrthinkj.kythucac.model.book.Chapter;
import com.mrthinkj.kythucac.modelDTO.book.BookSimple;
import com.mrthinkj.kythucac.modelDTO.book.ChapterSimple;
import com.mrthinkj.kythucac.service.mapper.BookSimpleMapper;
import com.mrthinkj.kythucac.service.mapper.ChapterSimpleMapper;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface BookRepository extends CrudRepository<Book, Integer> {
    @Query(value = "select book_name, book_image, book_author, book_description, type_name, book_view from book join book_type bt on book.id = bt.book_id join type t on bt.type_id = t.id group by book_name", nativeQuery = true)
    List<Object[]> findAllBook();
    default List<BookSimple> findAllBookConvert() {
        List<Object[]> result = findAllBook();
        return BookSimpleMapper.map(result);
    }

    @Query(value = "select book_name, book_image, book_author, book_description, type_name, book_view from book join book_type bt on book.id = bt.book_id join type t on bt.type_id = t.id where account_id = ?1 group by book_name", nativeQuery = true)
    List<Object[]> findAllBookByAccountId(int accountId);
    default List<BookSimple> findAllBookByAccountIdConvert(int accountId) {
        List<Object[]> result = findAllBookByAccountId(accountId);
        return BookSimpleMapper.map(result);
    }

    @Query(value = "select book_name, book_image, book_author, book_description, type_name, book_view from book join book_type bt on book.id = bt.book_id join type t on bt.type_id = t.id group by book_name order by book_view desc limit 3", nativeQuery = true)
    List<Object[]> find3BookHighestView();
    default List<BookSimple> find3BookHighestViewConvert() {
        List<Object[]> result = find3BookHighestView();
        return BookSimpleMapper.map(result);
    }

    @Query(value = "select book_name, book_image, book_author, book_description, type_name, book_view from book join book_type bt on book.id = bt.book_id join type t on bt.type_id = t.id where book.id = ?1 group by book_name", nativeQuery = true)
    Object[] findBookSimpleById(int bookId);
    default BookSimple findBookSimpleByIdConvert(int bookId) {
        Object[] result = findBookSimpleById(bookId);
        return BookSimpleMapper.mapOne(result);
    }

    // Su dung trong trang home
    @Query(value = "select book_name, book_image, book_author, book_description, type_name, book_view, book_post_date from book join book_type bt on book.id = bt.book_id join type t on bt.type_id = t.id order by book_post_date desc limit 7", nativeQuery = true)
    List<Object[]> findBookRecent();
    default List<BookSimple> findBookRecentConvert() {
        List<Object[]> result = findBookRecent();
        return BookSimpleMapper.map(result);
    }

    @Query(value = "select * from book as b join comment as cm on b.id = cm.book_id group by cm.id order by count(cm.id) desc", nativeQuery = true)
    List<Book> findTop10ByCommentCount();

    List<Book> findTop10ByOrderByViewDesc();

    Book findById(int id);

    Book findByNameEquals(String bookName);

    List<Book> findFirst10ByOrderByPostDateDesc();

    @Query(value = "select distinct b.* from book as b join chapter c on b.id = c.book_id order by c.chapter_post_date desc limit 10", nativeQuery = true)
    List<Book> findFirst10ByChapter();

//    @Query(value = "select * from book where book_name like '%?1%'", nativeQuery = true)
    List<Book> findByNameLike(String keyword);

    @Query(value = "select b.* from book_type as bt join book b on b.id = bt.book_id where bt.type_id = ?1", nativeQuery = true)
    List<Book> findBookByType(int typeId);

    @Query(value = "select b.* from book_type join book b on b.id = book_type.book_id where type_id = ?1 order by b.book_post_date desc", nativeQuery = true)
    List<Book> findByTypeAndSortByNewCreate(int typeId);

    List<Book> findByOrderByPostDateDesc();

    @Modifying
    @Transactional(rollbackOn = Exception.class)
    @Query(value = "delete from book_type where book_id = ?1", nativeQuery = true)
    void deleteBookTypeByBookId(Integer bookId);

    @Modifying
    @Transactional(rollbackOn = Exception.class)
    @Query(value = "delete from book where id = ?1", nativeQuery = true)
    void deleteBook(Integer bookId);
}
