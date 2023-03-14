package com.mrthinkj.kythucac.model.book;

import com.mrthinkj.kythucac.model.user.Account;
import com.mrthinkj.kythucac.model.user.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "chapter_purchase")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChapterPurchase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "purchase_date")
    private LocalDate purchaseDate;
    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;
    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;
    @ManyToOne
    @JoinColumn(name = "chapter_id")
    private Chapter chapter;
}
