package com.mrthinkj.kythucac.modelDTO.book;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.text.Normalizer;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookReadDTO {
    private int bookId;
    private String bookName;
    private String bookImage;
    private int chapterMark;
    private int totalChapter;

    public String convertAll(){
        String outputString = Normalizer.normalize(bookName, Normalizer.Form.NFD)
                .replaceAll("[^\\p{ASCII}\\w]+", "-")
                .replaceAll("\\W", "-")
                .toLowerCase();
        return outputString;
    }
}
