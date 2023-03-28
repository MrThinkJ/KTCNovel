package com.mrthinkj.kythucac.model.book;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mrthinkj.kythucac.model.user.Account;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "chapter")
public class Chapter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "index_in_book")
    private int index;
    @Column(name = "chapter_name")
    private String name;
    @Column(name = "chapter_content", columnDefinition = "MEDIUMTEXT")
    private String content;
    @Column(name = "chapter_post_date")
    private LocalDate postDate;
    @Column(name = "chapter_price")
    private int price;
    @Column(name = "vip_status")
    private boolean vipStatus;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id")
    private Book book;

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public boolean isVipStatus() {
        return vipStatus;
    }

    public void setVipStatus(boolean vipStatus) {
        this.vipStatus = vipStatus;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDate getPostDate() {
        return postDate;
    }

    public void setPostDate(LocalDate postDate) {
        this.postDate = postDate;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    @Override
    public String toString() {
        return "Chapter{" +
                "id=" + id +
                ", index=" + index +
                ", name='" + name + '\'' +
                ", content='" + content + '\'' +
                ", postDate=" + postDate +
                ", price=" + price +
                ", vipStatus=" + vipStatus +
                '}';
    }
}
