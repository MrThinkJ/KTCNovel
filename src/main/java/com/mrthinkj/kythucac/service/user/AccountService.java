package com.mrthinkj.kythucac.service.user;

import com.mrthinkj.kythucac.model.user.Account;
import com.mrthinkj.kythucac.repository.user.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class AccountService {
    @Autowired
    AccountRepository accountRepository;

    @Transactional(rollbackOn = Exception.class)
    public String pay(int accountId,int amount) throws Exception{
        Account account = accountRepository.findById(accountId);
        int currentAmount = account.getCurrency();
        if (currentAmount < amount)
            return "failed";
        account.setCurrency(currentAmount - amount);
        accountRepository.save(account);
        return "success";
    }
}
