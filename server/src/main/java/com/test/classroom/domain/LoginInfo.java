package com.test.classroom.domain;

public class LoginInfo {
    private final String name;
    private final String token;
    private final boolean isSuccessful;

    public LoginInfo(String name, String token, boolean isSuccessful)
    {
        this.name = name;
        this.token = token;
        this.isSuccessful = isSuccessful;
    }

    public String getName() {
        return name;
    }

    public String getToken() {
        return token;
    }

    public boolean getSuccessful() {
        return isSuccessful;
    }
}

