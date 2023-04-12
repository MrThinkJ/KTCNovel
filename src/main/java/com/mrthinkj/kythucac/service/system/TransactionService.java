package com.mrthinkj.kythucac.service.system;

import com.mrthinkj.kythucac.model.book.Book;
import com.mrthinkj.kythucac.model.system.Transaction;
import com.mrthinkj.kythucac.model.system.TransactionStatus;
import com.mrthinkj.kythucac.model.user.Account;
import com.mrthinkj.kythucac.repository.system.TransactionRepository;
import com.mrthinkj.kythucac.service.admin.AdminService;
import com.mrthinkj.kythucac.service.book.BookStatisticService;
import com.mrthinkj.kythucac.service.convert.Convert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class TransactionService {
    @Autowired
    TransactionRepository transactionRepository;
    @Autowired
    Convert convert;
    @Autowired
    BookStatisticService bookStatisticService;
    @Autowired
    AdminService adminService;

    public String addNewTransaction(Transaction transaction, Account account, String bookName){
        Book book = convert.findBookByName(bookName);
        if (transaction.getAmount() < 1000)
            return "not enough quantity";
        if (transaction.getBank().equals("0"))
            return "required bank";
        if (transaction.getAmount() > bookStatisticService.getRevenue(book))
            return "not enough balance";
        transaction.setAccount(account);
        transaction.setBook(book);
        transaction.setCashOutDate(LocalDate.now());
        transaction.setStatus(TransactionStatus.notStarted);
        transactionRepository.save(transaction);
        return "success";
    }

    public List<Transaction> getListTransactionByAccount(Account account){
        return transactionRepository.findByAccount(account);
    }

    public List<Transaction> getListUnprocessedTransaction(){
        return transactionRepository.findByStatus(TransactionStatus.notStarted);
    }

    public void confirmTransaction(int transactionId){
        Transaction transaction = transactionRepository.findById(transactionId);
//        adminService.confirmTransaction(transaction.getAccount());
        transaction.setStatus(TransactionStatus.confirm);
        transactionRepository.save(transaction);
    }

    public void cancelTransaction(int transactionId){
        Transaction transaction = transactionRepository.findById(transactionId);
//        adminService.cancelTransaction(transaction.getAccount());
        transaction.setStatus(TransactionStatus.cancel);
        transactionRepository.save(transaction);
    }
}
