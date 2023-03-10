package com.mrthinkj.kythucac.service.book;

import com.mrthinkj.kythucac.model.book.Chapter;
import com.mrthinkj.kythucac.model.book.ChapterPurchase;
import com.mrthinkj.kythucac.model.user.Account;
import com.mrthinkj.kythucac.modelDTO.book.ChapterSimple;
import com.mrthinkj.kythucac.repository.book.BookRepository;
import com.mrthinkj.kythucac.repository.book.ChapterPurchaseRepository;
import com.mrthinkj.kythucac.repository.book.ChapterRepository;
import com.mrthinkj.kythucac.repository.user.AccountRepository;
import com.mrthinkj.kythucac.service.mapper.ChapterSimpleMapper;
import com.mrthinkj.kythucac.service.user.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ChapterService {
    @Autowired
    ChapterRepository chapterRepository;
    @Autowired
    BookRepository bookRepository;
    @Autowired
    ChapterPurchaseRepository chapterPurchaseRepository;
    @Autowired
    AccountService accountService;
    @Autowired
    AccountRepository accountRepository;

    public Chapter getChapterByBookIdAndChapterIndex(String bookName, String chapterIndex, int accountId){
        int bookId = bookRepository.findByNameEquals(bookName.replace("-", " ")).getId();
        int index = Integer.parseInt(chapterIndex.substring(7));
        Chapter chapter = chapterRepository.findByBookIdAndIndex(bookId, index);
        if (!chapter.isVipStatus())
            return chapter;
        if (chapterPurchaseRepository.findAccountIdByAccountIdAndChapterId(accountId, chapter.getId()) == null)
            return null;
        return chapter;
    }

    public List<ChapterSimple> getChapterSimpleListByBookName(String bookName){
        String bookNameRemove = bookName.replace("-", " ");
        int bookId = bookRepository.findByNameEquals(bookNameRemove).getId();
        return chapterRepository.findListChapterSimpleByBookIdConvert(bookId);
    }

    public String buyChapter(String bookName, String chapterIndex, int accountId) throws Exception {
        int bookId = bookRepository.findByNameEquals(bookName.replace("-", " ")).getId();
        int index = Integer.parseInt(chapterIndex.substring(7));
        Chapter chapter = chapterRepository.findByBookIdAndIndex(bookId, index);
        if (!chapter.isVipStatus())
            return null;
        if (chapterPurchaseRepository.findAccountIdByAccountIdAndChapterId(accountId, chapter.getId()) != null)
                return null;
        String status = accountService.pay(accountId, chapter.getPrice());
        if (status.equals("failed"))
            return "failed";
        chapterPurchaseRepository.addChapterPurchase(accountId, chapter.getId(), bookId, LocalDate.now());
        return "success";
    }
}
