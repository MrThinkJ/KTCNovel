package com.mrthinkj.kythucac.modelDTO.book;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookReadDTO {
    private int bookId;
    private String bookName;
    private String bookImage;
    private int chapterMark;
    private int totalChapter;
}
