package com.mrthinkj.kythucac.model.book;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "book_statistic")
public class BookStatistic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @OneToOne
    @JoinColumn(name = "book_id")
    Book book;
    @Column(name = "total_revenue")
    private int totalRevenue;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public int getTotalRevenue() {
        return totalRevenue;
    }

    public void setTotalRevenue(int totalRevenue) {
        this.totalRevenue = totalRevenue;
    }
}
