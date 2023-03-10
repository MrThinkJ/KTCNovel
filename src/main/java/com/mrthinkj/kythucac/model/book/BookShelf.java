package com.mrthinkj.kythucac.model.book;

import com.mrthinkj.kythucac.model.user.Account;

import javax.persistence.*;

@Entity
@Table(name = "bookshelf")
public class BookShelf {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;

    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;

    public BookShelf(){}

    public BookShelf(Account account, Book book) {
        this.account = account;
        this.book = book;
    }

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
}
