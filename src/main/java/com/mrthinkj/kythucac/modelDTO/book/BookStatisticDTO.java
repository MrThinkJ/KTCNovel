package com.mrthinkj.kythucac.modelDTO.book;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.UnsupportedEncodingException;
import java.time.LocalDate;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookStatisticDTO {
    private String bookName;
    private String bookImage;
    private String bookAuthor;
    private String bookDescription;
    private String typeName;
    private Integer bookView;
    private Date bookPostDate;
    private Integer bookLike;
    private Integer totalRevenue;

    public String convert() throws UnsupportedEncodingException {
        String output = bookName.toLowerCase()
                .replaceAll("\\s+", "-");
        return output;
    }
}
