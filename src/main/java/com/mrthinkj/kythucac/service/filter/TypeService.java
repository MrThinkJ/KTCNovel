package com.mrthinkj.kythucac.service.filter;

import com.mrthinkj.kythucac.model.book.Type;
import com.mrthinkj.kythucac.repository.book.TypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TypeService {
    @Autowired
    TypeRepository typeRepository;

    public List<String> getTypes(){
        return typeRepository.findNameOfType();
    }
}
