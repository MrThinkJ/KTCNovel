package com.mrthinkj.kythucac.service.admin;

import com.mrthinkj.kythucac.model.user.Account;
import com.mrthinkj.kythucac.model.user.Role;
import com.mrthinkj.kythucac.repository.user.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class AdminService {
    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    RoleRepository roleRepository;

    public Boolean isAdmin(Account account){
        for (Role roleMatch : account.getRoleList()){
            if (roleMatch.getName().equals("ROLE_ADMIN")) return true;
        }
        return false;
    }

    public String confirmTransaction(Account account){
        String targetEmail = account.getEmail();
        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setTo(targetEmail);
        mail.setSubject("Confirm Email");
        mail.setText("Xin chào "+account.getUsername()+"\n\nYêu cầu rút tiền của bạn đã thành công, vui lòng kiểm tra số dư");
        mailSender.send(mail);
        return "success";
    }

    public String cancelTransaction(Account account){
        String targetEmail = account.getEmail();
        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setTo(targetEmail);
        mail.setSubject("Confirm Email");
        mail.setText("Xin chào "+account.getUsername()+"\n\nYêu cầu rút tiền của bạn đã bị hủy bỏ, vui lòng liên hệ admin để biết thêm thông tin chi tiết");
        mailSender.send(mail);
        return "success";
    }

    public String recharge(Account account, Integer amount){
        String targetEmail = account.getEmail();
        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setTo(targetEmail);
        mail.setSubject("Confirm Email");
        mail.setText("Xin chào "+account.getUsername()+"\n\nĐơn nạp tiền của bạn đã được thanh toán, vui lòng kiểm tra");
        mailSender.send(mail);
        return "success";
    }
}
