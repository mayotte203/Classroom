package com.test.classroom;

import java.util.Base64;
import java.util.Random;

public class Student {
    private final String name;
    private final String token;
    private Boolean handRisen;

    Student(String name)
    {
        this.name = name;
        this.handRisen = false;
        byte[] array = new byte[24];
        new Random().nextBytes(array);
        this.token = new String(Base64.getEncoder().encode(array));
    }

    public String getName() {
        return name;
    }

    public String getToken() {
        return token;
    }

    public Boolean isHandRisen() {
        return handRisen;
    }
}
