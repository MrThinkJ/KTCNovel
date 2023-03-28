package com.mrthinkj.kythucac.controller.user;

import com.mrthinkj.kythucac.model.user.User;
import com.mrthinkj.kythucac.modelDTO.book.BookSimple;
import com.mrthinkj.kythucac.modelDTO.user.UserDTO;
import com.mrthinkj.kythucac.service.book.BookReadService;
import com.mrthinkj.kythucac.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(method = RequestMethod.GET, path = "/trang-ca-nhan")
public class UserController {
    @Autowired
    UserService userService;
    @Autowired
    BookReadService bookReadService;
    @GetMapping("/{id}")
    public String getUser(@PathVariable Integer id,
                          Model model){
        UserDTO user = userService.getUserById(id);
        List<BookSimple> bookSimpleList = bookReadService.get3BookReadRecentByAccount(id);
        if (user == null)
            return "404";
        model.addAttribute("user", user);
        model.addAttribute("bookList", bookSimpleList);
        return "user/profile";
    }
}
