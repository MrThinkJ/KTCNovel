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

    public Account getAccountByUsernameAndPassword(String username, String password){
        return accountRepository.findByUsernameAndPassword(username, password);
    }

    @Transactional(rollbackOn = Exception.class)
    public String pay(Account account,int amount) throws Exception{
        int currentAmount = account.getCurrency();
        if (currentAmount < amount)
            return "failed";
        account.setCurrency(currentAmount - amount);
        accountRepository.save(account);
        return "success";
    }

    @Transactional(rollbackOn = Exception.class)
    public String recharge(Account account,int amount) throws Exception{
        int currentAmount = account.getCurrency();
        account.setCurrency(currentAmount + amount);
        accountRepository.save(account);
        return "success";
    }
}
