package com.mrthinkj.kythucac.model.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mrthinkj.kythucac.model.book.Book;
import com.mrthinkj.kythucac.model.book.BookRead;
import com.mrthinkj.kythucac.model.book.Chapter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "account")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "username")
    private String username;
    @Column(name = "password")
    private String password;
    @Column(name = "email")
    private String email;
    @Column(name = "currency")
    private int currency;
    @OneToOne(cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private User user;
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(
                    name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(
                    name = "role_id", referencedColumnName = "id"))
    private List<Role> roleList;
//    @ManyToMany(mappedBy = "accountList")
//    private List<Chapter> chapterList;
//    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL, orphanRemoval = true)
//    private List<BookRead> bookReadList;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Book> bookList; // Can khac phuc

    public int getCurrency() {
        return currency;
    }

    public void setCurrency(int currency) {
        this.currency = currency;
    }

    public List<Book> getBookList() {
        return bookList;
    }

    public void setBookList(List<Book> bookList) {
        this.bookList = bookList;
    }
//
//    public List<Chapter> getChapterList() {
//        return chapterList;
//    }
//
//    public void setChapterList(List<Chapter> chapterList) {
//        this.chapterList = chapterList;
//    }    public List<BookRead> getBookReadList() {
//        return bookReadList;
//    }
//
//    public void setBookReadList(List<BookRead> bookReadList) {
//        this.bookReadList = bookReadList;
//    }
//
//    public List<Book> getBookList() {
//        return bookList;
//    }
//
//    public void setBookList(List<Book> bookList) {
//        this.bookList = bookList;
//    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Role> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<Role> roleList) {
        this.roleList = roleList;
    }
}
