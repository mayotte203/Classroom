package com.test.classroom;

public class LoginInfo {
    private final String name;
    private final String token;
    private final Boolean isSuccessful;

    LoginInfo(String name, String token, Boolean isSuccessful)
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

    public Boolean getSuccessful() {
        return isSuccessful;
    }
}

