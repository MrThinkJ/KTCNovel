package com.mrthinkj.kythucac.service.user;

import com.mrthinkj.kythucac.model.user.Account;
import com.mrthinkj.kythucac.model.user.User;
import com.mrthinkj.kythucac.repository.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public User getUserByAccount(int id){
        return userRepository.findById(id);
    }
}
