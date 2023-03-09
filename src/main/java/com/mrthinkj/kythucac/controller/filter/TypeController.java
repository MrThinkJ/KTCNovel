package com.mrthinkj.kythucac.controller.filter;

import com.mrthinkj.kythucac.service.filter.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(method = RequestMethod.GET, path = "/truyen")
public class TypeController {
    @Autowired
    TypeService typeService;
    @GetMapping("/type")
    public List<String> getTypes(){
        return typeService.getTypes();
    }
}
