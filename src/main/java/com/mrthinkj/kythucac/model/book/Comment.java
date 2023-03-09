package com.mrthinkj.kythucac.model.book;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mrthinkj.kythucac.model.user.Account;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "comment")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "content")
    private String content;
    @Column(name = "comment_date")
    private LocalDate commentDate;
    @ManyToOne
    @JoinColumn(name = "book_id")
    @JsonIgnore
    private Book book;
    @ManyToOne
    @JoinColumn(name = "account_id")
    @JsonIgnore
    private Account account;

    public LocalDate getCommentDate() {
        return commentDate;
    }

    public void setCommentDate(LocalDate commentDate) {
        this.commentDate = commentDate;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }
}
