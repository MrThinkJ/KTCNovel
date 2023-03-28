package com.mrthinkj.kythucac.service.mapper;

import com.mrthinkj.kythucac.modelDTO.book.BookSimple;
import com.mrthinkj.kythucac.modelDTO.book.ChapterSimple;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
@Service
public class ChapterSimpleMapper{
    public static ChapterSimple map(Object[] row) {
        ChapterSimple chapterSimple = new ChapterSimple();
        chapterSimple.setIndexInBook((Integer) row[0]);
        chapterSimple.setChapterName((String) row[1]);
        chapterSimple.setVipStatus((Integer) row[2]);
        chapterSimple.setPostDate((Date) row[3]);
        return chapterSimple;
    }

    public static List<ChapterSimple> map(List<Object[]> rows) {
        return rows.stream().map(ChapterSimpleMapper::map).collect(Collectors.toList());
    }

    public static ChapterSimple mapOne(Object[] rows) {
        Object[] row = (Object[]) rows[0];
        ChapterSimple chapterSimple = new ChapterSimple();
        chapterSimple.setIndexInBook((Integer) row[0]);
        chapterSimple.setChapterName((String) row[1]);
        chapterSimple.setVipStatus((Integer) row[2]);
        chapterSimple.setPostDate((Date) row[3]);
        return chapterSimple;
    }
}
