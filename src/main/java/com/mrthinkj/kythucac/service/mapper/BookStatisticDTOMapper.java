package com.mrthinkj.kythucac.service.mapper;

import com.mrthinkj.kythucac.modelDTO.book.BookStatisticDTO;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookStatisticDTOMapper {
    public static BookStatisticDTO map(Object[] row) {
        BookStatisticDTO bookStatistic = new BookStatisticDTO((String) row[0], (String) row[1], (String) row[2], (String) row[3], (String) row[4], (Integer) row[5],(Date) row[6],((BigInteger) row[7]).intValue(), (Integer) row[8]);
        return bookStatistic;
    }
    public static List<BookStatisticDTO> map(List<Object[]> rows) {
        return rows.stream().map(BookStatisticDTOMapper::map).collect(Collectors.toList());
    }

    public static BookStatisticDTO mapOne(Object[] rows) {
        Object[] row = (Object[]) rows[0];
        BookStatisticDTO bookStatistic = new BookStatisticDTO((String) row[0], (String) row[1], (String) row[2], (String) row[3], (String) row[4], (Integer) row[5],(Date) row[6] ,((BigInteger) row[7]).intValue(), (Integer) row[8]);
        return bookStatistic;
    }
}
