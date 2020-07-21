package com.test.classroom.domain;


public class StudentUpdate {

    private String name;
    private Boolean handRaised;
    private String token;

    public StudentUpdate(String name, Boolean handRaised, String token) {
        this.name = name;
        this.handRaised = handRaised;
        this.token = token;
    }

    public String getName() {
        return name;
    }

    public Boolean getHandRaised() {
        return handRaised;
    }

    public String getToken() {
        return token;
    }
}