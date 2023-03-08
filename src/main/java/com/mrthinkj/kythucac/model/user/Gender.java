package com.mrthinkj.kythucac.model.user;

public enum Gender {
    male("Nam"),
    female("Nữ"),
    none("Không");
    private String gender;
    private Gender(String gender){
        this.gender = gender;
    }
}
