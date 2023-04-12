package com.mrthinkj.kythucac.service.user;

import com.mrthinkj.kythucac.exception.UserAlreadyExistException;
import com.mrthinkj.kythucac.model.user.*;
import com.mrthinkj.kythucac.modelDTO.user.AccountRegisterDTO;
import com.mrthinkj.kythucac.repository.user.AccountRepository;
import com.mrthinkj.kythucac.repository.user.RoleRepository;
import com.mrthinkj.kythucac.repository.user.VerificationTokenRepository;
import com.mrthinkj.kythucac.security.CustomUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
@Transactional
public class AccountService implements UserDetailsService {
    @Autowired
    AccountRepository accountRepository;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    VerificationTokenRepository verificationTokenRepository;

    public Account registerNewAccount(AccountRegisterDTO accountRegisterDTO) throws UserAlreadyExistException{
        Random rd = new Random();
        if (isEmailExist(accountRegisterDTO.getEmail())){
            throw new UserAlreadyExistException("Đã tồn tại email "+accountRegisterDTO.getEmail()+" trong hệ thống");
        }
        if (isUsernameExist(accountRegisterDTO.getUsername())){
            throw new UserAlreadyExistException("Đã tồn tại tên người dùng này trong hệ thống");
        }
        Account account = new Account();
        account.setUsername(accountRegisterDTO.getUsername());
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        account.setPassword(encoder.encode(accountRegisterDTO.getPassword()));
        account.setEmail(accountRegisterDTO.getEmail());
        account.setEnabled(false);
        account.setCurrency(0);
        Role role = roleRepository.getRoleByName("ROLE_USER");
        List<Role> roles = new ArrayList<>();
        roles.add(role);
        account.setRoleList(roles);
        User user = new User();
        user.setAvatar("/images/default/user_avatar.jpg");
        user.setName("user"+(int) (rd.nextDouble()*1000));
        user.setGender(Gender.none);
        account.setUser(user);
        return accountRepository.save(account);
    }

    public boolean isEmailExist(String email){
        return accountRepository.findByEmail(email) != null;
    }

    public boolean isUsernameExist(String username){
        return accountRepository.findByUsername(username) != null;
    }

    public Account getAccount(String verificationToken){
        return verificationTokenRepository.findByToken(verificationToken).getAccount();
    }

    public void saveAccount(Account account){
        accountRepository.save(account);
    }

    public void createVerificationToken(Account account, String token){
        VerificationToken verificationToken = verificationTokenRepository.findByAccount(account);
        if (verificationToken == null){
            verificationToken.setAccount(account);
            verificationToken.setToken(token);
            verificationTokenRepository.save(verificationToken);
            return;
        }
        verificationToken.setToken(token);
        verificationToken.setExpiryDate(new VerificationToken().getExpiryDate());
        verificationTokenRepository.save(verificationToken);
    }

    public VerificationToken getVerificationToken(String verificationToken){
        return verificationTokenRepository.findByToken(verificationToken);
    }

    public Account getAccountByUsernameAndPassword(String username, String password) {
        return accountRepository.findByUsernameAndPassword(username, password);
    }

    public Account getAccountById(int accountId){
        return accountRepository.findById(accountId);
    }

    @Transactional(rollbackOn = Exception.class)
    public String pay(Account account, int amount) throws Exception {
        int currentAmount = account.getCurrency();
        if (currentAmount < amount)
            return "failed";
        account.setCurrency(currentAmount - amount);
        accountRepository.save(account);
        return "success";
    }

    @Transactional(rollbackOn = Exception.class)
    public String recharge(Account account, int amount) throws Exception {
        int currentAmount = account.getCurrency();
        account.setCurrency(currentAmount + amount);
        accountRepository.save(account);
        return "success";
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account account = accountRepository.findByUsername(username);
        if (account == null)
            throw new UsernameNotFoundException(username);
        return new CustomUserDetails(account);
    }

    public Account getByUsername(String username){
        Account account = accountRepository.findByUsername(username);
        return account;
    }
}
