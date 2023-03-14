package com.mrthinkj.kythucac.service.mapper;

import com.mrthinkj.kythucac.modelDTO.book.BookReadDTO;
import com.mrthinkj.kythucac.modelDTO.book.BookSimple;
import com.mrthinkj.kythucac.service.book.ChapterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookReadDTOMapper {
    @Autowired
    ChapterService chapterService;

    public static BookReadDTO map(Object[] row) {
        BookReadDTO bookReadDTO = new BookReadDTO((Integer) row[0], (String) row[1], (String) row[2], (Integer) row[3], ((BigInteger) row[4]).intValue());
        return bookReadDTO;
    }
    public static List<BookReadDTO> map(List<Object[]> rows) {
        return rows.stream().map(BookReadDTOMapper::map).collect(Collectors.toList());
    }
}
