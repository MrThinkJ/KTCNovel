package com.mrthinkj.kythucac.controller.filter;

import com.mrthinkj.kythucac.model.book.Book;
import com.mrthinkj.kythucac.service.book.BookService;
import com.mrthinkj.kythucac.service.filter.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(method = RequestMethod.GET, path = "/truyen")
public class TypeController {
    @Autowired
    TypeService typeService;
    @Autowired
    BookService bookService;

    @GetMapping("/type")
    public List<String> getTypes(){
        return typeService.getTypes();
    }

    @GetMapping("/")
    public String getListType(@RequestParam(value = "keyword", required = false) String keyword,
                              @RequestParam(value = "type" , required = false) Integer typeId,
                              @RequestParam(value = "sortBy", required = false) String sortMethod,
                              Model model){
        if (keyword != null){
            List<Book> bookList = bookService.getListBookByName(keyword);
            model.addAttribute("bookList", bookList);
            return "page/search";
        }
        if (typeId != null && sortMethod == null){
            model.addAttribute("bookList", bookService.getListBookByType(typeId));
            return "page/type";
        }
        if (typeId != null){
            model.addAttribute("bookList", bookService.getListBookByTypeAndSortByNewCreated(typeId));
            return "page/type";
        }
        if (sortMethod != null){
            model.addAttribute("bookList", bookService.getListBookBySortByNewCreated());
            return "page/type";
        }
        return "page/type";
    }
}
