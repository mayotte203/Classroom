package com.test.classroom.domain;

public class LoginInfo {
    private final String name;
    private final boolean isSuccessful;

    public LoginInfo(String name, boolean isSuccessful)
    {
        this.name = name;
        this.isSuccessful = isSuccessful;
    }

    public String getName() {
        return name;
    }

    public boolean getSuccessful() {
        return isSuccessful;
    }
}

