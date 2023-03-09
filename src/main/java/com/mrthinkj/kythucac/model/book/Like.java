package com.mrthinkj.kythucac.model.book;

import com.mrthinkj.kythucac.model.user.Account;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "book_like")
public class Like {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "account_id")
    Account account;

    @ManyToOne
    @JoinColumn(name = "book_id")
    Book book;

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
