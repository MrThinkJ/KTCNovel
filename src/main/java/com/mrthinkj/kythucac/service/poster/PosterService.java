package com.mrthinkj.kythucac.service.poster;

import com.mrthinkj.kythucac.model.user.Account;
import com.mrthinkj.kythucac.modelDTO.book.BookSimple;
import com.mrthinkj.kythucac.repository.book.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PosterService {
    @Autowired
    BookRepository bookRepository;

    public List<BookSimple> getListBookByPoster(Account account){
        return bookRepository.findAllBookByAccountIdConvert(account.getId());
    }

}
