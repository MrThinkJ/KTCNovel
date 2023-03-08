package com.mrthinkj.kythucac.model.book;

public enum Status {
    comingOut("Đang ra"),
    finish("Hoàn tất"),
    pause("Tạm dừng"),
    cancel("Ngừng");
    private String status;
    private Status(String status){
        this.status = status;
    }
}
