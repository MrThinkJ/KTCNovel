package com.mrthinkj.kythucac.modelDTO.book;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChapterPurchaseDTO {
    private String chapterName;
    private Date purchaseDate;
    private Integer chapterPrice;
    private String username;
    private Integer userId;
}
