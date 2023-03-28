package com.mrthinkj.kythucac.model.user;

public enum Gender {
    male("Nam nhân"),
    female("Nữ nhân"),
    none("Bán thân bất toại");
    private String gender;

    private Gender(String gender) {
        this.gender = gender;
    }

    public static String getGenderFromInt(int value) {
        switch (value) {
            case 1:
                return Gender.male.gender;
            case 2:
                return Gender.female.gender;
            default:
                return Gender.none.gender;
        }
    }

    public static String getGenderFromString(String string) {
        switch (string) {
            case "male":
                return "Nam nhân";
            case "female":
                return "Nữ nhân";
            case "none":
                return "Bán thân bất toại";
            default:
                return "Unknown gender";
        }
    }
}
