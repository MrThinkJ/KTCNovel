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

    public static String getStatusFromString(String string) {
        switch (string) {
            case "comingOut":
                return "Đang ra";
            case "finish":
                return "Hoàn tất";
            case "pause":
                return "Tạm dừng";
            default:
                return "Ngừng";
        }
    }
}
