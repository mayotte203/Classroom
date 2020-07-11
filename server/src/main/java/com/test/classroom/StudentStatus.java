package com.test.classroom;

public class StudentStatus {
    private final String name;
    private final Boolean handRisen;

    StudentStatus(String name, Boolean isHandRisen)
    {
        this.name = name;
        this.handRisen = isHandRisen;
    }

    public String getName() {
        return name;
    }

    public Boolean getHandRisen() {
        return handRisen;
    }
}
