package com.mrthinkj.kythucac.controller.user;

import com.mrthinkj.kythucac.model.user.User;
import com.mrthinkj.kythucac.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(method = RequestMethod.GET, path = "/nguoi-dung")
public class UserController {
    @Autowired
    UserService userService;
    @GetMapping("/{id}")
    public User getUser(@PathVariable Integer id){
        User user = userService.getUserByAccount(id);
        return user;
    }
}
