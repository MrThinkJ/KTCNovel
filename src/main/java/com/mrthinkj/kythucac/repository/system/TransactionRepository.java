package com.mrthinkj.kythucac.repository.system;

import com.mrthinkj.kythucac.model.system.Transaction;
import com.mrthinkj.kythucac.model.system.TransactionStatus;
import com.mrthinkj.kythucac.model.user.Account;
import com.mrthinkj.kythucac.service.system.TransactionService;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends CrudRepository<Transaction, Integer> {
    List<Transaction> findByAccount(Account account);

    List<Transaction> findByStatus(TransactionStatus transactionStatus);

    Transaction findById(int id);
}
