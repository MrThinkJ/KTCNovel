package com.mrthinkj.kythucac.model.system;

import com.mrthinkj.kythucac.model.book.Book;
import com.mrthinkj.kythucac.model.user.Account;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@Table(name = "transaction")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "bank_id")
    @NotNull(message = "Không được để trống")
    private long bankId;
    @Column(name = "bank")
    private String bank;
    @Column(name = "amount")
    @NotNull(message = "Không được để trống")
    private int amount;
    @Column(name = "cash_out_date")
    private LocalDate cashOutDate;
    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;

    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;

    @Column(name = "status")
    private TransactionStatus status;

    public TransactionStatus getStatus() {
        return status;
    }

    public void setStatus(TransactionStatus status) {
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getBankId() {
        return bankId;
    }

    public void setBankId(long bankId) {
        this.bankId = bankId;
    }

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public LocalDate getCashOutDate() {
        return cashOutDate;
    }

    public void setCashOutDate(LocalDate cashOutDate) {
        this.cashOutDate = cashOutDate;
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

    @Override
    public String toString() {
        return "Transaction{" +
                "id=" + id +
                ", bankId=" + bankId +
                ", bank='" + bank + '\'' +
                ", amount=" + amount +
                ", cashOutDate=" + cashOutDate +
                '}';
    }
}
