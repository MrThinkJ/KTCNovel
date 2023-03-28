package com.mrthinkj.kythucac.service.book;

import com.mrthinkj.kythucac.model.book.Book;
import com.mrthinkj.kythucac.model.book.Chapter;
import com.mrthinkj.kythucac.model.book.ChapterPurchase;
import com.mrthinkj.kythucac.model.user.Account;
import com.mrthinkj.kythucac.modelDTO.book.ChapterSimple;
import com.mrthinkj.kythucac.repository.book.BookRepository;
import com.mrthinkj.kythucac.repository.book.ChapterPurchaseRepository;
import com.mrthinkj.kythucac.repository.book.ChapterRepository;
import com.mrthinkj.kythucac.repository.user.AccountRepository;
import com.mrthinkj.kythucac.service.convert.Convert;
import com.mrthinkj.kythucac.service.mapper.ChapterSimpleMapper;
import com.mrthinkj.kythucac.service.user.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
    @Autowired
    BookStatisticService bookStatisticService;
    @Autowired
    Convert convert;

    public Integer getTotalChapterByBook(Book book){
        return chapterRepository.countNumberOfChapterByBookId(book.getId());
    }

    public Chapter getChapterByBookIdAndChapterIndexUpdate(String bookName, String chapterIndex, Account account){
        Book book = convert.findBookByName(bookName);
        int index = Integer.parseInt(chapterIndex.substring(7));
        Chapter chapter = chapterRepository.findByBookIdAndIndex(book.getId(), index);
        return chapter;
    }

    public Chapter getChapterByBookIdAndChapterIndex(Book book, String chapterIndex){
        int index = Integer.parseInt(chapterIndex.substring(7));
        return chapterRepository.findByBookIdAndIndex(book.getId(), index);
    }

    public String getFirstLineOfChapter(Book book, String chapterIndex){
        Chapter chapter = getChapterByBookIdAndChapterIndex(book, chapterIndex);
        String content = chapter.getContent().split("\n")[0];
        String first100 = content.substring(0, Math.min(content.length(), 100));
        return first100;
    }

    public Chapter getChapterByBookIdAndChapterIndex(Book book, String chapterIndex, Account account){
        Chapter chapter = getChapterByBookIdAndChapterIndex(book, chapterIndex);
        if (!chapter.isVipStatus())
            return chapter;
        if (isBoughtChapter(chapter, account))
            return chapter;
        chapter.setContent(null);
        return chapter;
    }

    public Boolean isBoughtChapter(Chapter chapter, Account account){
        if (account == null) // if chapter is VIP and not user
            return false;
        int accountId = account.getId();
        if (chapterPurchaseRepository.findAccountIdByAccountIdAndChapterId(accountId, chapter.getId()) == null) // if chapter is VIP and haven't bought yet
            return false;
        return true;
    }

    public Boolean isLastChapter(Book book, String chapterIndex){
        int index = Integer.parseInt(chapterIndex.substring(7));
        Chapter chapter = chapterRepository.findLastChapterByBookId(book.getId());
        if (index == chapter.getIndex())
            return true;
        return false;
    }

    public List<ChapterSimple> getChapterSimpleListByBookName(String bookName){
        String bookNameRemove = bookName.replace("-", " ");
        int bookId = bookRepository.findByNameEquals(bookNameRemove).getId();
        return chapterRepository.findListChapterSimpleByBookIdConvert(bookId);
    }

    public String buyChapter(String bookName, String chapterIndex, Account account) throws Exception {
        Book book = convert.findBookByName(bookName);
        int bookId = book.getId();
        int index = Integer.parseInt(chapterIndex.substring(7));
        Chapter chapter = chapterRepository.findByBookIdAndIndex(bookId, index);
        if (!chapter.isVipStatus())
            return null;
        if (chapterPurchaseRepository.findAccountIdByAccountIdAndChapterId(account.getId(), chapter.getId()) != null)
            return null;
        String status = accountService.pay(account, chapter.getPrice());
        if (status.equals("failed"))
            return "failed";
        ChapterPurchase chapterPurchase = new ChapterPurchase();
        chapterPurchase.setChapter(chapter);
        chapterPurchase.setBook(book);
        chapterPurchase.setAccount(account);
        chapterPurchase.setPurchaseDate(LocalDate.now());
        chapterPurchaseRepository.save(chapterPurchase);
        bookStatisticService.addRevenue(book, chapter.getPrice());
        return "success";
    }

    public String addNewChapter(String bookName, Chapter chapter, String content){
        Book book = convert.findBookByName(bookName);
        Integer chapterId = chapterRepository.findIdByIndexAndBook(chapter.getIndex(), book.getId());
        if (chapterId != null)
            return "exist";
        if (!chapter.isVipStatus())
            chapter.setPrice(0);
        chapter.setId(0);
        chapter.setBook(book);
        chapter.setContent(content);
        chapter.setPostDate(LocalDate.now());
        chapterRepository.save(chapter);
        return "success";
    }

    public String updateChapter(String bookName, Chapter chapter, String content){
        Book book = convert.findBookByName(bookName);
        Integer chapterId = chapterRepository.findIdByIndexAndBook(chapter.getIndex(), book.getId());
        if (chapterId != null && chapterId != chapter.getId())
            return "exist";
        if (!chapter.isVipStatus())
            chapter.setPrice(0);
        chapter.setBook(book);
        chapter.setContent(content);
        chapter.setPostDate(LocalDate.now());
        chapterRepository.save(chapter);
        return "success";
    }

    public Page<ChapterPurchase> getListChapterPurchaseByAccount(Account account, int size, int page){
        Pageable pageable = PageRequest.of(page, size);
        return chapterPurchaseRepository.findByAccount(account, pageable);
    }

}
