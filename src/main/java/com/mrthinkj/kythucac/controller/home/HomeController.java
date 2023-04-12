package com.mrthinkj.kythucac.controller.home;

import com.mrthinkj.kythucac.model.user.Account;
import com.mrthinkj.kythucac.model.user.User;
import com.mrthinkj.kythucac.modelDTO.book.BookReadDTO;
import com.mrthinkj.kythucac.modelDTO.book.BookSimple;
import com.mrthinkj.kythucac.modelDTO.user.UserDTO;
import com.mrthinkj.kythucac.service.book.BookReadService;
import com.mrthinkj.kythucac.service.book.BookService;
import com.mrthinkj.kythucac.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
public class HomeController {
    @Autowired
    UserService userService;
    @Autowired
    BookService bookService;
    @Autowired
    BookReadService bookReadService;

    @GetMapping("/session-user")
    public @ResponseBody User getUserSession(@ModelAttribute("userAccount") Account account){
        if (account == null)
            return null;
        User user = userService.getUserByAccount(account.getId());
        return user;
    }

    @GetMapping()
    public String showHomePage(@ModelAttribute("userAccount") Account account,
                               Model model){
        if (account != null){
            List<BookReadDTO> bookReadList = bookReadService.getAllBookRead(account);
            if (bookReadList.size() != 0){
                List<BookReadDTO> bookReadDTOList = new ArrayList<>();
                int size = bookReadList.size() >= 3 ? 3 : bookReadList.size();
                for (int i=0;i<size;i++)
                    bookReadDTOList.add(bookReadList.get(i));
                model.addAttribute("bookReadList", bookReadDTOList);
            }
        }
        model.addAttribute("highestLikeBooks", bookService.get3BookHighestLike());
        model.addAttribute("highestViewBooks", bookService.get3BookHighestView());
        model.addAttribute("highestBookshelfBooks", bookService.get3BookHighestBookShelf());
        model.addAttribute("bookRecent", bookService.getBookRecentHome());
        model.addAttribute("bookRecentFoot", bookService.getBookRecent());
        return "page/home";
    }

    @ModelAttribute("userAccount")
    public Account getAccount(HttpSession session){
        return (Account) session.getAttribute("userAccount");
    }
}
