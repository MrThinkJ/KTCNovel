package com.mrthinkj.kythucac.service.convert;

import com.mrthinkj.kythucac.model.book.Book;
import com.mrthinkj.kythucac.repository.book.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.Entity;
import java.util.List;
@Component
public class Convert {
    @Autowired
    BookRepository bookRepository;

    public Book findBookByName(String bookName){
        return bookRepository.findByNameEquals(bookName.replace("-", " "));
    }

    public String getBookName(String bookName){
        StringBuilder outputBuilder = new StringBuilder();
        boolean capitalize = true;

        for (char c : bookName.toCharArray()) {
            if (capitalize && Character.isLetter(c)) {
                outputBuilder.append(Character.toUpperCase(c));
                capitalize = false;
            } else if (c == '-') {
                outputBuilder.append(' ');
                capitalize = true;
            } else {
                outputBuilder.append(c);
            }
        }
        String outputString = outputBuilder.toString();
        return outputString;
    }

    public List<String> splitByLine(String content){
        List<String> stringList = List.of(content.split("\n"));
        return stringList;
    }
}
