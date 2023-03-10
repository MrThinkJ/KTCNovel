package com.mrthinkj.kythucac.service.mapper;

import com.mrthinkj.kythucac.modelDTO.book.BookSimple;
import com.mrthinkj.kythucac.modelDTO.book.ChapterSimple;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookSimpleMapper {
    public static BookSimple map(Object[] row) {
        BookSimple bookSimple = new BookSimple((String) row[0], (String) row[1], (String) row[2], (String) row[3], (String) row[4]);
        return bookSimple;
    }
    public static List<BookSimple> map(List<Object[]> rows) {
        return rows.stream().map(BookSimpleMapper::map).collect(Collectors.toList());
    }
}
