package com.mrthinkj.kythucac.controller.book;

import com.mrthinkj.kythucac.model.book.Comment;
import com.mrthinkj.kythucac.model.book.Rate;
import com.mrthinkj.kythucac.service.book.BookEvaluateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(method = RequestMethod.GET, path = "/truyen")
public class EvaluateBookController {
    @Autowired
    BookEvaluateService bookEvaluateService;
    @PostMapping("/{bookName}/like")
    public void addLikeToBook(@PathVariable String bookName){
        // Session accountId
        bookEvaluateService.toggleLikeToBook(bookName, 1);
    }

    @PostMapping("/{bookName}/view")
    public void addViewToBook(@PathVariable String bookName){
        bookEvaluateService.addViewToBook(bookName);
    }

    @GetMapping("/{bookName}/rate-form")
    public String showRateForm(@PathVariable String bookName,
                               Model model){
        model.addAttribute("rate", new Rate());
        return "add";
    }

    @GetMapping("/{bookName}/rate")
    public List<Rate> showRate(@PathVariable String bookName){
        return bookEvaluateService.getRateOfBook(bookName);
    }

    @PostMapping("/{bookName}/process-rate")
    public void addRateToBook(@PathVariable String bookName,
                              @ModelAttribute("rate") Rate rate){
        bookEvaluateService.addRateToBook(bookName, rate);
    }

    @GetMapping("/{bookName}/comment")
    public String showCommentForm(@PathVariable String bookName,
                               Model model){
        model.addAttribute("comment", new Comment());
        return "add-comment";
    }

    @PostMapping("/{bookName}/process-comment")
    public void addCommentToBook(@PathVariable String bookName,
                              @ModelAttribute("rate") Comment comment){
        bookEvaluateService.addCommentToBook(bookName, comment);
    }
}
