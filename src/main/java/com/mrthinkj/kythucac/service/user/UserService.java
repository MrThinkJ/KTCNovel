package com.mrthinkj.kythucac.service.user;

import com.mrthinkj.kythucac.model.user.Account;
import com.mrthinkj.kythucac.model.user.User;
import com.mrthinkj.kythucac.modelDTO.user.UserDTO;
import com.mrthinkj.kythucac.repository.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public User getUserByAccount(int id){
        return userRepository.findById(id);
    }

    public UserDTO getUserById(int id){
        UserDTO user = userRepository.findUserByIdConvert(id);
        if (user == null)
            return null;
        return user;
    }

    public String updateAvatar(Account account, String imagePath){
        User user = userRepository.findByAccount(account);
        user.setAvatar(imagePath);
        userRepository.save(user);
        return "success";
    }

    public String updateUser(Account account, User user){
        user.setId(account.getId());
        user.setAccount(account);
        user.setAvatar(account.getUser().getAvatar());
        userRepository.save(user);
        return "success";
    }

    public List<User> getAllUser(){
        return userRepository.findAll();
    }
}
