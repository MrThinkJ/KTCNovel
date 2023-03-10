package com.mrthinkj.kythucac.modelDTO.book;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookSimple {
    private String bookName;
    private String bookImage;
    private String bookAuthor;
    private String bookDescription;
    private String typeName;
}
