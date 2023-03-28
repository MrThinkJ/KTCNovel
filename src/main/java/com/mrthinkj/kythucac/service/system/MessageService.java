package com.mrthinkj.kythucac.service.system;

import com.mrthinkj.kythucac.model.system.Message;
import com.mrthinkj.kythucac.model.user.Account;
import com.mrthinkj.kythucac.repository.system.MessageRepository;
import com.mrthinkj.kythucac.service.user.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class MessageService {
    @Autowired
    MessageRepository messageRepository;
    @Autowired
    AccountService accountService;

    public List<Message> getNewestMessageListByAccount(Account account){
        return messageRepository.findNewestMessageListByAccount(account.getId());
    }

    public List<Message> getMessageListBetweenTwoAccount(Account mainAccount, Account account){
        return messageRepository.findByFromAccountAndToAccountOrToAccountAndFromAccountOrderBySendDateAsc(mainAccount, account, mainAccount, account);
    }

    public String sendMessage(Account fromAccount, Integer toAccountId, Message message){
        Account toAccount = accountService.getAccountById(toAccountId);
        message.setFromAccount(fromAccount);
        message.setToAccount(toAccount);
        message.setSendDate(LocalDateTime.now());
        try{
            messageRepository.save(message);
        } catch (Exception e){
            return "Tin nhắn quá dài !";
        }
        return "success";
    }
}
