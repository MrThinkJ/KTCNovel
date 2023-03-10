package com.mrthinkj.kythucac.modelDTO.book;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChapterSimple {
    private Integer indexInBook;
    private String chapterName;
    private Integer vipStatus;
    private Date postDate;
}
