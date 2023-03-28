package com.mrthinkj.kythucac.modelDTO.book;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.UnsupportedEncodingException;

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
}
