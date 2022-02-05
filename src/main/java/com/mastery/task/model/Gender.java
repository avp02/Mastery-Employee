package com.mastery.task.model;

import lombok.Getter;

@Getter
public enum Gender {

    MALE(1),
    FEMALE(2);

    private int id;

    Gender(int id) {
        this.id = id;
    }

    public static Gender getGenderById(int id) {
        return id == 1 ? MALE : FEMALE;
    }

}
