package com.test.classroom.domain;

public class HandActionRequest {
    private String name;
    private String token;
    private Boolean handRaised;

    public String getName() {
        return name;
    }

    public String getToken() {
        return token;
    }

    public Boolean isHandRaised() {
        return handRaised;
    }
}
