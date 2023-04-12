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
        String output = Normalizer.normalize(bookName, Normalizer.Form.NFD)
                .replaceAll("[đĐ]", "[d]")
                .replaceAll("[^\\p{ASCII}]+", "") // Remove non-ASCII characters
                .replace("[", "").replace("]", "")
                .replaceAll("\\W+", "-") // Replace consecutive non-word characters with a single hyphen
                .toLowerCase(); // Convert to lowercase
        return output;
    }
}
