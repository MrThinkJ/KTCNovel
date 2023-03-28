package com.mrthinkj.kythucac.service.mapper;

import com.mrthinkj.kythucac.modelDTO.book.BookSimple;
import com.mrthinkj.kythucac.modelDTO.book.ChapterPurchaseDTO;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ChapterPurchaseMapper {
    public static ChapterPurchaseDTO map(Object[] row) {
        ChapterPurchaseDTO chapterPurchaseDTO = new ChapterPurchaseDTO((String) row[0], (Date) row[1], (Integer) row[2], (String) row[3], (Integer) row[4]);
        return chapterPurchaseDTO;
    }

    public static List<ChapterPurchaseDTO> map(List<Object[]> rows) {
        return rows.stream().map(ChapterPurchaseMapper::map).collect(Collectors.toList());
    }
}
