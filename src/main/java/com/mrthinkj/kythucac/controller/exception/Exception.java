package com.mrthinkj.kythucac.controller.exception;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Exception {
    @GetMapping("/404")
    public String show404Page(){
        return "exception/not-found";
    }

    @GetMapping("/403")
    public String show403Page(){
        return "exception/access-denied";
    }
}
