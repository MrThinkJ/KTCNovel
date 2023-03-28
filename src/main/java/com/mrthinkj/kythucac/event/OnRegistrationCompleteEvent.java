package com.mrthinkj.kythucac.event;

import com.mrthinkj.kythucac.model.user.Account;
import org.springframework.context.ApplicationEvent;

public class OnRegistrationCompleteEvent extends ApplicationEvent {
    private String url;
    private Account account;

    public OnRegistrationCompleteEvent(Account account, String url) {
        super(account);
        this.account = account;
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
}
