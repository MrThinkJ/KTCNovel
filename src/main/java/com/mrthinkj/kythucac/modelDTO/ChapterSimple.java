package com.mrthinkj.kythucac.modelDTO;

public class ChapterSimple {
    private Integer indexInBook;
    private String chapterName;
    private Integer vipStatus;

    public ChapterSimple (){}

    public ChapterSimple(Integer indexInBook, String chapterName, Integer vipStatus) {
        this.indexInBook = indexInBook;
        this.chapterName = chapterName;
        this.vipStatus = vipStatus;
    }

    public Integer getIndexInBook() {
        return indexInBook;
    }

    public void setIndexInBook(Integer indexInBook) {
        this.indexInBook = indexInBook;
    }

    public String getChapterName() {
        return chapterName;
    }

    public void setChapterName(String chapterName) {
        this.chapterName = chapterName;
    }

    public Integer getVipStatus() {
        return vipStatus;
    }

    public void setVipStatus(Integer vipStatus) {
        this.vipStatus = vipStatus;
    }
}
