package com.mrthinkj.kythucac.model.system;

public enum TransactionStatus {
    notStarted("Chưa xử lí"),
    cancel("Hủy"),
    confirm("Hoàn tất");
    private final String displayName;

    TransactionStatus(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

    public static String getStatusFromString(String string) {
        switch (string) {
            case "notStarted":
                return "Chưa xử lí";
            case "cancel":
                return "Hủy";
            default:
                return "Hoàn tất";
        }
    }
}
