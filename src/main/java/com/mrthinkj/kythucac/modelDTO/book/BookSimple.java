package com.mrthinkj.kythucac.modelDTO.book;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.UnsupportedEncodingException;
import java.text.Normalizer;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookSimple {
    private String bookName;
    private String bookImage;
    private String bookAuthor;
    private String bookDescription;
    private String typeName;
    private Integer bookView;

    public String convert() throws UnsupportedEncodingException {
        String output = bookName.toLowerCase()
                .replaceAll("\\s+", "-");
        return output;
    }

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
