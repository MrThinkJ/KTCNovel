package com.mrthinkj.kythucac.service.book;

import com.mrthinkj.kythucac.model.book.Chapter;
import com.mrthinkj.kythucac.repository.book.BookRepository;
import com.mrthinkj.kythucac.repository.book.ChapterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChapterService {
    @Autowired
    ChapterRepository chapterRepository;
    @Autowired
    BookRepository bookRepository;

    public Chapter getChapterByBookIdAndChapterIndex(String bookName, String chapterIndex){
        int bookId = bookRepository.findByNameEquals(bookName.replace("-", " ")).getId();
        int index = Integer.parseInt(chapterIndex.substring(7));
        return chapterRepository.findByBookIdAndIndex(bookId, index);
    }

    public List<Chapter> getChapterListByBookName(String bookName){
        String bookNameRemove = bookName.replace("-", " ");
        int bookId = bookRepository.findByNameEquals(bookNameRemove).getId();
        return chapterRepository.findByBookId(bookId);
    }
}
