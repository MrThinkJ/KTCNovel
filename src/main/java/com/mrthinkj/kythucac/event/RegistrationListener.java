package com.mrthinkj.kythucac.event;

import com.mrthinkj.kythucac.model.user.Account;
import com.mrthinkj.kythucac.service.user.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.MessageSource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class RegistrationListener implements ApplicationListener<OnRegistrationCompleteEvent> {
    @Autowired
    private AccountService service;

    @Autowired
    private JavaMailSender mailSender;

    @Override
    public void onApplicationEvent(OnRegistrationCompleteEvent onRegistrationCompleteEvent) {
        this.confirmRegistration(onRegistrationCompleteEvent);
    }

    private void confirmRegistration(OnRegistrationCompleteEvent event){
        Account account = event.getAccount();
        String token = UUID.randomUUID().toString();
        service.createVerificationToken(account, token);

        String targetEmail = account.getEmail();
        String confirmUrl = event.getUrl()+"/confirmRegistration?token="+token;

        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setTo(targetEmail);
        mail.setSubject("Registration Confirmation");
        mail.setText("Xin chào "+account.getUsername()+"\n\nBạn đã đăng ký thành công tài khoản trên Kỳ Thư Các, để kích hoạt tài khoản, nạp 15k và bấm vào link dưới đây: "+"\n\n"+confirmUrl);
        mailSender.send(mail);
    }
}
