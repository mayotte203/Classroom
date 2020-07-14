package com.test.classroom.api.actions;

public class HandActionRequest {
    private String name;
    private String token;
    private Boolean handRaisen;

    public String getName() {
        return name;
    }

    public String getToken() {
        return token;
    }

    public Boolean isHandRaisen() {
        return handRaisen;
    }
}
