package com.mrthinkj.kythucac.model.book;

import com.mrthinkj.kythucac.model.user.Account;
import com.mrthinkj.kythucac.model.user.User;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "book_read")
public class BookRead {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;
    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;
    @Column(name = "chapter_mark")
    private int chapterMark;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public int getChapterMark() {
        return chapterMark;
    }

    public void setChapterMark(int chapterMark) {
        this.chapterMark = chapterMark;
    }
}
